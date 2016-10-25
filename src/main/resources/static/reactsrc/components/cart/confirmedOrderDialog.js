import React from 'react';
import {connect} from 'react-redux';
import {toggleConfirmedOrderDialogAction} from '../../actions/actions.js';
import Modal from 'react-modal';

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

const ConfirmedOrderDialogView = ({confirmedOrderDialogIsOpen, toggleConfirmedOrderDialog}) => {
    return (
        <div>
            <Modal
                isOpen={confirmedOrderDialogIsOpen}
                onAfterOpen={() => console.log("modal opened")}
                onRequestClose={() => toggleConfirmedOrderDialog(false)}
                style={modalStyle}
            >
                <div className="modal-content">
                    <div className="modal-header bg-inverse">
                        <button type="button" className="close" onClick={() => toggleConfirmedOrderDialog(false)}>
                            <span>×</span>
                        </button>
                        <h4 className="modal-title">Hvala na narudžbi</h4>
                    </div>
                    <div className="modal-body">
                        <p>
                            Vaša narudžba je primljena i poslata je potvrda na Vaš E-Mail sa pregledom detalja narudžbe.
                        </p>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-success" onClick={() => toggleConfirmedOrderDialog(false)}>Ok</button>
                    </div>
                </div>
            </Modal>
        </div>
    )
};

ConfirmedOrderDialogView.propTypes = {};

const mapStateToProps = (state, ownProps) => {
    return {
        confirmedOrderDialogIsOpen: state.confirmedOrderDialog.isOpen
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        toggleConfirmedOrderDialog: (isOpen) => dispatch(toggleConfirmedOrderDialogAction(isOpen))
    }
};

const ConfirmedOrderDialog = connect(mapStateToProps, mapDispatchToProps)(ConfirmedOrderDialogView);
export default ConfirmedOrderDialog;