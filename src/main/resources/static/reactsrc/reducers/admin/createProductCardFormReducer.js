import {UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION} from '../../actions/admin/actions.js';

const createProductCardFormReducer = (createProductCardFormState = {}, action) => {
    switch (action.type) {
        case UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION:
            const newForm = {...createProductCardFormState};
            newForm[action.input.id] = action.input.value;
            return newForm;
        default:
            return createProductCardFormState
    }
};

export default createProductCardFormReducer;
