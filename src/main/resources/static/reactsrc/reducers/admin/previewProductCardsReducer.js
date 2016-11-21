import {RECEIVE_PREVIEW_PRODUCT_CARD_ACTION} from '../../actions/admin/actions.js';

const previewProductCardsReducer = (previewProductCardsState = {}, action) => {
    switch (action.type) {
        case RECEIVE_PREVIEW_PRODUCT_CARD_ACTION:
            const stateCopy = {...previewProductCardsState};
            stateCopy[action.productCode] = action.previewProductCard;
            return stateCopy;
        default:
            return previewProductCardsState
    }
};

export default previewProductCardsReducer;
