import {
    RECEIVE_CART_ACTION,
    RECEIVE_CART_ENTRIES_ACTION,
    ADD_TO_CART_ACTION,
    REMOVE_CART_ENTRY_ACTION,
    UPDATE_CART_ENTRY_AMOUNT_ACTION,
    CHANGE_PICKUP_TYPE_ACTION
} from '../actions/actions.js';

import {DELIVERY_OPTIONS} from '../constants/constants.js';

const privateEntryReducer = (entriesState = [], action) => {
    switch (action.type) {
        case ADD_TO_CART_ACTION:
            return [...entriesState, action.entry];
        case REMOVE_CART_ENTRY_ACTION:
            return entriesState
                .reduce((acc, entry) => {
                    entry.id != action.entryId ? acc.push(entry) : acc;
                    return acc;
                }, []);
        case UPDATE_CART_ENTRY_AMOUNT_ACTION:
            return entriesState
                .map(entry => entry.id === action.entry.id ? action.entry : entry);
        case RECEIVE_CART_ENTRIES_ACTION:
            return action.entries;
        default:
            return entriesState;
    }
};

const cartReducer = (cartState = {}, action) => {
    switch (action.type) {
        case RECEIVE_CART_ACTION:
            return action.cart;
        case ADD_TO_CART_ACTION:
        case REMOVE_CART_ENTRY_ACTION:
        case UPDATE_CART_ENTRY_AMOUNT_ACTION:
        case RECEIVE_CART_ENTRIES_ACTION:
            const entries = privateEntryReducer(cartState.entries, action);
            const pickupTypePrice = cartState.pickupType ? DELIVERY_OPTIONS.find(_ => _.value == cartState.pickupType).price : 0;
            return {...cartState, entries, totalPrice: entries.map(_ => _.totalPrice).reduce((acc, cur) => acc + cur, 0) + pickupTypePrice};
        case CHANGE_PICKUP_TYPE_ACTION:
            return {...cartState, pickupType: action.pickupType, totalPrice: action.totalPrice};
        default:
            return cartState;
    }
};

export default cartReducer;