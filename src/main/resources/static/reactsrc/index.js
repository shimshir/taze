import "babel-polyfill";
import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {Router, browserHistory} from 'react-router';
import routes from './routes';
import {createStore} from 'redux';
import {syncHistoryWithStore} from 'react-router-redux';
import MainReducer from './reducer/mainReducer.js';
import axios from 'axios';

const store = createStore(MainReducer);
//const unsubscribe = store.subscribe(() => console.log(store.getState()))

const history = syncHistoryWithStore(browserHistory, store);

axios.get('/img/home.jpg').then(response => console.log('home.jpg'));
axios.get('/img/about-us.jpg').then(response => console.log('about-us.jpg'));
axios.get('/img/gallery.jpg').then(response => console.log('gallery.jpg'));
axios.get('/img/products.jpg').then(response => console.log('products.jpg'));
axios.get('/img/contact.jpg').then(response => console.log('contact.jpg'));

ReactDOM.render(
    <Provider store={store}>
        <Router history={history} routes={routes}/>
    </Provider>,
    document.getElementById('root')
);
