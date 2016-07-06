import axios from 'axios';
import {API_ENDPOINT} from '../constants/constants.js';
import Cookies from 'js-cookie';

export const CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION = 'CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION';
export const ADD_TO_CART_ACTION = 'ADD_TO_CART_ACTION';
export const REMOVE_CART_ENTRY_ACTION = 'REMOVE_CART_ENTRY_ACTION';
export const UPDATE_CART_ENTRY_AMOUNT_ACTION = 'UPDATE_CART_ENTRY_AMOUNT_ACTION';
export const UPDATE_PLACE_ORDER_FORM_ACTION = 'UPDATE_PLACE_ORDER_FORM_ACTION';
export const RECEIVE_SESSION_ACTION = 'RECEIVE_SESSION_ACTION';
export const RECEIVE_CART_ACTION = 'RECEIVE_CART_ACTION';

export const changeActiveTopNavbarItemAction = (topNavbarItem) => {
    return {
        type: CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION,
        topNavbarItem
    }
};

export const asyncGetSessionAction = (dispatch) => {
    const currentSession = Cookies.getJSON('tazeSession');
    if (currentSession === undefined) {
        axios.get(API_ENDPOINT + '/session')
            .then(res => {
                const session = res.data;
                Cookies.set('tazeSession', session, { path: '/', expires: 1 });
                return session;
            })
            .then(session => {
                dispatch(receiveSessionAction(session));
                asyncGetCartAction(dispatch, session.id);
            });
    } else
        dispatch(receiveSessionAction(currentSession));
};

const receiveSessionAction = (session) => {
    return {
        type: RECEIVE_SESSION_ACTION,
        session
    }
};

export const asyncGetCartAction = (dispatch, sessionId) => {
    axios.get(API_ENDPOINT + `/cart/${sessionId}`)
        .then(res => dispatch(receiveCartAction(res.data)));
};

const receiveCartAction = (cart) => {
    return {
        type: RECEIVE_CART_ACTION,
        cart
    }
};

export const asyncAddToCartAction = (dispatch, cartId, entry) => {
    axios.post(API_ENDPOINT + `/cart/${cartId}/entries`, entry)
        .then(res => dispatch(addToCartAction(res.data)));
};

const addToCartAction = (entry) => {
    return {
        type: ADD_TO_CART_ACTION,
        entry
    }
};

export const removeCartEntryAction = (entryIndex) => {
    return {
        type: REMOVE_CART_ENTRY_ACTION,
        entryIndex
    }
};

export const updateCartEntryAmountAction = (entryIndex, amount) => {
    return {
        type: UPDATE_CART_ENTRY_AMOUNT_ACTION,
        entryIndex,
        amount
    }
};

export const updatePlaceOrderFormAction = (input) => {
    return {
        type: UPDATE_PLACE_ORDER_FORM_ACTION,
        input
    }
};