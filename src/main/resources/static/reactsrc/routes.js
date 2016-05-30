import React from 'react';
import { Route, IndexRoute } from 'react-router';

import App from './components/app.js';
import Root from './components/content/root.js';
import AboutUs from './components/content/aboutUs.js';
import Gallery from './components/content/gallery.js';
import Products from './components/content/products/products.js';
import Contact from './components/content/contact.js';


const NoMatch = () => {
    return (<div><h1>404</h1></div>)
};

export default (
    <Route path="/" components={App}>
        <IndexRoute component={Root}/>
        <Route path="/about-us" component={AboutUs}/>
        <Route path="/gallery" component={Gallery}/>
        <Route path="/products" component={Products}/>
        <Route path="/contact" component={Contact}/>
        <Route path="*" component={NoMatch}/>
    </Route>
);
