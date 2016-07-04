import { UPDATE_PLACE_ORDER_FORM_ACTION } from '../actions/actions.js';

const placeOrderFormReducer = (placeOrderFormState = new Map, action) => {
    switch (action.type) {
        case UPDATE_PLACE_ORDER_FORM_ACTION:
            return new Map(placeOrderFormState.set(action.input.id, action.input.value));
        default:
            return placeOrderFormState;
    }
};

export default placeOrderFormReducer;
