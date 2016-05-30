import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import activeTopNavbarItemReducer from './activeTopNavbarItemReducer.js';

const MainReducer = combineReducers({
    activeTopNavbarItem: activeTopNavbarItemReducer,
    routing: routerReducer
});

export default MainReducer