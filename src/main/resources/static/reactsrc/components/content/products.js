import React, { Component } from 'react';
import { connect } from 'react-redux';

class ProductsView extends Component {
    componentWillMount() {
        console.log("products mounted");
    }

    render() {
        return (<div><h1>Proizvodi</h1></div>);
    }
}

const Products = connect(undefined, undefined)(ProductsView);
export default Products;