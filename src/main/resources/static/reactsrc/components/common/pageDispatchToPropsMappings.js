import {changeActiveTopNavbarItemAction, asyncGetPageAction} from '../../actions/actions.js';

export const pageDispatchToPropsMappings = (dispatch, ownProps) => {
    return {
        changeActiveTopNavbarItem: (topNavbarItem) => {
            dispatch(changeActiveTopNavbarItemAction(topNavbarItem));
        },
        getPage: (path) => {
            asyncGetPageAction(dispatch, path);
        }
    };
};
