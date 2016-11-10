import {changeActiveTopNavbarItemAction, asyncGetPageAction} from '../../actions/actions.js';

export const pageDispatchToPropsMappings = (dispatch, ownProps) => {
    return {
        changeActiveTopNavbarItem: (topNavbarItem) => {
            dispatch(changeActiveTopNavbarItemAction(topNavbarItem));
        },
        // TODO: Would probably be better to use "path" instead of "code", since "path" is already known when on a specific page
        getPage: (code) => {
            asyncGetPageAction(dispatch, code);
        }
    };
};
