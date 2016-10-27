import axios from 'axios';
import {API_REST_BASE_PATH} from '../constants/constants.js';
import Cookies from 'js-cookie';

export const CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION = 'CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION';
export const ADD_TO_CART_ACTION = 'ADD_TO_CART_ACTION';
export const REMOVE_CART_ENTRY_ACTION = 'REMOVE_CART_ENTRY_ACTION';
export const UPDATE_CART_ENTRY_AMOUNT_ACTION = 'UPDATE_CART_ENTRY_AMOUNT_ACTION';
export const UPDATE_PLACE_ORDER_FORM_ACTION = 'UPDATE_PLACE_ORDER_FORM_ACTION';
export const RECEIVE_NEW_SESSION_ACTION = 'RECEIVE_NEW_SESSION_ACTION';
export const RECEIVE_CART_ACTION = 'RECEIVE_CART_ACTION';
export const RECEIVE_CART_ENTRIES_ACTION = 'RECEIVE_CART_ENTRIES_ACTION';
export const RECEIVE_PRODUCTS_ACTION = 'RECEIVE_PRODUCTS_ACTION';
export const RECEIVE_PRODUCT_ACTION = 'RECEIVE_PRODUCT_ACTION';
export const ADD_TO_ERROR_MAP_ACTION = 'ADD_TO_ERROR_MAP_ACTION';
export const REMOVE_FROM_ERROR_MAP_ACTION = 'REMOVE_FROM_ERROR_MAP_ACTION';
export const TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION = 'TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION';

export const changeActiveTopNavbarItemAction = (topNavbarItem) => {
    return {
        type: CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION,
        topNavbarItem
    }
};

export const asyncCheckSessionAction = (dispatch) => {
    const session = Cookies.getJSON('tazeSession');
    if (session != undefined) {
        axios.get(API_REST_BASE_PATH + `/sessions/${session.uuid}`)
            .then(res => {
                      dispatch(receiveNewSessionAction(res.data));
                      asyncGetCartAction(dispatch, res.data);
                  }
            )
            .catch(res => {
                if (res.status == 404) {
                    asyncCreateNewSessionAction(dispatch);
                }
            });
    } else {
        asyncCreateNewSessionAction(dispatch);
    }

};

const asyncCreateNewSessionAction = (dispatch) => {
    axios.post(API_REST_BASE_PATH + '/sessions', {})
        .then(sessionPostRes => axios.get(sessionPostRes.headers.location))
        .then(sessionGetRes => {
            const session = sessionGetRes.data;
            Cookies.set('tazeSession', session, {path: '/', expires: 1});
            dispatch(receiveNewSessionAction(session));
            asyncGetCartAction(dispatch, session);
        });

};

const receiveNewSessionAction = (session) => {
    return {
        type: RECEIVE_NEW_SESSION_ACTION,
        session
    }
};

export const asyncGetCartAction = (dispatch, session) => {
    axios.get(API_REST_BASE_PATH + `/orders/search/findBySessionUuidAndStatusValue?sessionUuid=${session.uuid}&status=CART`)
        .then(res => {
            if (res.data._embedded) {
                const order = res.data._embedded.orders[0];
                dispatch(receiveCartAction(order));
                asyncGetCartEntriesAction(dispatch, order._links.entries.href);
            } else {
                asyncCreateNewCartAction(dispatch, session);
            }
        })
        .catch(res => {
            if (res.status == 404) {
                asyncCreateNewCartAction(dispatch, session);
            }
        });
};

const asyncCreateNewCartAction = (dispatch, session) => {
    return axios.post(API_REST_BASE_PATH + '/orders', {session: session._links.self.href, status: 'CART'})
        .then(cartPostRes => axios.get(cartPostRes.headers.location))
        .then(cartGetRes => {
            dispatch(receiveCartAction(cartGetRes.data));
            asyncGetCartEntriesAction(dispatch, cartGetRes.data._links.entries.href)
        });
};

const receiveCartAction = (cart) => {
    return {
        type: RECEIVE_CART_ACTION,
        cart
    }
};

const asyncGetCartEntriesAction = (dispatch, entriesUri) => {
    axios.get(entriesUri, {params: {projection: 'with-product'}})
        .then(res => dispatch(receiveCartEntriesAction(res.data._embedded ? res.data._embedded.orderEntries : [])));
};

