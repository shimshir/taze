import { CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION } from '../actions/actions.js';

const activeTopNavbarItemReducer = (activeTopNavbarItemState = '', action) => {
    switch (action.type) {
        case CHANGE_ACTIVE_TOP_NAVBAR_ITEM_ACTION:
            return action.topNavbarItem;
        default:
            return activeTopNavbarItemState
    }
};

export default activeTopNavbarItemReducer;