import React, {Component} from "react";
import {connect} from "react-redux";
import StageContainer from '../stage/stageContainer.js';
import ContentContainer from "../common/contentContainer.js";
import {pageDispatchToPropsMappings} from "../common/pageDispatchToPropsMappings.js";
import Entries from "./entries.js";
import {asyncGetCartAction} from "../../actions/actions.js";
import PlaceOrderDialog from "./placeOrderDialog.js";
import ConfirmedOrderDialog from "./confirmedOrderDialog.js";
import Price from "../common/price.js";
import {DELIVERY_OPTIONS} from "../../constants/constants.js";
import {asyncChangePickupTypeAction} from "../../actions/actions.js";
import SelectInput from "../common/selectInput.js";

class CartPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('cart');
        if (this.props.session.uuid) {
            this.props.getCart(this.props.session)
                .then(cart => this.props.changePickupType(cart.id, 'COLLECT'));
        }
        this.props.getPage(window.location.pathname);
    }

    render() {
        const page = this.props.pages[window.location.pathname];
        return (
            <div className="container">
                {page && <StageContainer page={page}/>}
                <ContentContainer>
                    {(this.props.cart.entries && this.props.cart.entries.length != 0) ?
                     <div>
                         <Entries entries={this.props.cart.entries}/>
                         <div className="cart-summary row">
                             <div className="cart-summary-left col-lg-6">
                                 <SelectInput id="pickupType"
                                              label="Preuzimanje"
                                              defaultValue={this.props.cart.pickupType}
                                              options={DELIVERY_OPTIONS}
                                              onChange={(event) => this.props.changePickupType(this.props.cart.id, event.target.value)}/>
                             </div>
                             <div className="cart-summary-right col-lg-6">
                                 <b className="text-uppercase">Ukupna cijena: </b>
                                 <b className="price-value"><Price value={this.props.cart.totalPrice}/></b>
                                 <br/><br/>
                                 <PlaceOrderDialog/>
                             </div>
                         </div>
                     </div> :
                     <div className="cart-summary">
                         <b className="text-uppercase">Korpa je prazna</b>
                     </div>
                    }
                    <ConfirmedOrderDialog/>
                </ContentContainer>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        session: state.session,
        cart: state.cart,
        pages: state.pages
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getCart: (session) => asyncGetCartAction(dispatch, session)
    }
};

const CartPage = connect(mapStateToProps, (dispatch, ownProps) => {
    return {
        changePickupType: (cartId, pickupType) => asyncChangePickupTypeAction(dispatch, cartId, pickupType),
        ...mapDispatchToProps(dispatch, ownProps), ...pageDispatchToPropsMappings(
            dispatch, ownProps)
    }
})(CartPageView);
export default CartPage;