import { ADD_TO_ERRORS_ACTION, REMOVE_FROM_ERRORS_ACTION } from '../actions/actions.js';

const errorsReducer = (errorsState = {}, action) => {
    switch (action.type) {
        case ADD_TO_ERRORS_ACTION:
            const addToErrorsCopy = {...errorsState};
            addToErrorsCopy[action.key] = action.error;
            return addToErrorsCopy;
        case REMOVE_FROM_ERRORS_ACTION:
            const removeFromErrorsCopy = {...errorsState};
            delete removeFromErrorsCopy[action.key];
            return removeFromErrorsCopy;
        default:
            return errorsState
    }
};

export default errorsReducer;
