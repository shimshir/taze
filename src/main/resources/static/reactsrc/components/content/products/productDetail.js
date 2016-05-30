import React, {Component} from 'react';
import {connect} from 'react-redux';

class ProductDetailView extends Component {
    amounts = new Array(30).fill(1).map((_, i) => i + 1);

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
                                    pricePerUnit: this.props.pricePerUnit,
                                    amount: this.refs.amountSelect.value
                                };
                                console.log(orderEntry);
                            }}>
                        <h2>{this.props.headerText}</h2>
                        <hr/>
                        <div className="control-container col-lg-6">
                            <div className="row">
                                <span className="col-lg-6">Cijena:</span>
                                <span className="col-lg-6 price-value">{`${this.props.pricePerUnit} KM/${this.props.unitCode}`}</span>
                            </div>
                            <div className="row">
                                <div className="col-lg-6">
                                    <label for="amount">Količina:</label>
                                </div>
                                <div className="col-lg-6">
                                    <select className="form-control amount-select" id="amount" ref="amountSelect">
                                        {
                                            this.amounts.map(amount => <option key={amount}
                                                                               value={amount}>
                                                {`${amount} ${this.props.unitCode}`}
                                            </option>)
                                        }
                                    </select>
                                    <small>{this.props.additionalText}</small>
                                </div>
                            </div>
                        </div>
                        <div className="control-container col-lg-6">
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