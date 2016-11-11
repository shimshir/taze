import {RECEIVE_PAGE_ACTION} from '../actions/actions.js';

const pagesReducer = (pagesState = {}, action) => {
    switch (action.type) {
        case RECEIVE_PAGE_ACTION:
            const stateCopy = {...pagesState};
            stateCopy[action.page.path] = action.page;
            return stateCopy;
        default:
            return pagesState
    }
};

export default pagesReducer;
