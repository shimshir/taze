import React, {Component} from 'react';
import {connect} from 'react-redux';
import Entry from './entry.js';
import PlaceOrderDialog from './placeOrderDialog.js';

class EntriesView extends Component {
    componentWillMount() {
        console.log('cart entries mounted');
    }
    
    render() {
        return (
            <div className="cart-entries-container">
                {this.props.entries ? 
                    <ul className="list-group">
                        {this.props.entries.map(entry =>
                            <li key={entry.id} className="list-group-item">
                                <Entry entry={entry} />
                            </li>
                        )}
                    </ul>
                    : null
                }
                <div className="cart-summary">
                    <b className="text-uppercase">Ukupna cijena: </b>
                    <b className="price-value">{`${this.props.totalCartPrice} KM`}</b>
                    <br/><br/>
                    <PlaceOrderDialog/>
                </div>
            </div>
        );
    }
}

EntriesView.propTypes = {
    entries: React.PropTypes.array.isRequired
};

const mapStateToProps = (state, ownProps) => {
    return {
        totalCartPrice: state.cart.entries
            .map(entry => entry.totalPrice)
            .reduce((sum, totalEntryPrice) => sum + totalEntryPrice, 0)
    }
};

const Entries = connect(mapStateToProps, undefined)(EntriesView);
export default Entries;