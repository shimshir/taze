import {changeActiveTopNavbarItemAction} from '../../actions/actions.js';

export const changeActiveTopNavbarItemDispatchMapping = (dispatch, ownProps) => {
    return {
        changeActiveTopNavbarItem: (topNavbarItem) => {
            dispatch(changeActiveTopNavbarItemAction(topNavbarItem));
        }
    };
};
