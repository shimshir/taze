import React from 'react';
import {connect} from 'react-redux';
import {updateCartEntryAmountAction} from '../../actions/actions.js'
import AmountSelect from '../common/amountSelect.js';
import {Link} from 'react-router';

const CartEntryView = ({ entry, index, updateEntryAmount }) => {
    return (
        <div className="row">
            <div className="col-lg-2">
                <Link to={`/products/${entry.product.code}`}><img src={entry.product.listImage}/></Link>
            </div>
            <div className="col-lg-10">
                <div className="row">
                    <div className="col-lg-4">
                        <h2>{entry.product.name}</h2>
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
                        <small>{entry.product.footnote}</small>
                        <br/>
                        <span className="pseudo-anchor" onClick={() => this.removeCartEntry(index)}>Izbaci</span>
                    </div>
                    <div className="col-lg-4">
                        <span className="price-value"><b>{entry.product.pricePerUnit * entry.amount} KM</b></span>
                    </div>
                    <div className="col-lg-4">
                        <AmountSelect id="amount"
                                      amounts={new Array(30).fill(1).map((_, i) => i + 1)}
                                      unitCode={entry.product.unitCode}
                                      defaultValue={entry.amount}
                                      onChange={(event) => updateEntryAmount(index, event.target.value)}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

CartEntryView.PropTypes = {
    entry: React.PropTypes.object.isRequired,
    index: React.PropTypes.number.isRequired
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updateEntryAmount: (cartEntryIndex, amount) => {
            dispatch(updateCartEntryAmountAction(cartEntryIndex, amount));
        }
    }
};

const CartEntry = connect(undefined, mapDispatchToProps)(CartEntryView);
export default CartEntry;