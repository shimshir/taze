import React, {Component} from 'react';
import {connect} from 'react-redux';

class CartItemsView extends Component {
    componentWillMount() {
        console.log('cartItems mounted');
    }
    render() {
        return (
            <div className="cart-items-container">
                <ul>
                    <li>cartItems</li>
                </ul>
            </div>
        );
    }
}

const CartItems = connect(undefined, undefined)(CartItemsView);
export default CartItems;