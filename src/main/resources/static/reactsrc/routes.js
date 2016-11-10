import React from 'react';
import {Route, IndexRoute} from 'react-router';

import App from './components/app.js';
import RootPage from './components/content/rootPage.js';
import AboutUsPage from './components/content/aboutUsPage.js';
import GalleryPage from './components/content/galleryPage.js';
import ProductsPage from './components/content/products/productsPage.js';
import ContactPage from './components/content/contactPage.js';
import CartPage from './components/cart/cartPage.js';
import ProductDetailPage from './components/content/products/productDetailPage.js';
import ConfirmedOrderPage from './components/content/confirmedOrderPage.js';
import ProductCreatePage from './components/admin/products/productCreatePage.js';

const NoMatch = () => {
    return (<div><h1>404</h1></div>)
};

export default (
    <Route path="/" components={App}>
        <IndexRoute component={RootPage}/>
        <Route path="/about-us" component={AboutUsPage}/>
        <Route path="/gallery" component={GalleryPage}/>
        <Route path="/products" component={ProductsPage}/>
        <Route path="/products/:productCode" component={ProductDetailPage}/>
        <Route path="/contact" component={ContactPage}/>
        <Route path="/cart" component={CartPage}/>
        <Route path="/confirmed-order" component={ConfirmedOrderPage}/>
        <Route path="/admin/products/create" component={ProductCreatePage}/>
        <Route path="*" component={NoMatch}/>
    </Route>
);
