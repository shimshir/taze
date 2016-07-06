import { RECEIVE_CART_ACTION, ADD_TO_CART_ACTION, REMOVE_CART_ENTRY_ACTION, UPDATE_CART_ENTRY_AMOUNT_ACTION } from '../actions/actions.js';

const privateEntryReducer = (entriesState = [], action) => {
  switch (action.type) {
      case ADD_TO_CART_ACTION:
          return [...entriesState, action.entry];
      case REMOVE_CART_ENTRY_ACTION:
          return entriesState
              .reduce((acc, entry, index) => {
                  index != action.entryIndex ? acc.push(entry) : acc;
                  return acc;
              }, []);
      case UPDATE_CART_ENTRY_AMOUNT_ACTION:
          return entriesState
              .map((entry, index) => {
                 if (index === action.entryIndex)
                     return {...entry, amount: action.amount};
                  else
                     return entry;
              });
      default:
          return entriesState;
  }
};

const cartReducer = (cartState = {entries: []}, action) => {
    switch (action.type) {
        case RECEIVE_CART_ACTION:
            return action.cart;
        case ADD_TO_CART_ACTION:
        case REMOVE_CART_ENTRY_ACTION:
        case UPDATE_CART_ENTRY_AMOUNT_ACTION:
            const entries = privateEntryReducer(cartState.entries, action);
            return {...cartState, entries};
        default:
            return cartState;
    }
};

export default cartReducer;