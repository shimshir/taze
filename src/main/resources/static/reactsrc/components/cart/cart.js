import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';
import Entries from './entries.js';
import {asyncGetCartAction} from '../../actions/actions.js';

class CartView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
        if (this.props.sessionId)
            this.props.getCart(this.props.sessionId);
    }

    render() {
        return (
            <div>
                <Stage headerText="Korpa" stageBackgroundClass="cart"/>
                <ContentContainer>
                    <Entries entries={this.props.cart.entries}/>
                </ContentContainer>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        sessionId: state.session.id,
        cart: state.cart
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getCart: (sessionId) => {
            asyncGetCartAction(dispatch, sessionId);
        }
    }
};
console.log({...changeActiveTopNavbarItemDispatchMapping});
const Cart = connect(mapStateToProps, (dispatch, ownProps) => {
    return {...mapDispatchToProps(dispatch, ownProps), ...changeActiveTopNavbarItemDispatchMapping(dispatch, ownProps)}
})(CartView);
export default Cart;