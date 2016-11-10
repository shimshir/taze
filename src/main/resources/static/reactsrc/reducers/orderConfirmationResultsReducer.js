import {RECEIVE_ORDER_CONFIRMATION_RESULT_ACTION} from '../actions/actions.js';

const orderConfirmationResultsReducer = (orderConfirmationResultsState = {}, action) => {
    switch (action.type) {
        case RECEIVE_ORDER_CONFIRMATION_RESULT_ACTION:
            const stateCopy = {...orderConfirmationResultsState};
            stateCopy[action.orderId] = action.confirmationResult;
            return stateCopy;
        default:
            return orderConfirmationResultsState
    }
};

export default orderConfirmationResultsReducer;
