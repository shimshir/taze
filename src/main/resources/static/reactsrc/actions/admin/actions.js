import axios from "axios";
import {API_ADMIN_PATH} from "../../constants/constants.js";

export const UPDATE_CREATE_PRODUCT_FORM_ACTION = 'UPDATE_CREATE_PRODUCT_FORM_ACTION';

export const asyncCreateProductAction = (dispatch, createProductForm) => {
    return axios.post(API_ADMIN_PATH + "/products", createProductForm)
        .then(res => console.log(res.data));
};

export const updateCreateProductFormAction = (input) => {
    return {
        type: UPDATE_CREATE_PRODUCT_FORM_ACTION,
        input
    }
};
