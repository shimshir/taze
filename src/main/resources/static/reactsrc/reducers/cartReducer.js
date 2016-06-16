import { ADD_TO_CART_ACTION, REMOVE_FROM_CART_ACTION } from '../actions/actions.js';

const privateCartEntryReducer = (cartEntriesState = [], action) => {
  switch (action.type) {
      case ADD_TO_CART_ACTION:
          return [...cartEntriesState, action.cartEntry];
      case REMOVE_FROM_CART_ACTION:
          return cartEntriesState
              .reduce((acc, entry, index) => {
                  index != action.cartEntryIndex ? acc.push(entry) : acc;
                  return acc;
              }, []);
      default:
          return cartEntriesState;
  }
};

//TODO: Remove stub entries
import {CHICKEN, HONEY} from '../constants/constants.js';
const stubEntries = [{product: CHICKEN, amount: 3}, {product: HONEY, amount: 5}, {product: CHICKEN, amount: 1}];
//

const cartReducer = (cartState = {cartEntries: stubEntries}, action) => {
    switch (action.type) {
        case ADD_TO_CART_ACTION:
        case REMOVE_FROM_CART_ACTION:
            const cartEntries = privateCartEntryReducer(cartState.cartEntries, action);
            return Object.assign({}, cartState, {cartEntries});
        default:
            return cartState;
    }
};

export default cartReducer;