import { ADD_TO_ERROR_MAP_ACTION, REMOVE_FROM_ERROR_MAP_ACTION } from '../actions/actions.js';

//TODO: Change the map to an array of error objects
const errorMapReducer = (errorMap = new Map(), action) => {
    switch (action.type) {
        case ADD_TO_ERROR_MAP_ACTION:
            return new Map(errorMap).set(action.key, action.error);
        case REMOVE_FROM_ERROR_MAP_ACTION:
            errorMap.delete(action.key);
            return new Map(errorMap);
        default:
            return errorMap
    }
};

export default errorMapReducer;