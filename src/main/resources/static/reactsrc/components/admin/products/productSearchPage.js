import React, {Component} from "react";
import {connect} from "react-redux";
import ContentContainer from '../../common/contentContainer.js';
import {asyncGetProductsAction} from '../../../actions/actions.js';
import ProductRow from './productRow.js';

class ProductSearchPageView extends Component {
    componentWillMount() {
        this.props.getProducts();
    }

    render() {
        return (
            <ContentContainer>
                <table id="productsSearchTable" className="table table-bordered table-hover">
                    <thead className="thead-inverse">
                    <tr>
                        <th>Id</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>List image</th>
                        <th>Pdp image</th>
                        <th>Price per unit</th>
                        <th>Unit code</th>
                        <th>Footnote</th>
                        <th>Product Card</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.products.map(product => <ProductRow key={product.code} product={product}/>)
                    }
                    </tbody>
                </table>
            </ContentContainer>
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

const ProductSearchPage = connect(mapStateToProps, mapDispatchToProps)(ProductSearchPageView);
export default ProductSearchPage;
