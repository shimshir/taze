import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';

class ProductsView extends Component {
    componentWillMount() {
        console.log("products mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Proizvodi" stageBackgroundClass="products"/>
            </div>
        );
    }
}

const Products = connect(undefined, undefined)(ProductsView);
export default Products;