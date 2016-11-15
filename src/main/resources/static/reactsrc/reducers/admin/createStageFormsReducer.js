import {UPDATE_CREATE_STAGE_FORMS_ACTION, ADD_CREATE_STAGE_FORM_ACTION, REMOVE_CREATE_STAGE_FORM_ACTION} from '../../actions/admin/actions.js';

const createStageFormsReducer = (createStageFormsState = [{}], action) => {
    switch (action.type) {
        case UPDATE_CREATE_STAGE_FORMS_ACTION:
            return createStageFormsState.map((form, index) => {
                if (index == action.index) {
                    const newForm = {...form};
                    newForm[action.input.id] = action.input.value;
                    return newForm;
                } else {
                    return form;
                }
            });
        case ADD_CREATE_STAGE_FORM_ACTION:
            const formsAfterAdd = Array.from(createStageFormsState);
            formsAfterAdd.push({});
            return formsAfterAdd;
        case REMOVE_CREATE_STAGE_FORM_ACTION:
            const formsAfterRemove = Array.from(createStageFormsState);
            formsAfterRemove.pop();
            return formsAfterRemove;
        default:
            return createStageFormsState
    }
};

export default createStageFormsReducer;
