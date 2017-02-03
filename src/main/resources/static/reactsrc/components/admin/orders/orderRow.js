import React, {Component} from "react";
import {connect} from "react-redux";

class OrderRowView extends Component {

    componentWillMount() {
    }

    render() {
        const order = this.props.order;
        console.log(order);
        return (
            <tr>
                <th scope="row">{order.id}</th>
                <td>{order.status}</td>
                <td>{order.token ? order.token.used.toString() : null}</td>
                <td>{order.pickupType}</td>
                <td>{order.clientOrderTimeString}</td>
                <td>{order.totalPrice}</td>
                <td>{order.entries.length}</td>
                <td>{order.customer ? order.customer.email : null}</td>
            </tr>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {}
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const OrderRow = connect(mapStateToProps, mapDispatchToProps)(OrderRowView);

OrderRow.propTypes = {
    order: React.PropTypes.object.isRequired
};

export default OrderRow;
