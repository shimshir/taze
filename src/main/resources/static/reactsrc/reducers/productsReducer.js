import {RECEIVE_PRODUCTS_ACTION, RECEIVE_PRODUCT_ACTION} from "../actions/actions.js";

const productsReducer = (productsState = [], action) => {
    switch (action.type) {
        case RECEIVE_PRODUCTS_ACTION:
            return action.products;
        case RECEIVE_PRODUCT_ACTION:
            const filteredProducts = productsState.filter(product => product.code != action.product.code);
            filteredProducts.push(action.product);
            return filteredProducts;
        default:
            return productsState
    }
};

export default productsReducer;
