import React from 'react';
import { Route, IndexRoute } from 'react-router';

import App from './components/app.js';
import Home from './components/content/home.js';
import AboutUs from './components/content/aboutUs.js';
import Gallery from './components/content/gallery.js';
import Products from './components/content/products.js';
import Contact from './components/content/contact.js';

const Root = () => {
    return (<div><h1>Root</h1></div>)
};

const NoMatch = () => {
    return (<div><h1>404</h1></div>)
};

export default (
    <Route path="/" components={App}>
        <IndexRoute component={Root}/>
        <Route path="/home" component={Home}/>
        <Route path="/about-us" component={AboutUs}/>
        <Route path="/gallery" component={Gallery}/>
        <Route path="/products" component={Products}/>
        <Route path="/contact" component={Contact}/>
        <Route path="*" component={NoMatch}/>
    </Route>
);
