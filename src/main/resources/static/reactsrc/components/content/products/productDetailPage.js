import React, {Component} from 'react';
import {connect} from 'react-redux';
import {pageDispatchToPropsMappings} from '../../common/pageDispatchToPropsMappings.js';
import ProductDetailContent from './productDetailContent.js';
import StageContainer from '../../stage/stageContainer.js';
import ContentContainer from '../../common/contentContainer.js';
import {asyncGetProductAction} from '../../../actions/actions.js';

class ProductDetailPageView extends Component {

    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products');
        if (!this.props.products.find(product => product.code == this.props.params.productCode))
            this.props.getProduct(this.props.params.productCode);
        this.props.getPage(window.location.pathname);
    }

    render() {
        const product = this.props.products.find(product => product.code == this.props.params.productCode);
        const page = this.props.pages[window.location.pathname];
        return (
            product ?
            <div>
                {page && <StageContainer page={page}/>}
                <ContentContainer>
                    <ProductDetailContent product={product}/>
                </ContentContainer>

            </div>
                :
            null
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
        getProduct: (productCode) => {
            asyncGetProductAction(dispatch, productCode);
        }
    };
};

const ProductDetailPage = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...pageDispatchToPropsMappings(
            dispatch, ownProps)
    }
})(ProductDetailPageView);
export default ProductDetailPage;