import React, {Component} from "react";
import {connect} from "react-redux";
import Stage from "../stage/stage.js";
import ContentContainer from "../common/contentContainer.js";
import {changeActiveTopNavbarItemDispatchMapping} from "../common/commonMappings.js";
import Entries from "./entries.js";
import {asyncGetCartAction} from "../../actions/actions.js";
import PlaceOrderDialog from "./placeOrderDialog.js";
import ConfirmedOrderDialog from "./confirmedOrderDialog.js";

class CartView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
        if (this.props.session.id) {
            this.props.getCart(this.props.session);
        }
    }

    render() {
        return (
            <div>
                <Stage headerText="Korpa" stageBackgroundClass="cart"/>
                <ContentContainer>
                    {(this.props.cart.entries && this.props.cart.entries.length != 0) ?
                     <div>
                         <Entries entries={this.props.cart.entries}/>
                         <div className="cart-summary">
                             <b className="text-uppercase">Ukupna cijena: </b>
                             <b className="price-value">{`${this.props.cart.totalPrice} KM`}</b>
                             <br/><br/>
                             <PlaceOrderDialog/>
                         </div>
                     </div> :
                     <div className="cart-summary">
                         <b className="text-uppercase">Korpa je prazna</b>
                     </div>
                    }
                    <ConfirmedOrderDialog/>
                </ContentContainer>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        session: state.session,
        cart: state.cart
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getCart: (session) => asyncGetCartAction(dispatch, session)
    }
};

const Cart = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        ...mapDispatchToProps(dispatch, ownProps), ...changeActiveTopNavbarItemDispatchMapping(
            dispatch, ownProps)
    }
})(CartView);
export default Cart;