import React, {Component} from 'react';
import Modal from 'react-modal';
import {connect} from 'react-redux';

const modalStyle = {
    content: {
        width                 : '600px',
        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)',
        padding               : '0',
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
                            <h4>Content...</h4>
                            <p>
                                Molimo Vas unesite Vaše podatke, potvrda o primljenoj narudžbi biće Vam dostavljena na uneseni e-mail.
                            </p>
                            <form>
                                <div className="row">
                                    <div className="form-group col-lg-6">
                                        <div className="col-lg-3 no-padding">
                                            <label for="firstName">Ime</label>
                                        </div>
                                        <div className="col-lg-9 no-padding">
                                            <input type="text" className="form-control" id="firstName" placeholder="Unesite Vaše ime"/>
                                        </div>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <div className="col-lg-3 no-padding">
                                            <label for="lastName">Prezime</label>
                                        </div>
                                        <div className="col-lg-9 no-padding">
                                            <input type="text" className="form-control" id="lastName" placeholder="Unesite Vaše prezime"/>
                                        </div>
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

const PlaceOrderDialog = connect()(PlaceOrderDialogView);
export default PlaceOrderDialog;