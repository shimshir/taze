import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import activeTopNavbarItemReducer from './activeTopNavbarItemReducer.js';
import cartReducer from './cartReducer.js';
import placeOrderFormReducer from './placeOrderFormReducer.js';
import sessionReducer from './sessionReducer.js';
import errorMapReducer from './errorMapReducer.js';
import productsReducer from './productsReducer.js';
import confirmedOrderDialogReducer from './confirmedOrderDialogReducer.js';
import orderConfirmationResultsReducer from './orderConfirmationResultsReducer.js';
import createProductFormReducer from './admin/createProductFormReducer.js';
import createProductCardFormReducer from './admin/createProductCardFormReducer.js';


const MainReducer = combineReducers({
    routing: routerReducer,
    activeTopNavbarItem: activeTopNavbarItemReducer,
    cart: cartReducer,
    placeOrderForm: placeOrderFormReducer,
    session: sessionReducer,
    errorMap: errorMapReducer,
    products: productsReducer,
    confirmedOrderDialog: confirmedOrderDialogReducer,
    orderConfirmationResults: orderConfirmationResultsReducer,
    createProductForm: createProductFormReducer,
    createProductCardForm: createProductCardFormReducer
});

export default MainReducer