import { ADD_TO_CART_ACTION } from '../actions/actions.js';

const orderEntryReducer = (orderEntriesState = [], action) => {
  switch (action.type) {
      case ADD_TO_CART_ACTION:
          return [...orderEntriesState, action.orderEntry]
      default:
          return orderEntriesState;
  }
};

const cartReducer = (cartState = {}, action) => {
    switch (action.type) {
        case ADD_TO_CART_ACTION:
            const orderEntries = orderEntryReducer(cartState.orderEntries, action);
            const cart = Object.assign({}, cartState, {orderEntries});
            console.log(cart);
            return cart;
        default:
            return cartState;
    }
};

export default cartReducer;