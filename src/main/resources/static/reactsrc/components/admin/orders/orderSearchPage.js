import React, {Component} from "react";
import {connect} from "react-redux";
import ContentContainer from '../../common/contentContainer.js';
import {asyncGetOrdersAction} from '../../../actions/admin/actions.js';
import OrderRow from './orderRow.js';

class OrderSearchPageView extends Component {
    componentWillMount() {
        this.props.getOrders();
    }

    render() {
        return (
            <ContentContainer>
                <table id="orderSearchTable" className="table table-bordered table-hover">
                    <thead className="thead-inverse">
                    <tr>
                        <th>Id</th>
                        <th>Status</th>
                        <th>Token used</th>
                        <th>Pickup Type</th>
                        <th>Client time of ordering</th>
                        <th>Total Price</th>
                        <th>Entries</th>
                        <th>Customer</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.orders.map(order => <OrderRow key={order.id} order={order}/>)
                    }
                    </tbody>
                </table>
            </ContentContainer>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        orders: state.orders
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getOrders: () => {
            asyncGetOrdersAction(dispatch);
        }
    }
};

const OrderSearchPage = connect(mapStateToProps, mapDispatchToProps)(OrderSearchPageView);
export default OrderSearchPage;
