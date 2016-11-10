import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {asyncConfirmOrderAction} from '../../actions/actions.js';

class ConfirmedOrderPageView extends Component {
    componentWillMount() {
        this.props.confirmOrder(this.props.location.query.orderId, this.props.location.query.token)
    }

    render() {
        const confirmationResult = this.props.confirmationResults[this.props.location.query.orderId];
        return (
            confirmationResult ?
            <div>
                <Stage headerText="Order Confirmed" stageBackgroundClass="confirmed-order"/>
                <ContentContainer>
                    {confirmationResult.message}
                </ContentContainer>
            </div> : null
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        confirmationResults: state.orderConfirmationResults
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        confirmOrder: (orderId, token) => {
            asyncConfirmOrderAction(dispatch, orderId, token);
        }
    }
};

const ConfirmedOrderPage = connect(mapStateToProps, mapDispatchToProps)(ConfirmedOrderPageView);
export default ConfirmedOrderPage;