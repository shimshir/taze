import { RECEIVE_NEW_SESSION_ACTION } from '../actions/actions.js';

const sessionReducer = (sessionState = {}, action) => {
    switch (action.type) {
        case RECEIVE_NEW_SESSION_ACTION:
            return action.session;
        default:
            return sessionState
    }
};

export default sessionReducer;
