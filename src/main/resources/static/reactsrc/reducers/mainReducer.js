import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import activeTopNavbarItemReducer from './activeTopNavbarItemReducer.js';
import cartReducer from './cartReducer.js';

const MainReducer = combineReducers({
    routing: routerReducer,
    activeTopNavbarItem: activeTopNavbarItemReducer,
    cart: cartReducer

});

export default MainReducer