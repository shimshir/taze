import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';
import CartItems from './cartItems.js';

class CartView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
    }

    render() {
        return (
            <div>
                <Stage headerText="Korpa" stageBackgroundClass="cart"/>
                <ContentContainer>
                    <CartItems/>
                </ContentContainer>
            </div>
        );
    }
}

const Cart = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(CartView);
export default Cart;