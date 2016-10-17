import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import activeTopNavbarItemReducer from './activeTopNavbarItemReducer.js';
import cartReducer from './cartReducer.js';
import placeOrderFormReducer from './placeOrderFormReducer.js';
import sessionReducer from './sessionReducer.js';
import errorMapReducer from './errorMapReducer.js';

const MainReducer = combineReducers({
    routing: routerReducer,
    activeTopNavbarItem: activeTopNavbarItemReducer,
    cart: cartReducer,
    placeOrderForm: placeOrderFormReducer,
    session: sessionReducer,
    errorMap: errorMapReducer
});

export default MainReducer