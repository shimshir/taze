import React, {Component} from 'react';
import {connect} from 'react-redux';
import StageContainer from '../stage/stageContainer.js';
import ContentContainer from '../common/contentContainer.js';
import {asyncConfirmOrderAction} from '../../actions/actions.js';
import {pageDispatchToPropsMappings} from '../common/pageDispatchToPropsMappings.js';

class ConfirmedOrderPageView extends Component {
    componentWillMount() {
        this.props.confirmOrder(this.props.location.query.orderId, this.props.location.query.token);
        this.props.getPage('confirmed-order');
    }

    render() {
        const confirmationResult = this.props.confirmationResults[this.props.location.query.orderId];
        return (
            confirmationResult ?
            <div>
                {this.props.page && <StageContainer page={this.props.page}/>}
                <ContentContainer>
                    {confirmationResult.message}
                </ContentContainer>
            </div> : null
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        confirmationResults: state.orderConfirmationResults,
        page: state.pages['confirmed-order']
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        confirmOrder: (orderId, token) => {
            asyncConfirmOrderAction(dispatch, orderId, token);
        }
    }
};

const ConfirmedOrderPage = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...pageDispatchToPropsMappings(
            dispatch, ownProps)
    }
})(ConfirmedOrderPageView);
export default ConfirmedOrderPage;