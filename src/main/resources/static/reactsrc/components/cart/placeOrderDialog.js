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
    state = {modalIsOpen: false};
    
    openModal = () => {
        this.setState({modalIsOpen: true});
    };

    closeModal = () => {
        this.setState({modalIsOpen: false});
    };
    
    render() {
        return (
            <div>
                <button onClick={this.openModal} className="btn btn-success">Naruci</button>
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
                            <h4 className="modal-title">Modal title</h4>
                        </div>
                        <div className="modal-body">
                            <h4>Content...</h4>
                            <p>
                                Pellentesque habitant morbi tristique senectus
                                et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae,
                                ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean
                                ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien
                                ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet,
                                wisi.
                            </p>
                            <p>
                                Pellentesque habitant morbi tristique senectus
                                et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae,
                                ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean
                                ultricies mi vitae est.
                            </p>
                            <p>
                                Pellentesque habitant morbi tristique senectus
                                et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae,
                                ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean
                                ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien
                                ullamcorper pharetra.
                            </p>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" onClick={this.closeModal}>Prekini</button>
                            <button type="button" className="btn btn-success">Posalji</button>
                        </div>
                    </div>
                </Modal>
            </div>
        )
    }
}

const PlaceOrderDialog = connect()(PlaceOrderDialogView);
export default PlaceOrderDialog;