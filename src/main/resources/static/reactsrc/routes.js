import React from 'react';
import {Route, IndexRoute} from 'react-router';

import App from './components/app.js';
import Root from './components/content/root.js';
import AboutUs from './components/content/aboutUs.js';
import Gallery from './components/content/gallery.js';
import Products from './components/content/products/products.js';
import Contact from './components/content/contact.js';
import Cart from './components/cart/cart.js';
import ProductDetail from './components/content/products/productDetail';
import ConfirmedOrder from './components/content/confirmedOrder.js';
import ProductCreate from './components/admin/products/productCreate.js';

const NoMatch = () => {
    return (<div><h1>404</h1></div>)
};

export default (
    <Route path="/" components={App}>
        <IndexRoute component={Root}/>
        <Route path="/about-us" component={AboutUs}/>
        <Route path="/gallery" component={Gallery}/>
        <Route path="/products" component={Products}/>
        <Route path="/products/:productCode" component={ProductDetail}/>
        <Route path="/contact" component={Contact}/>
        <Route path="/cart" component={Cart}/>
        <Route path="/confirmedOrder" component={ConfirmedOrder}/>
        <Route path="/admin/products/create" component={ProductCreate}/>
        <Route path="*" component={NoMatch}/>
    </Route>
);
