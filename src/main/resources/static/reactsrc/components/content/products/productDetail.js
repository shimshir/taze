import React, {Component} from 'react';
import {connect} from 'react-redux';

class ProductDetailView extends Component {
    amounts = new Array(30).fill(1).map((_, i) => i + 1);

    state = {totalPrice: this.props.pricePerUnit};

    componentWillMount() {
    }

    render() {
        return (
            <div className="row product-content-container">
                <div className="col-lg-5">
                    <img className="primary-product-image" src={this.props.imageSrc}/>
                </div>
                <div className="col-lg-7">
                    <form id="orderForm" onSubmit={(event) => {
                                event.preventDefault();
                                const orderEntry = {
                                    productCode: this.props.productCode,
                                    amount: this.refs.amountSelect.value
                                };
                                console.log(orderEntry);
                            }}>
                        <h2>{this.props.headerText}</h2>
                        <hr/>
                        <div className="control-container col-lg-8 no-padding">
                            <div className="row">
                                <span className="col-lg-6 no-padding">Cijena:</span>
                                <span className="col-lg-6 no-padding price-value">{`${this.props.pricePerUnit} KM/${this.props.unitCode}`}</span>
                            </div>
                            <div className="row">
                                <div className="col-lg-6 no-padding">
                                    <label for="amount">Količina:</label>
                                </div>
                                <div className="col-lg-6 no-padding">
                                    <select className="form-control amount-select"
                                            id="amount"
                                            ref="amountSelect"
                                            onChange={() => {
                                                this.setState({totalPrice: this.props.pricePerUnit * this.refs.amountSelect.value});
                                            }}>
                                        {
                                            this.amounts.map(amount => <option key={amount}
                                                                               value={amount}>
                                                {`${amount} ${this.props.unitCode}`}
                                            </option>)
                                        }
                                    </select>
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
    productCode: React.PropTypes.string.isRequired,
    pricePerUnit: React.PropTypes.number.isRequired,
    unitCode: React.PropTypes.string.isRequired,
    imageSrc: React.PropTypes.string.isRequired,
    headerText: React.PropTypes.string.isRequired,
    additionalText: React.PropTypes.string
};

const ProductDetail = connect(undefined, undefined)(ProductDetailView);
export default ProductDetail;