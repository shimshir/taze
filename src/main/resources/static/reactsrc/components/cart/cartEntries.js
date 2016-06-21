import React, {Component} from 'react';
import {connect} from 'react-redux';
import {removeFromCartAction} from '../../actions/actions.js'
import CartEntry from './cartEntry.js';

class CartEntriesView extends Component {
    componentWillMount() {
        console.log('cartEntries mounted');
    }

    removeCartEntry = (index) => {
        this.props.removeFromCart(index);
    };
    
    render() {
        return (
            <div className="cart-entries-container">
                {this.props.entries ? 
                    <ul className="list-group">
                        {this.props.entries.map((cartEntry, ceIndex) =>
                            <li key={ceIndex} className="list-group-item">
                                <CartEntry entry={cartEntry} index={ceIndex}/>
                            </li>
                        )}
                    </ul>
                    : null
                }
                <div className="cart-summary">
                    <b className="text-uppercase">Ukupna suma: </b>
                    <b className="price-value">{`${this.props.totalCartValue} KM`}</b>
                    <br/><br/>
                    <button className="btn btn-success">Naruci</button>
                </div>
            </div>
        );
    }
}

CartEntriesView.propTypes = {
    entries: React.PropTypes.array
};

const mapStateToProps = (state, ownProps) => {
    return {
        totalCartValue: state.cart.cartEntries.map(entry => entry.amount * entry.product.pricePerUnit).reduce((sum, priceValue) => sum + priceValue, 0)
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        removeFromCart: (cartEntryIndex) => {
            dispatch(removeFromCartAction(cartEntryIndex));
        }
    }
};

const CartEntries = connect(mapStateToProps, mapDispatchToProps)(CartEntriesView);
export default CartEntries;