import React, {Component} from 'react';
import {connect} from 'react-redux';
import {removeFromCartAction, updateCartEntryAmountAction} from '../../actions/actions.js'
import AmountSelect from '../common/amountSelect.js';

class CartEntriesView extends Component {
    componentWillMount() {
        console.log('cartEntries mounted');
    }

    removeCartEntry = (index) => {
        this.props.removeFromCart(index);
    };
    
    updateEntryAmount = (index, amount) => {
        this.props.updateEntryAmount(index, amount);
    };
    
    render() {
        return (
            <div className="cart-entries-container">
                <div></div>
                {this.props.entries ? 
                    <ul className="list-group">
                        {this.props.entries.map((cartEntry, ceIndex) =>
                            <li key={ceIndex} className="list-group-item">
                                <div className="row">
                                    <div className="col-lg-2">
                                        <img src={cartEntry.product.listImage}/>
                                    </div>
                                    <div className="col-lg-10">
                                        <div className="row">
                                            <div className="col-lg-4">
                                                <h2>{cartEntry.product.name}</h2>
                                            </div>
                                            <div className="col-lg-4">
                                                <label>Ukupna cijena</label>
                                            </div>
                                            <div className="col-lg-4">
                                                Količina
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-lg-4">
                                                <span className="pseudo-anchor" onClick={() => this.removeCartEntry(ceIndex)}>Izbaci</span>
                                            </div>
                                            <div className="col-lg-4">
                                                <span className="price-value"><b>{cartEntry.product.pricePerUnit * cartEntry.amount} KM</b></span>
                                            </div>
                                            <div className="col-lg-4">
                                                <AmountSelect id="amount"
                                                              amounts={new Array(30).fill(1).map((_, i) => i + 1)}
                                                              unitCode={cartEntry.product.unitCode}
                                                              defaultValue={cartEntry.amount}
                                                              onChange={(event) => this.updateEntryAmount(ceIndex, event.target.value)}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        )}
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
        },
        updateEntryAmount: (cartEntryIndex, amount) => {
            dispatch(updateCartEntryAmountAction(cartEntryIndex, amount));
        }
    }  
};

const CartEntries = connect(undefined, mapDispatchToProps)(CartEntriesView);
export default CartEntries;