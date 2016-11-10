import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';
import ProductDetailContent from './productDetailContent.js';
import Stage from '../../stage/stage.js';
import ContentContainer from '../../common/contentContainer.js';
import {asyncGetProductAction} from '../../../actions/actions.js';

class ProductDetailPageView extends Component {

    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products');
        if (!this.props.products.find(product => product.code == this.props.params.productCode))
            this.props.getProduct(this.props.params.productCode);
    }

    render() {
        const product = this.props.products.find(product => product.code == this.props.params.productCode);
        return (
            product ?
            <div>
                <Stage headerText={product.name} stageBackgroundClass={product.code}/>
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
        products: state.products
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
        ...mapDispatchToProps(dispatch, ownProps), ...changeActiveTopNavbarItemDispatchMapping(
            dispatch, ownProps)
    }
})(ProductDetailPageView);
export default ProductDetailPage;