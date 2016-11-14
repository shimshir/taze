import React, {Component} from "react";
import {connect} from "react-redux";
import ContentContainer from '../../common/contentContainer.js';
import {updateCreatePageFormAction} from '../../../actions/admin/actions.js'

class PageCreatePageView extends Component {
    componentWillMount() {

    }

    handlePageInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreatePageForm({id, value});
    };

    render() {
        return (
            <ContentContainer>
                <div className="form-container">
                    <h2>Stranica</h2>
                    <p>Unesite podatke o stranici</p>
                    <form id="createPageForm">
                        <TextInput id="path"
                                   label="Path"
                                   onChange={this.handlePageInputChange}
                                   defaultValue={this.props.createPageForm.path}/>
                        <TextInput id="parentPagePath"
                                   label="Parent path"
                                   onChange={this.handlePageInputChange}
                                   defaultValue={this.props.createPageForm.parentPagePath}/>
                    </form>
                </div>
            </ContentContainer>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        createPageForm: state.createPageForm
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updateCreatePageForm: (input) => dispatch(updateCreatePageFormAction(input))
    }
};

const PageCreatePage = connect(mapStateToProps, mapDispatchToProps)(PageCreatePageView);
export default PageCreatePage;
