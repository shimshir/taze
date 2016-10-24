import React, {Component} from 'react';
import {connect} from 'react-redux';
import Entry from './entry.js';

class EntriesView extends Component {
    componentWillMount() {
        console.log('cart entries mounted');
    }

    render() {
        return (
            <div className="cart-entries-container">
                 <ul className="list-group">
                     {this.props.entries.map(
                         entry =>
                             <li key={entry.id} className="list-group-item">
                                 <Entry entry={entry}/>
                             </li>
                     )}
                 </ul>
            </div>
        );
    }
}

EntriesView.propTypes = {
    entries: React.PropTypes.array.isRequired
};

const mapStateToProps = (state, ownProps) => {
    return {
        totalCartPrice: state.cart.entries ? state.cart.entries
            .map(entry => entry.totalPrice)
            .reduce((sum, totalEntryPrice) => sum + totalEntryPrice, 0) : undefined
    }
};

const Entries = connect(mapStateToProps, undefined)(EntriesView);
export default Entries;