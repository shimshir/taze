import axios from "axios";
import {API_REST_PATH} from "../constants/constants.js";
import Cookies from "js-cookie";

export const CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION = 'CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION';
export const ADD_TO_CART_ACTION = 'ADD_TO_CART_ACTION';
export const REMOVE_CART_ENTRY_ACTION = 'REMOVE_CART_ENTRY_ACTION';
export const UPDATE_CART_ENTRY_AMOUNT_ACTION = 'UPDATE_CART_ENTRY_AMOUNT_ACTION';
export const UPDATE_PLACE_ORDER_FORM_ACTION = 'UPDATE_PLACE_ORDER_FORM_ACTION';
export const RECEIVE_NEW_SESSION_ACTION = 'RECEIVE_NEW_SESSION_ACTION';
export const RECEIVE_CART_ACTION = 'RECEIVE_CART_ACTION';
export const RECEIVE_CART_ENTRIES_ACTION = 'RECEIVE_CART_ENTRIES_ACTION';
export const RECEIVE_PRODUCTS_ACTION = 'RECEIVE_PRODUCTS_ACTION';
export const RECEIVE_PRODUCT_CARDS_ACTION = 'RECEIVE_PRODUCT_CARDS_ACTION';
export const RECEIVE_PRODUCT_ACTION = 'RECEIVE_PRODUCT_ACTION';
export const ADD_TO_ERROR_MAP_ACTION = 'ADD_TO_ERROR_MAP_ACTION';
export const REMOVE_FROM_ERROR_MAP_ACTION = 'REMOVE_FROM_ERROR_MAP_ACTION';
export const TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION = 'TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION';
export const RECEIVE_ORDER_CONFIRMATION_RESULT_ACTION = 'RECEIVE_ORDER_CONFIRMATION_RESULT_ACTION';
export const RECEIVE_PAGE_ACTION = 'RECEIVE_PAGE_ACTION';

export const changeActiveTopNavbarItemAction = (topNavbarItem) => {
    return {
        type: CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION,
        topNavbarItem
    }
};

