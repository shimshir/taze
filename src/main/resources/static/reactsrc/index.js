import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, browserHistory } from 'react-router';
import routes from './routes';
import { createStore } from 'redux';
import { syncHistoryWithStore } from 'react-router-redux';
import MainReducer from './reducer/mainReducer.js';

const store = createStore(MainReducer);
//const unsubscribe = store.subscribe(() => console.log(store.getState()))

const history = syncHistoryWithStore(browserHistory, store);

ReactDOM.render(
    <Provider store={store}>
        <Router history={history} routes={routes} />
    </Provider>,
    document.getElementById('root')
);
