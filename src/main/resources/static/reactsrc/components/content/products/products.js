import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../../stage/stage.js';
import ContentContainer from '../../common/contentContainer.js';
import ProductCardDeck from './productCardDeck.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';
import {asyncGetProductsAction} from '../../../actions/actions.js';

class ProductsView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products');
        this.props.getProducts();
    }

    render() {
        return (
            <div>
                <Stage headerText="Proizvodi" stageBackgroundClass="products"/>
                <ContentContainer>
                    <ProductCardDeck products={this.props.products}/>
                </ContentContainer>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        products: state.products
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getProducts: () => {
            asyncGetProductsAction(dispatch);
        }
    }
};

const Products = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...changeActiveTopNavbarItemDispatchMapping(
            dispatch, ownProps)
    }
})(ProductsView);
export default Products;