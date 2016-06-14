import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../../stage/stage.js';
import ContentContainer from '../../common/contentContainer.js';
import ProductCardDeck from './productCardDeck.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';

class ProductsView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products');
    }

    render() {
        return (
            <div>
                <Stage headerText="Proizvodi" stageBackgroundClass="products"/>
                <ContentContainer>
                    <ProductCardDeck />
                </ContentContainer>
            </div>
        );
    }
}

const Products = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(ProductsView);
export default Products;