import {UPDATE_CREATE_PAGE_FORM_ACTION} from '../../actions/admin/actions.js';

const createPageFormReducer = (createPageFormState = {}, action) => {
    switch (action.type) {
        case UPDATE_CREATE_PAGE_FORM_ACTION:
            const newForm = {...createPageFormState};
            newForm[action.input.id] = action.input.value;
            return newForm;
        default:
            return createPageFormState
    }
};

export default createPageFormReducer;
