import {UPDATE_CREATE_PRODUCT_FORM_ACTION} from '../../actions/admin/actions.js';

const createProductFormReducer = (createProductFormState = {}, action) => {
    switch (action.type) {
        case UPDATE_CREATE_PRODUCT_FORM_ACTION:
            const newForm = {...createProductFormState};
            newForm[action.input.id] = action.input.value;
            return newForm;
        default:
            return createProductFormState
    }
};

export default createProductFormReducer;
