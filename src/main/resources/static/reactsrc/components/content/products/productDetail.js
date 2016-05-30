import React, {Component} from 'react';
import Stage from '../../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {connect} from 'react-redux';

class ProductDetailView extends Component {
    amounts = new Array(30).fill(1).map((_, i) => i + 1);

    componentWillMount() {
        console.log('product detail');
    }

    render() {
        return (
            <div>
                <Stage headerText="Pilad" stageBackgroundClass="chicken"/>
                <ContentContainer>
                    <div className="row product-content-container">
                        <div className="col-lg-5">
                            <img className="primary-product-image" src="/img/products/chicken-pdp.jpg"/>
                        </div>
                        <div className="col-lg-7">
                            <form id="orderForm" onSubmit={(event) => {
                                event.preventDefault();
                                console.log("submitted");
                            }}>
                                <h2>Pile</h2>
                                <hr/>
                                <div className="control-container col-lg-6">
                                    <div className="row">
                                        <span className="col-lg-6">Cijena:</span>
                                        <span className="col-lg-6 price-value">{`8 KM/${this.props.unitCode}`}</span>
                                    </div>
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <label for="amount">Količina:</label>
                                        </div>
                                        <div className="col-lg-6">
                                            <select className="form-control amount-select" id="amount">
                                                {
                                                    this.amounts.map(amount => <option key={amount} value={amount}>{`${amount} ${this.props.unitCode}`}</option>)
                                                }
                                            </select>
                                            <small>* Kilaža se zaokružuje da bude cio broj piladi.</small>
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
                </ContentContainer>
            </div>
        );
    }
}

const ProductDetail = connect(undefined, undefined)(ProductDetailView);
export default ProductDetail;