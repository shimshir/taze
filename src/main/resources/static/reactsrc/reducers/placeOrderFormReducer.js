import {UPDATE_PLACE_ORDER_FORM_ACTION} from "../actions/actions.js";

const placeOrderFormReducer = (placeOrderFormState = {pickupType: 'COLLECT'}, action) => {
    switch (action.type) {
        case UPDATE_PLACE_ORDER_FORM_ACTION:
            const newForm = {...placeOrderFormState};
            newForm[action.input.id] = action.input.value;
            console.log(newForm);
            return newForm;
        default:
            return placeOrderFormState;
    }
};

export default placeOrderFormReducer;