export const asyncCheckSessionAction = (dispatch) => {
    const session = Cookies.getJSON('tazeSession');
    if (session != undefined) {
        axios.get(API_REST_PATH + `/sessions/${session.id}`)
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
    axios.post(API_REST_PATH + '/sessions', {})
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
    axios.get(API_REST_PATH + `/orders/search/findBySessionIdAndStatusId?sessionId=${session.id}&status=CART`)
        .then(res => {
            if (res.data.id) {
                const cart = res.data;
                asyncGetCartEntriesAction(dispatch, cart._links.entries.href)
                    .then(entries => dispatch(receiveCartAction({...cart, entries})));
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
    return axios.post(API_REST_PATH + '/orders', {session: session._links.self.href, status: 'CART'})
        .then(postCartRes => axios.get(postCartRes.headers.location))
        .then(getCartRes =>
                  asyncGetCartEntriesAction(dispatch, getCartRes.data._links.entries.href)
                      .then(entries => dispatch(receiveCartAction({...getCartRes.data, entries})))
        );
};

const receiveCartAction = (cart) => {
    return {
        type: RECEIVE_CART_ACTION,
        cart
    }
};

const asyncGetCartEntriesAction = (dispatch, entriesUri) => {
    return axios.get(entriesUri, {params: {projection: 'with-product'}})
        .then(res => {
            const entries = res.data._embedded ? res.data._embedded.orderEntries : [];
            dispatch(receiveCartEntriesAction(entries));
            return entries;
        });
};

const receiveCartEntriesAction = (entries) => {
    return {
        type: RECEIVE_CART_ENTRIES_ACTION,
        entries
    }
};

export const asyncAddToCartAction = (dispatch, cartUri, entry) => {
    axios.post(API_REST_PATH + '/orderEntries?projection=with-product',
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
    axios.delete(API_REST_PATH + `/orderEntries/${entryId}`).catch(res => console.log(res));
};

const removeCartEntryAction = (entryId) => {
    return {
        type: REMOVE_CART_ENTRY_ACTION,
        entryId
    }
};

export const asyncUpdateCartEntryAction = (dispatch, entryId, amount) => {
    axios.patch(API_REST_PATH + `/orderEntries/${entryId}?projection=with-product`, {amount})
        .then(orderEntryPatchRes => axios.get(API_REST_PATH + `/orderEntries/${entryId}?projection=with-product`))
        .then(orderEntryGetRes => dispatch(updateCartEntryAmountAction(orderEntryGetRes.data)));
};

const updateCartEntryAmountAction = (entry) => {
    return {
        type: UPDATE_CART_ENTRY_AMOUNT_ACTION,
        entry
    }
};

export const asyncGetProductsAction = (dispatch) => {
    axios.get(API_REST_PATH + '/products')
        .then(res => dispatch(receiveProductsAction(res.data._embedded.products)));
};

const receiveProductsAction = (products) => {
    return {
        type: RECEIVE_PRODUCTS_ACTION,
        products
    }
};

export const asyncGetProductAction = (dispatch, productcode) => {
    axios.get(API_REST_PATH + `/products/search/findByCode?code=${productcode}`)
        .then(res => dispatch(receiveProductAction(res.data)));
};

const receiveProductAction = (product) => {
    return {
        type: RECEIVE_PRODUCT_ACTION,
        product
    }
};

export const asyncGetProductCardsAction = (dispatch) => {
    return axios.get(API_REST_PATH + '/productCards')
        .then(res => dispatch(receiveProductCardsAction(res.data._embedded.productCards)));
};

const receiveProductCardsAction = (productCards) => {
    return {
        type: RECEIVE_PRODUCT_CARDS_ACTION,
        productCards
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
        .then(customerGetRes => asyncCreateOrderAction(dispatch, cart, customerGetRes.data, placeOrderForm.pickupType))
        .then(createOrderRes => asyncCreateNewCartAction(dispatch, session))
        .then(createNewCartRes => dispatch(toggleConfirmedOrderDialogAction(true)));
};

const asyncCreateCustomerAction = (dispatch, placeOrderForm, session) => {
    return axios.post(API_REST_PATH + '/customers', {
        firstName: placeOrderForm.firstName,
        lastName: placeOrderForm.lastName,
        address: placeOrderForm.address,
        email: placeOrderForm.email,
        session: session._links.self.href
    });
};

const asyncCreateOrderAction = (dispatch, cart, customer, pickupType) => {
    return axios.patch(cart._links.self.href, {
        customer: customer._links.self.href,
        status: 'ORDERED',
        pickupType
    });
};

export const asyncConfirmOrderAction = (dispatch, orderId, token) => {
    return axios({
                     method: 'patch',
                     url: API_REST_PATH + `/orders/${orderId}`,
                     data: {
                         status: 'CONFIRMED'
                     },
                     headers: {
                         'X-Confirmation-Token': token
                     }
                 })
        .then(confirmOrderRes => dispatch(receiveOrderConfirmationResultAction(orderId, {status: 'success', message: 'Your order has been confirmed'})))
        .catch(errorRes => dispatch(receiveOrderConfirmationResultAction(orderId, {status: 'error', message: errorRes.data.message})));
};

const receiveOrderConfirmationResultAction = (orderId, confirmationResult) => {
    return {
        type: RECEIVE_ORDER_CONFIRMATION_RESULT_ACTION,
        orderId,
        confirmationResult
    }
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

export const asyncGetPageAction = (dispatch, path) => {
    return axios.get(API_REST_PATH + `/pages/search/findByPath?path=${path}`)
        .then(res => dispatch(receivePageAction(res.data)));
};

const receivePageAction = (page) => {
    return {
        type: RECEIVE_PAGE_ACTION,
        page
    }
};