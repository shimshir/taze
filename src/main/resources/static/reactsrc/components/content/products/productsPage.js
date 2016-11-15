import React, {Component} from 'react';
import {connect} from 'react-redux';
import StageContainer from '../../stage/stageContainer.js';
import ContentContainer from '../../common/contentContainer.js';
import ProductCardDeck from './productCardDeck.js';
import {pageDispatchToPropsMappings} from '../../common/pageDispatchToPropsMappings.js';
import {asyncGetProductsAction} from '../../../actions/actions.js';

class ProductsPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products');
        this.props.getProducts();
        this.props.getPage(window.location.pathname);
    }

    render() {
        const page = this.props.pages[window.location.pathname];
        return (
            <div className="container">
                {page && <StageContainer page={page}/>}
                <ContentContainer>
                    <ProductCardDeck products={this.props.products}/>
                </ContentContainer>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        products: state.products,
        pages: state.pages
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getProducts: () => {
            asyncGetProductsAction(dispatch);
        }
    }
};

const ProductsPage = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...pageDispatchToPropsMappings(
            dispatch, ownProps)
    }
})(ProductsPageView);
export default ProductsPage;