import React, {Component} from 'react';
import {connect} from 'react-redux';
import {addToCartAction} from '../../../actions/actions.js';
import AmountSelect from '../../common/amountSelect.js';

class ProductDetailView extends Component {
    amounts = new Array(30).fill(1).map((_, i) => i + 1);

    state = {totalPrice: this.props.product.pricePerUnit};
    
    updateTotalPrice = (event) => {
        this.setState({totalPrice: this.props.product.pricePerUnit * event.target.value});
    };

    handleSubmit = (event) => {
        event.preventDefault();
        const cartEntry = {
            product: this.props.product,
            amount: this.refs.amountSelect.value
        };
        this.props.addToCart(cartEntry);
    };

    componentWillMount() {
    }

    render() {
        return (
            <div className="row product-content-container">
                <div className="col-lg-5">
                    <img className="primary-product-image" src={this.props.product.pdpImage}/>
                </div>
                <div className="col-lg-7">
                    <form id="orderForm" onSubmit={event => this.handleSubmit(event)}>
                        <h2>{this.props.product.name}</h2>
                        <hr/>
                        <div className="control-container col-lg-8 no-padding">
                            <div className="row">
                                <span className="col-lg-6 no-padding">Cijena:</span>
                                <span className="col-lg-6 no-padding price-value">{`${this.props.product.pricePerUnit} KM/${this.props.product.unitCode}`}</span>
                            </div>
                            <div className="row">
                                <div className="col-lg-6 no-padding">
                                    <label for="amount">Količina:</label>
                                </div>
                                <div className="col-lg-6 no-padding">
                                    <AmountSelect id="amount"
                                                  onChange={this.updateTotalPrice}
                                                  amounts={this.amounts}
                                                  unitCode={this.props.product.unitCode}
                                    />
                                    <small>{this.props.additionalText}</small>
                                </div>
                                <div className="col-lg-12 no-padding total-price">
                                    <span className="col-lg-6 no-padding">Ukupna cijena:</span>
                                    <span className="col-lg-6 no-padding"><b>{this.state.totalPrice + ' KM'}</b></span>
                                </div>
                            </div>
                        </div>
                        <div className="control-container col-lg-4 no-padding">
                            <div className="row">
                                <button type="submit" className="btn btn-success">
                                    <i className="fa fa-cart-plus"/> Dodaj u korpu
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

ProductDetailView.propTypes = {
    product: React.PropTypes.object.isRequired,
    imageSrc: React.PropTypes.string.isRequired,
    additionalText: React.PropTypes.string
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        addToCart: (cartEntry) => {
            dispatch(addToCartAction(cartEntry));
        }
    }
};

const ProductDetail = connect(undefined, mapDispatchToProps)(ProductDetailView);
export default ProductDetail;