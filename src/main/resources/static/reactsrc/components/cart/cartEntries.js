import React, {Component} from 'react';
import {connect} from 'react-redux';
import {removeFromCartAction} from '../../actions/actions.js'

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
                    <ul>
                        {
                            this.props.entries.map((cartEntry, index) =>
                                <li key={index}>
                                    <div>
                                        <span>{cartEntry.productCode}, {cartEntry.amount}</span>
                                        <button onClick={() => this.removeCartEntry(index)}>Izbaci</button>
                                    </div>
                                </li>
                            )
                        }
                    </ul>
                    : null
                }
            </div>
        );
    }
}

CartEntriesView.propTypes = {
    entries: React.PropTypes.array
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        removeFromCart: (cartEntryIndex) => {
            dispatch(removeFromCartAction(cartEntryIndex));
        }
    }  
};

const CartEntries = connect(undefined, mapDispatchToProps)(CartEntriesView);
export default CartEntries;