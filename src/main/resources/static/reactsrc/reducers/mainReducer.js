import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import activeTopNavbarItemReducer from './activeTopNavbarItemReducer.js';
import cartReducer from './cartReducer.js';
import placeOrderFormReducer from './placeOrderFormReducer.js';
import sessionReducer from './sessionReducer.js';
import errorsReducer from './errorsReducer.js';
import productsReducer from './productsReducer.js';
import productCardsReducer from './productCardsReducer.js';
import confirmedOrderDialogReducer from './confirmedOrderDialogReducer.js';
import orderConfirmationResultsReducer from './orderConfirmationResultsReducer.js';

import createProductFormReducer from './admin/createProductFormReducer.js';
import createProductCardFormReducer from './admin/createProductCardFormReducer.js';
import createPageFormReducer from './admin/createPageFormReducer.js';
import createStageFormsReducer from './admin/createStageFormsReducer.js';

import pagesReducer from './pagesReducer.js';

const MainReducer = combineReducers(
    {
        routing: routerReducer,
        activeTopNavbarItem: activeTopNavbarItemReducer,
        cart: cartReducer,
        placeOrderForm: placeOrderFormReducer,
        session: sessionReducer,
        errors: errorsReducer,
        products: productsReducer,
        productCards: productCardsReducer,
        confirmedOrderDialog: confirmedOrderDialogReducer,
        orderConfirmationResults: orderConfirmationResultsReducer,
        createProductForm: createProductFormReducer,
        createProductCardForm: createProductCardFormReducer,
        createPageForm: createPageFormReducer,
        createStageForms: createStageFormsReducer,
        pages: pagesReducer
    }
);

export default MainReducer
