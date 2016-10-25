import { TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION } from '../actions/actions.js';

const confirmedOrderDialogReducer = (confirmedOrderDialogState = {isOpen: false}, action) => {
    switch (action.type) {
        case TOGGLE_CONFIRMED_ORDER_DIALOG_ACTION:
            return {...confirmedOrderDialogState, isOpen: action.isOpen};
        default:
            return confirmedOrderDialogState
    }
};

export default confirmedOrderDialogReducer;