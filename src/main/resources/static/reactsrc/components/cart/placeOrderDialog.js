import React, {Component} from 'react';
import Modal from 'react-modal';
import {connect} from 'react-redux';
import {updatePlaceOrderFormAction, addToErrorMapAction, removeFromErrorMapAction} from '../../actions/actions.js';
import TextInput from '../common/textInput.js';

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
        this.props.errorMap.forEach((_, key) => {
            if (key.startsWith('placeOrderForm'))
                placeOrderFormHasErrors = true;
        });
        if (!placeOrderFormHasErrors) {
            // TODO: Submit form to backend
            this.closeModal();
        }
    };



    placeOrderInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updatePlaceOrderForm({id, value});

        if (this.props.placeOrderForm.get('eMail') != this.props.placeOrderForm.get('eMailConfirm'))
            this.props.addToErrorMap('placeOrderForm.eMailConfirm.match', {message: 'You must confirm your e-mail address!'});
        else
            this.props.removeFromErrorMap('placeOrderForm.eMailConfirm.match');
    };

    render() {
        return (
            <div>
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
                                           defaultValue={this.props.placeOrderForm.get('firstName')}
                                           placeHolderText="Unesite Vaše ime"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="lastName"
                                           label="Prezime"
                                           defaultValue={this.props.placeOrderForm.get('lastName')}
                                           placeHolderText="Unesite Vaše prezime"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="address"
                                           label="Adresa"
                                           defaultValue={this.props.placeOrderForm.get('address')}
                                           placeHolderText="Vaša adresa (dostava samo unutar grada Sarajevo)"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="eMail"
                                           label="E-Mail"
                                           defaultValue={this.props.placeOrderForm.get('eMail')}
                                           placeHolderText="Vaša E-Mail adresa na koju će da stigne potvrda o narudžbi"
                                           onChange={this.placeOrderInputChange}/>
                                <TextInput id="eMailConfirm"
                                           label="E-Mail"
                                           defaultValue={this.props.placeOrderForm.get('eMailConfirm')}
                                           placeHolderText="Ponovite vašu e-mail adresu"
                                           onChange={this.placeOrderInputChange}
                                           hasError={this.props.errorMap.get('placeOrderForm.eMailConfirm.match')}/>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" onClick={this.closeModal}>Prekini</button>
                            <button type="button" className="btn btn-success" onClick={this.confirmModal}>Potvrdi</button>
                        </div>
                    </div>
                </Modal>
            </div>
        )
    }
}


const mapStateToProps = (state, ownProps) => {
    return {
        placeOrderForm: state.placeOrderForm,
        errorMap: state.errorMap
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updatePlaceOrderForm: (input) => {
            dispatch(updatePlaceOrderFormAction(input));
        },
        addToErrorMap: (key, error) => {
            dispatch(addToErrorMapAction(key, error));
        },
        removeFromErrorMap: (key) => {
            dispatch(removeFromErrorMapAction(key));
        }
    }
};

const PlaceOrderDialog = connect(mapStateToProps, mapDispatchToProps)(PlaceOrderDialogView);
export default PlaceOrderDialog;