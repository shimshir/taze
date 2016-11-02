import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {asyncConfirmOrderAction} from '../../actions/actions.js';

class ConfirmedOrderView extends Component {
    componentWillMount() {
        this.props.confirmOrder(this.props.location.query.orderId, this.props.location.query.token)
    }

    render() {
        return (
            <div>
                <Stage headerText="Order Confirmed" stageBackgroundClass="confirmed-order"/>
                <ContentContainer>
                </ContentContainer>
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        confirmOrder: (orderId, token) => {
            asyncConfirmOrderAction(dispatch, orderId, token);
        }
    }
};

const ConfirmedOrder = connect(undefined, mapDispatchToProps)(ConfirmedOrderView);
export default ConfirmedOrder;