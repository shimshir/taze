import React, {Component} from 'react';
import {connect} from 'react-redux';
import {removeFromCartAction} from '../../actions/actions.js'
import AmountSelect from '../common/amountSelect.js';

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
                <div></div>
                {this.props.entries ? 
                    <ul className="list-group">
                        {this.props.entries.map((cartEntry, index) =>
                            <li key={index} className="list-group-item">
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
                                                <span className="pseudo-anchor" onClick={() => this.removeCartEntry(index)}>Izbaci</span>
                                            </div>
                                            <div className="col-lg-4">
                                                <span>{cartEntry.product.pricePerUnit * cartEntry.amount} KM</span>
                                            </div>
                                            <div className="col-lg-4">
                                                <AmountSelect id="amount"
                                                              amounts={new Array(30).fill(1).map((_, i) => i + 1)}
                                                              unitCode={cartEntry.product.unitCode}
                                                              defaultSelected={(amount) => cartEntry.amount === amount}
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
        }
    }  
};

const CartEntries = connect(undefined, mapDispatchToProps)(CartEntriesView);
export default CartEntries;