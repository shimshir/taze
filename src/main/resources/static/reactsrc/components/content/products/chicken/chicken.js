import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';
import Stage from '../../../stage/stage.js';
import ContentContainer from '../../common/contentContainer.js';

class ChickenView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products')
    }
    render() {
        const amounts = new Array(30).fill(1).map((_, i) => i + 1);
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
                                        <span className="col-lg-6 price-value">8 KM/kg</span>
                                    </div>
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <label for="amount">Količina:</label>
                                        </div>
                                        <div className="col-lg-6">
                                            <select className="form-control amount-select" id="amount">
                                                {
                                                    amounts.map(amount => <option key={amount} value={amount}>{amount} kg</option>)
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


const Chicken = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(ChickenView);
export default Chicken;