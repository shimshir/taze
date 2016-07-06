import React from 'react';
import {connect} from 'react-redux';
import {asyncRemoveCartEntryAction, updateCartEntryAmountAction} from '../../actions/actions.js'
import AmountSelect from '../common/amountSelect.js';
import {Link} from 'react-router';

const EntryView = ({ cart, entry, removeCartEntry, updateEntryAmount }) => {
    return (
        <div className="row">
            <div className="col-lg-2">
                <Link to={`/products/${entry.product.code}`}><img src={entry.product.listImage}/></Link>
            </div>
            <div className="col-lg-10">
                <div className="row">
                    <div className="col-lg-4">
                        <h4>{entry.product.name}</h4>
                    </div>
                    <div className="col-lg-4">
                        <label>Cijena</label>
                    </div>
                    <div className="col-lg-4">
                        Količina
                    </div>
                </div>
                <div className="row">
                    <div className="col-lg-4">
                        <small>{entry.product.footnote}</small>
                        <br/>
                        <span className="pseudo-anchor" onClick={() => removeCartEntry(cart.id, entry.id)}>Izbaci</span>
                    </div>
                    <div className="col-lg-4">
                        <span className="price-value"><b>{entry.product.pricePerUnit * entry.amount} KM</b></span>
                    </div>
                    <div className="col-lg-4">
                        <AmountSelect id="amount"
                                      amounts={new Array(30).fill(1).map((_, i) => i + 1)}
                                      unitCode={entry.product.unitCode}
                                      selectedValue={entry.amount}
                                      onChange={(event) => updateEntryAmount(entry.id, event.target.value)}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

EntryView.PropTypes = {
    entry: React.PropTypes.object.isRequired
};

const mapStateToProps = (state, ownProps) => {
    return {
        cart: state.cart
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        removeCartEntry: (cartId, entryId) => {
            asyncRemoveCartEntryAction(dispatch, cartId, entryId);
        },
        updateEntryAmount: (entryId, amount) => {
            dispatch(updateCartEntryAmountAction(entryId, amount));
        }
    }
};

const Entry = connect(mapStateToProps, mapDispatchToProps)(EntryView);
export default Entry;