const receiveCartEntriesAction = (entries) => {
    return {
        type: RECEIVE_CART_ENTRIES_ACTION,
        entries
    }
};

export const asyncAddToCartAction = (dispatch, cartUri, entry) => {
    axios.post(API_REST_BASE_PATH + '/orderEntries?projection=with-product',
               {
                   ...entry,
                   product: entry.product._links.self.href,
                   order: cartUri
               })
        .then(postEntryRes => axios.get(postEntryRes.headers.location, {params: {projection: 'with-product'}}))
        .then(getEntryRes => dispatch(addToCartAction(getEntryRes.data)));
};

const addToCartAction = (entry) => {
    return {
        type: ADD_TO_CART_ACTION,
        entry
    }
};

export const asyncRemoveCartEntryAction = (dispatch, entryId) => {
    dispatch(removeCartEntryAction(entryId));
    axios.delete(API_REST_BASE_PATH + `/orderEntries/${entryId}`).catch(res => console.log(res));
};

const removeCartEntryAction = (entryId) => {
    return {
        type: REMOVE_CART_ENTRY_ACTION,
        entryId
    }
};

export const asyncUpdateCartEntryAction = (dispatch, entryId, amount) => {
    axios.patch(API_REST_BASE_PATH + `/orderEntries/${entryId}?projection=with-product`, {amount})
        .then(orderEntryPatchRes => axios.get(API_REST_BASE_PATH + `/orderEntries/${entryId}?projection=with-product`))
        .then(orderEntryGetRes => dispatch(updateCartEntryAmountAction(orderEntryGetRes.data)));
};

const updateCartEntryAmountAction = (entry) => {
    return {
        type: UPDATE_CART_ENTRY_AMOUNT_ACTION,
        entry
    }
};

export const asyncGetProductsAction = (dispatch) => {
    axios.get(API_REST_BASE_PATH + '/products')
        .then(res => dispatch(receiveProductsAction(res.data._embedded.products)));
};

const receiveProductsAction = (products) => {
    return {
        type: RECEIVE_PRODUCTS_ACTION,
        products
    }
};

export const asyncGetProductAction = (dispatch, productcode) => {
    axios.get(API_REST_BASE_PATH + `/products/search/findByCode?code=${productcode}`)
        .then(res => {
            if (res.data._embedded) {
                const product = res.data._embedded.products[0];
                dispatch(receiveProductAction(product))
            }
        });
};

const receiveProductAction = (product) => {
    return {
        type: RECEIVE_PRODUCT_ACTION,
        product
    }
};

export const updatePlaceOrderFormAction = (input) => {
    return {
        type: UPDATE_PLACE_ORDER_FORM_ACTION,
        input
    }
};

export const asyncPlaceOrderAction = (dispatch, placeOrderForm, cart, session) => {
    asyncCreateCustomerAction(dispatch, placeOrderForm, session)
        .then(customerCreateRes => axios.get(customerCreateRes.headers.location))
        .then(customerGetRes => asyncCreateOrderAction(dispatch, cart, session, customerGetRes.data))
        .then(createOrderRes => asyncCreateNewCartAction(dispatch, session))
        .then(createNewCartRes => dispatch(toggleConfirmedOrderDialogAction(true)));
};

const asyncCreateCustomerAction = (dispatch, placeOrderForm, session) => {
    return axios.post(API_REST_BASE_PATH + '/customers', {
        firstName: placeOrderForm.firstName,
        lastName: placeOrderForm.lastName,
        address: placeOrderForm.address,
        email: placeOrderForm.email,
        session: session._links.self.href
    });
};

const asyncCreateOrderAction = (dispatch, cart, session, customer) => {
    return axios.patch(cart._links.self.href, {
        customer: customer._links.self.href,
        session: session._links.self.href,
        status: 'ORDERED'
    });
};

export const addToErrorMapAction = (key, error) => {
    return {
        type: ADD_TO_ERROR_MAP_ACTION,
        key,
        error
    }
};

export const removeFromErrorMapAction = (key) => {
    return {
        type: REMOVE_FROM_ERROR_MAP_ACTION,
        key
    }
};

export const toggleConfirmedOrderDialogAction = (isOpen) => {
    return {
        type: TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION,
        isOpen
    }
};
