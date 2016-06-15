import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';
import CartEntries from './cartEntries.js';

class CartView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
        //TODO: Fetch cart from backend
    }

    render() {
        return (
            <div>
                <Stage headerText="Korpa" stageBackgroundClass="cart"/>
                <ContentContainer>
                    <CartEntries entries={this.props.cart.cartEntries}/>
                </ContentContainer>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        cart: state.cart
    }
};

const Cart = connect(mapStateToProps, changeActiveTopNavbarItemDispatchMapping)(CartView);
export default Cart;