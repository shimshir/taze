export const CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION = 'CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION';
export const ADD_TO_CART_ACTION = 'ADD_TO_CART_ACTION';
export const REMOVE_FROM_CART_ACTION = 'REMOVE_FROM_CART_ACTION';
export const UPDATE_CART_ENTRY_AMOUNT_ACTION = 'UPDATE_CART_ENTRY_AMOUNT_ACTION';

export const changeActiveTopNavbarItemAction = (topNavbarItem) => {
    return {
        type: CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION,
        topNavbarItem
    }
};

export const addToCartAction = (cartEntry) => {
    return {
        type: ADD_TO_CART_ACTION,
        cartEntry
    }
};

export const removeFromCartAction = (cartEntryIndex) => {
    return {
        type: REMOVE_FROM_CART_ACTION,
        cartEntryIndex
    }
};

export const updateCartEntryAmountAction = (cartEntryIndex, amount) => {
    return {
        type: UPDATE_CART_ENTRY_AMOUNT_ACTION,
        cartEntryIndex,
        amount
    }
};