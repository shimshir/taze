import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';
import Entries from './entries.js';
import {asyncGetCartAction} from '../../actions/actions.js';

class CartView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
        if (this.props.sessionUuid) {
            this.props.getCart(this.props.sessionUuid);
        }
    }

    render() {
        return (
            <div>
                <Stage headerText="Korpa" stageBackgroundClass="cart"/>
                <ContentContainer>
                    {this.props.cart.entries ?
                     <Entries entries={this.props.cart.entries}/>
                     : null
                    }
                </ContentContainer>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        sessionUuid: state.session.uuid,
        cart: state.cart
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getCart: (sessionUuid) => {
            asyncGetCartAction(dispatch, sessionUuid);
        }
    }
};

const Cart = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...changeActiveTopNavbarItemDispatchMapping(
            dispatch, ownProps)
    }
})(CartView);
export default Cart;