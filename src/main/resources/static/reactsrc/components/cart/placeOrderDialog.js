import React, {Component} from 'react';
import Modal from 'react-modal';
import {connect} from 'react-redux';
import {updatePlaceOrderFormAction} from '../../actions/actions.js';

const modalStyle = {
    content: {
        width                 : '600px',
        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)',
        padding               : '0px',
        border                : 'none'
    },
    overlay: {
        backgroundColor: 'rgba(0, 0, 0, 0.5)'
    }
};

class PlaceOrderDialogView extends Component {
    state = {modalIsOpen: true};
    
    openModal = () => {
        this.setState({modalIsOpen: true});
    };

    closeModal = () => {
        this.setState({modalIsOpen: false});
    };

    placeOrderInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updatePlaceOrderForm({id, value});
    };
    
    render() {
        // TODO: Make the input row in the #placeOrderForm a component itself
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
                                <div className="row">
                                    <div className="col-lg-2">
                                        <label for="firstName">Ime</label>
                                    </div>
                                    <div className="col-lg-10">
                                        <input type="text"
                                               className="form-control"
                                               id="firstName"
                                               defaultValue={this.props.placeOrderForm.get('firstName')}
                                               placeholder="Unesite Vaše ime"
                                               onChange={this.placeOrderInputChange}/>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-lg-2">
                                        <label for="lastName">Prezime</label>
                                    </div>
                                    <div className="col-lg-10">
                                        <input type="text"
                                               className="form-control"
                                               id="lastName"
                                               defaultValue={this.props.placeOrderForm.get('lastName')}
                                               placeholder="Unesite Vaše prezime"
                                               onChange={this.placeOrderInputChange}/>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-lg-2">
                                        <label for="address">Adresa</label>
                                    </div>
                                    <div className="col-lg-10">
                                        <input type="text"
                                               className="form-control"
                                               id="address"
                                               defaultValue={this.props.placeOrderForm.get('address')}
                                               placeholder="Vaša adresa (dostava samo unutar grada Sarajevo)"
                                               onChange={this.placeOrderInputChange}/>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-lg-2">
                                        <label for="eMail">e-mail</label>
                                    </div>
                                    <div className="col-lg-10">
                                        <input type="text"
                                               className="form-control"
                                               id="eMail"
                                               defaultValue={this.props.placeOrderForm.get('eMail')}
                                               placeholder="Vaša e-mail adresa na koju će da stigne potvrda o narudžbi"
                                               onChange={this.placeOrderInputChange}/>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-lg-2">
                                        <label for="eMailConfirm">e-mail</label>
                                    </div>
                                    <div className="col-lg-10">
                                        <input type="text"
                                               className="form-control"
                                               id="eMailConfirm"
                                               defaultValue={this.props.placeOrderForm.get('eMailConfirm')}
                                               placeholder="Ponovite vašu e-mail adresu"
                                               onChange={this.placeOrderInputChange}/>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" onClick={this.closeModal}>Prekini</button>
                            <button type="button" className="btn btn-success">Potvrdi</button>
                        </div>
                    </div>
                </Modal>
            </div>
        )
    }
}


const mapStateToProps = (state, ownProps) => {
    return {
        placeOrderForm: state.placeOrderForm
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updatePlaceOrderForm: (input) => {
            dispatch(updatePlaceOrderFormAction(input));
        }
    }
};

const PlaceOrderDialog = connect(mapStateToProps, mapDispatchToProps)(PlaceOrderDialogView);
export default PlaceOrderDialog;