import React, {Component} from "react";
import {connect} from "react-redux";
import ContentContainer from '../../common/contentContainer.js';
import {asyncGetOrdersAction} from '../../../actions/admin/actions.js';

class OrderSearchPageView extends Component {
    componentWillMount() {
        this.props.getOrders();
    }

    render() {
        // TODO: Use the orders in the table
        return (
            <ContentContainer>
                <table id="orderSearchTable" className="table table-bordered table-hover">

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
