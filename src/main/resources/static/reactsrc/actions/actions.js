import axios from 'axios';
import {API_REST_BASE_PATH, API_CUSTOM_BASE_PATH} from '../constants/constants.js';
import Cookies from 'js-cookie';

export const CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION = 'CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION';
export const ADD_TO_CART_ACTION = 'ADD_TO_CART_ACTION';
export const REMOVE_CART_ENTRY_ACTION = 'REMOVE_CART_ENTRY_ACTION';
export const UPDATE_CART_ENTRY_AMOUNT_ACTION = 'UPDATE_CART_ENTRY_AMOUNT_ACTION';
export const UPDATE_PLACE_ORDER_FORM_ACTION = 'UPDATE_PLACE_ORDER_FORM_ACTION';
export const RECEIVE_NEW_SESSION_ACTION = 'RECEIVE_NEW_SESSION_ACTION';
export const RECEIVE_CART_ACTION = 'RECEIVE_CART_ACTION';
export const ADD_TO_ERROR_MAP_ACTION = 'ADD_TO_ERROR_MAP_ACTION';
export const REMOVE_FROM_ERROR_MAP_ACTION = 'REMOVE_FROM_ERROR_MAP_ACTION';

export const changeActiveTopNavbarItemAction = (topNavbarItem) => {
    return {
        type: CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION,
        topNavbarItem
    }
};

export const asyncCheckSessionAction = (dispatch) => {
    const session = Cookies.getJSON('tazeSession');
    if (session != undefined) {
        axios.get(API_REST_BASE_PATH + `/sessions/search/findByUuidValue?uuid=${session.uuid}`)
            .then(res => asyncGetCartAction(dispatch, res.data.uuid))
            .catch(res => {
                if (res.status == 404)
                    asyncCreateNewSessionAction(dispatch);
            });
    } else {
        asyncCreateNewSessionAction(dispatch);
    }

};

const asyncCreateNewSessionAction = (dispatch) => {
    axios.get(API_CUSTOM_BASE_PATH + '/sessions/create')
        .then(res => {
            const session = res.data;
            Cookies.set('tazeSession', session, { path: '/', expires: 1 });
            dispatch(receiveNewSessionAction(session));
            asyncGetCartAction(dispatch, session.uuid);
        });

};

const receiveNewSessionAction = (session) => {
    return {
        type: RECEIVE_NEW_SESSION_ACTION,
        session
    }
};

export const asyncGetCartAction = (dispatch, sessionUuid) => {
    axios.get(API_REST_BASE_PATH + `/carts/search/findBySessionUuidValue?sessionUuid=${sessionUuid}`)
        .then(res => asyncGetCartEntriesAction(dispatch, res.data))
        .catch(res => {
            if (res.status == 404)
                asyncCreateNewCartAction(dispatch, sessionUuid);
        });
};

const asyncCreateNewCartAction = (dispatch, sessionUuid) => {
    axios.get(API_CUSTOM_BASE_PATH + `/carts/create?sessionUuid=${sessionUuid}`)
        .then(res => asyncGetCartEntriesAction(dispatch, res.data))
};

const asyncGetCartEntriesAction = (dispatch, cartData) => {
    axios.get(cartData._links.entries.href)
        .then(res => res.data._embedded.cartEntries)
        .then(entries => dispatch(receiveCartAction({id: cartData.id, entries})));
};

const receiveCartAction = (cart) => {
    return {
        type: RECEIVE_CART_ACTION,
        cart
    }
};

export const asyncAddToCartAction = (dispatch, cartId, entry) => {
    axios.post(API_REST_BASE_PATH + `/carts/${cartId}/entries`, entry)
        .then(res => dispatch(addToCartAction(res.data)));
};

const addToCartAction = (entry) => {
    return {
        type: ADD_TO_CART_ACTION,
        entry
    }
};

export const asyncRemoveCartEntryAction = (dispatch, entryId) => {
    dispatch(removeCartEntryAction(entryId));
    axios.delete(API_REST_BASE_PATH + `/entries/${entryId}`);
};

const removeCartEntryAction = (entryId) => {
    return {
        type: REMOVE_CART_ENTRY_ACTION,
        entryId
    }
};

export const asyncUpdateCartEntryAction = (dispatch, cartId, entry) => {
    dispatch(updateCartEntryAmountAction(entry.id, entry.amount));
    axios.put(API_REST_BASE_PATH + `/carts/${cartId}/entries/${entry.id}`, entry);
};

const updateCartEntryAmountAction = (entryId, amount) => {
    return {
        type: UPDATE_CART_ENTRY_AMOUNT_ACTION,
        entryId,
        amount
    }
};

export const updatePlaceOrderFormAction = (input) => {
    return {
        type: UPDATE_PLACE_ORDER_FORM_ACTION,
        input
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
