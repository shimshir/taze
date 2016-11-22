import React, {Component} from "react";
import Modal from "react-modal";
import {connect} from "react-redux";
import {
    updatePlaceOrderFormAction,
    addToErrorsAction,
    removeFromErrorsAction,
    asyncPlaceOrderAction
} from "../../actions/actions.js";
import TextInput from "../common/textInput.js";
import {DELIVERY_OPTIONS} from "../../constants/constants.js";

const modalStyle = {
    content: {
        width: '600px',
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
        padding: '0px',
        border: 'none'
    },
    overlay: {
        backgroundColor: 'rgba(0, 0, 0, 0.5)'
    }
};

class PlaceOrderDialogView extends Component {
    state = {modalIsOpen: false};

    openModal = () => {
        this.setState({modalIsOpen: true});
    };

    closeModal = () => {
        this.setState({modalIsOpen: false});
    };

    confirmModal = () => {
        var placeOrderFormHasErrors = false;

        Object.keys(this.props.errors).forEach(key => {
            if (key.startsWith('placeOrderForm')) {
                placeOrderFormHasErrors = true;
            }
        });

        if (!placeOrderFormHasErrors) {
            this.props.placeOrder(this.props.placeOrderForm, this.props.cart, this.props.session);
            this.closeModal();
        }
    };

    placeOrderInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updatePlaceOrderForm({id, value});

        if ((id == 'emailConfirm' && value != this.props.placeOrderForm.email) || (id == 'email' && value != this.props.placeOrderForm.emailConfirm)) {
            this.props.addToErrors('placeOrderForm.emailConfirm.match', {message: 'You must confirm your e-mail address!'});
        } else {
            this.props.removeFromErrors('placeOrderForm.emailConfirm.match');
        }
    };

    render() {
        return (
            <span>
                <button onClick={this.openModal} className="btn btn-success">Naruči</button>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onAfterOpen={() => console.log("modal opened")}
                    onRequestClose={this.closeModal}
                    style={modalStyle}
                >
                    <div className="modal-content">
                        <div className="modal-header bg-inverse">
                            <button type="button" className="close" onClick={this.closeModal}>
                                <span>×</span>
                            </button>
                            <h4 className="modal-title">Naruči</h4>
                        </div>
                        <div className="modal-body">
                            <p>
                                Molimo Vas unesite Vaše podatke, potvrda o primljenoj narudžbi biće Vam dostavljena na uneseni e-mail.
                            </p>
                            <form id="placeOrderForm">
                                <TextInput id="firstName"
                                           label="Ime"
                                           defaultValue={this.props.placeOrderForm.firstName}
                                           placeHolderText="Unesite Vaše ime"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="lastName"
                                           label="Prezime"
                                           defaultValue={this.props.placeOrderForm.lastName}
                                           placeHolderText="Unesite Vaše prezime"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="address"
                                           label="Adresa"
                                           defaultValue={this.props.placeOrderForm.address}
                                           placeHolderText="Vaša adresa (dostava samo unutar grada Sarajevo)"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="email"
                                           label="E-Mail"
                                           defaultValue={this.props.placeOrderForm.email}
                                           placeHolderText="Vaša E-Mail adresa za potvrdu narudžbe"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="emailConfirm"
                                           label="E-Mail"
                                           defaultValue={this.props.placeOrderForm.emailConfirm}
                                           placeHolderText="Ponovite vašu e-mail adresu"
                                           onChange={this.placeOrderInputChange}
                                           hasError={this.props.errors['placeOrderForm.emailConfirm.match']}/>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" onClick={this.closeModal}>Prekini</button>
                            <button type="button" className="btn btn-success" onClick={this.confirmModal}>Potvrdi</button>
                        </div>
                    </div>
                </Modal>
            </span>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        placeOrderForm: state.placeOrderForm,
        cart: state.cart,
        session: state.session,
        errors: state.errors
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updatePlaceOrderForm: (input) => {
            dispatch(updatePlaceOrderFormAction(input));
        },
        addToErrors: (key, error) => {
            dispatch(addToErrorsAction(key, error));
        },
        removeFromErrors: (key) => {
            dispatch(removeFromErrorsAction(key));
        },
        placeOrder: (placeOrderForm, cart, session) => {
            asyncPlaceOrderAction(dispatch, placeOrderForm, cart, session);
        }
    }
};

const PlaceOrderDialog = connect(mapStateToProps, mapDispatchToProps)(PlaceOrderDialogView);
export default PlaceOrderDialog;
