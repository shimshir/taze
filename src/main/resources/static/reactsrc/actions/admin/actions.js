import axios from "axios";
import {API_ADMIN_PATH} from "../../constants/constants.js";

export const UPDATE_CREATE_PRODUCT_FORM_ACTION = 'UPDATE_CREATE_PRODUCT_FORM_ACTION';
export const UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION = 'UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION';
export const UPDATE_CREATE_PAGE_FORM_ACTION = 'UPDATE_CREATE_PAGE_FORM_ACTION';
export const UPDATE_CREATE_STAGE_FORMS_ACTION = 'UPDATE_CREATE_STAGE_FORMS_ACTION';
export const ADD_CREATE_STAGE_FORM_ACTION = 'ADD_CREATE_STAGE_FORM_ACTION';
export const REMOVE_CREATE_STAGE_FORM_ACTION = 'REMOVE_CREATE_STAGE_FORM_ACTION';

export const asyncCreateProductAction = (dispatch, createProductForm) => {
    return axios.post(API_ADMIN_PATH + "/products", createProductForm)
        .then(res => console.log(res.data));
};

export const asyncCreateProductCardAction = (dispatch, createProductCardForm) => {
    return axios.post(API_ADMIN_PATH + "/productCards", createProductCardForm)
        .then(res => console.log(res.data));
};

export const updateCreateProductFormAction = (input) => {
    return {
        type: UPDATE_CREATE_PRODUCT_FORM_ACTION,
        input
    }
};

export const updateCreateProductCardFormAction = (input) => {
    return {
        type: UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION,
        input
    }
};

export const updateCreatePageFormAction = (input) => {
    return {
        type: UPDATE_CREATE_PAGE_FORM_ACTION,
        input
    }
};

export const updateCreateStageFormsAction = (index, input) => {
    return {
        type: UPDATE_CREATE_STAGE_FORMS_ACTION,
        index,
        input
    }
};

export const addCreateStageFormAction = () => {
    return {
        type: ADD_CREATE_STAGE_FORM_ACTION
    }
};

export const removeCreateStageFormAction = () => {
    return {
        type: REMOVE_CREATE_STAGE_FORM_ACTION
    }
};

export const asyncCreatePage = (dispatch, pageWithStages) => {
    console.log(pageWithStages);
    axios.post(API_ADMIN_PATH + "/pages", pageWithStages)
        .then(res => console.log(res.data));
};
