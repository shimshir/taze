import React, {Component} from 'react';
import {connect} from 'react-redux';
import {asyncAddToCartAction} from '../../../actions/actions.js';
import AmountSelect from '../../common/amountSelect.js';

class ProductDetailView extends Component {
    amounts = new Array(30).fill(1).map((_, i) => i + 1);

    state = {
        totalPrice: this.props.product.pricePerUnit,
        entry: {
            product: this.props.product,
            amount: 1
        }
    };
    
    handleAmountChange = (event) => {
        this.setState({
            totalPrice: this.props.product.pricePerUnit * event.target.value,
            entry: {
                product: this.props.product,
                amount: event.target.value
            }
        });
    };

    handleSubmit = (event) => {
        event.preventDefault();
        this.props.addToCart(this.props.cartId, this.state.entry);
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
                                <span className="col-lg-6 no-padding">{`${this.props.product.pricePerUnit} KM/${this.props.product.unitCode}`}</span>
                            </div>
                            <div className="row">
                                <div className="col-lg-6 no-padding">
                                    <label for="amount">Količina:</label>
                                </div>
                                <div className="col-lg-6 no-padding">
                                    <AmountSelect id="amount"
                                                  onChange={this.handleAmountChange}
                                                  amounts={this.amounts}
                                                  unitCode={this.props.product.unitCode}
                                    />
                                    <small>{this.props.product.footnote}</small>
                                </div>
                                <div className="col-lg-12 no-padding total-price">
                                    <span className="col-lg-6 no-padding">Ukupna cijena:</span>
                                    <span className="col-lg-6 no-padding price-value"><b>{this.state.totalPrice + ' KM'}</b></span>
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
    imageSrc: React.PropTypes.string.isRequired
};

const mapStateToProps = (state, ownProps) => {
    return {
        cartId: state.cart.id
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        addToCart: (cartId, entry) => {
            asyncAddToCartAction(dispatch, cartId, entry);
        }
    }
};

const ProductDetail = connect(mapStateToProps, mapDispatchToProps)(ProductDetailView);
export default ProductDetail;