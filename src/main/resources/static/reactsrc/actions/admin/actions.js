import axios from "axios";
import {API_ADMIN_PATH, API_REST_PATH} from "../../constants/constants.js";

export const UPDATE_CREATE_PRODUCT_FORM_ACTION = 'UPDATE_CREATE_PRODUCT_FORM_ACTION';
export const UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION = 'UPDATE_CREATE_PRODUCT_CARD_FORM_ACTION';
export const UPDATE_CREATE_PAGE_FORM_ACTION = 'UPDATE_CREATE_PAGE_FORM_ACTION';
export const UPDATE_CREATE_STAGE_FORMS_ACTION = 'UPDATE_CREATE_STAGE_FORMS_ACTION';
export const ADD_CREATE_STAGE_FORM_ACTION = 'ADD_CREATE_STAGE_FORM_ACTION';
export const REMOVE_CREATE_STAGE_FORM_ACTION = 'REMOVE_CREATE_STAGE_FORM_ACTION';
export const RECEIVE_PREVIEW_PRODUCT_CARD_ACTION = 'RECEIVE_PREVIEW_PRODUCT_CARD_ACTION';
export const RECEIVE_ORDERS_ACTION = 'RECEIVE_ORDERS_ACTION';

export const asyncCreateProductAction = (dispatch, createProductForm) => {
    return axios.post(API_ADMIN_PATH + "/products", createProductForm)
        .then(res => console.log(res.data));
};

export const asyncCreateProductCardAction = (dispatch, createProductCardForm) => {
    return axios.post(API_ADMIN_PATH + "/productCards", createProductCardForm)
        .then(res => console.log(res.data));
};

export const asyncGetOrdersAction = (dispatch) => {
    return axios.get(API_REST_PATH + "/orders")
        .then(res => res.data._embedded.orders)
        .then(orders =>
                  axios.get(API_REST_PATH + "/orderEntries?projection=with-product")
                      .then(res => res.data._embedded.orderEntries)
                      .then(entries => orders.map(order => ({...order, entries: entries.filter(entry => entry.orderId == order.id)}))))
        .then(orders =>
                  orders.map(order =>
                                 axios.get(order._links.customer.href)
                                     .catch(error => {
                                         if (error.response.status == 404) {
                                             return {...order, customer: null}
                                         }
                                     })
                                     .then(res => ({...order, customer: res.data}))
                  )
        )
        .then(orderPromises => Promise.all(orderPromises))
        .then(orders => dispatch(receiveOrdersAction(orders)));
};

const receiveOrdersAction = (orders) => {
    return {
        type: RECEIVE_ORDERS_ACTION,
        orders
    }
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
    axios.post(API_ADMIN_PATH + "/pages", pageWithStages)
        .then(res => console.log(res.data));
};

export const asyncGetPreviewProductCardAction = (dispatch, productCode) => {
    axios.get(API_REST_PATH + `/productCards/search/findByProductCode?code=${productCode}`)
        .then(getProductCardRes => dispatch(receivePreviewProductCardAction(productCode, getProductCardRes.data)));
};

const receivePreviewProductCardAction = (productCode, previewProductCard) => {
    return {
        type: RECEIVE_PREVIEW_PRODUCT_CARD_ACTION,
        productCode,
        previewProductCard
    }
};
