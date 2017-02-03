import React, {Component} from 'react';
import {connect} from 'react-redux';

class AdminOrdersView extends Component {

    componentWillMount() {
    }

    render() {
        return (
            <div>
                {this.props.children}
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {}
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const AdminOrders = connect(mapStateToProps, mapDispatchToProps)(AdminOrdersView);
export default AdminOrders;
