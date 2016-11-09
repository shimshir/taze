import {RECEIVE_PRODUCT_CARDS_ACTION} from "../actions/actions.js";

const productCardsReducer = (productCardsState = [], action) => {
    switch (action.type) {
        case RECEIVE_PRODUCT_CARDS_ACTION:
            return action.productCards;
        default:
            return productCardsState
    }
};

export default productCardsReducer;
