import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPS } from '../../constants/constants.js';

class ProductsView extends Component {
    componentWillMount() {
        console.log("products mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Proizvodi" stageBackgroundClass="products"/>
                <ContentContainer content={LOREM_IPSUM_PARAGRAPS}/>
            </div>
        );
    }
}

const Products = connect(undefined, undefined)(ProductsView);
export default Products;