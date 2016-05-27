import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';

const MainReducer = combineReducers(
    {
        routing: routerReducer
    }
);

export default MainReducer