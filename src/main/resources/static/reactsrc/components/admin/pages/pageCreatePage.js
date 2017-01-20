import React, {Component} from "react";
import {connect} from "react-redux";
import ContentContainer from '../../common/contentContainer.js';
import {
    updateCreatePageFormAction,
    updateCreateStageFormsAction,
    addCreateStageFormAction,
    removeCreateStageFormAction,
    asyncCreatePage
} from '../../../actions/admin/actions.js'
import TextInput from "../../common/textInput.js";
import FileInput from "../../common/fileInput.js";

class PageCreatePageView extends Component {
    state = {stagesCount: 3};

    reader = new FileReader();

    componentWillMount() {
    }

    handlePageInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreatePageForm({id, value});
    };

    handleStageInputChange = (stageIndex, event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreateStageForms(stageIndex, {id, value});
    };

    handleFileInputChange = (inputChangeEvent, formUpdateHandler) => {
        const id = inputChangeEvent.target.id;
        this.reader.onload = (onLoadEvent) => {
            formUpdateHandler({id, value: onLoadEvent.target.result});
        };

        this.reader.readAsDataURL(inputChangeEvent.target.files[0]);
    };

    handleFormSubmit = () => {
        const payload = {...this.props.createPageForm, stages: this.props.createStageForms};
        this.props.createPage(payload);
    };

    render() {
        const stagesCount = this.props.createStageForms.length;
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
                <div className="form-container">
                    <h2>Stage</h2>
                    <p>Enter stage data</p>
                    {
                        [...new Array(stagesCount).keys()]
                            .map(stageIndex =>
                                     <form key={stageIndex} id="createStageForm">
                                         <TextInput id="headline"
                                                    label="Headline"
                                                    onChange={(event) => this.handleStageInputChange(stageIndex, event)}
                                                    defaultValue={this.props.createStageForms[stageIndex].headline}/>
                                         <FileInput id="imageData"
                                                    label="Slika"
                                                    accept="image/*"
                                                    onChange={(event) =>
                                                        this.handleFileInputChange(event, (input) => this.props.updateCreateStageForms(stageIndex, input))}/>
                                     </form>
                            )
                    }
                    <button onClick={this.props.addCreateStageForm}>+</button>
                    <button onClick={this.props.removeCreateStageForm}>-</button>
                </div>
                <button onClick={this.handleFormSubmit}>Sacuvaj</button>
            </ContentContainer>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        createPageForm: state.createPageForm,
        createStageForms: state.createStageForms,
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        updateCreatePageForm: (input) => dispatch(updateCreatePageFormAction(input)),
        updateCreateStageForms: (index, input) => dispatch(updateCreateStageFormsAction(index, input)),
        addCreateStageForm: () => dispatch(addCreateStageFormAction()),
        removeCreateStageForm: () => dispatch(removeCreateStageFormAction()),
        createPage: (pageWithStages) => asyncCreatePage(dispatch, pageWithStages)
}
};

const PageCreatePage = connect(mapStateToProps, mapDispatchToProps)(PageCreatePageView);
export default PageCreatePage;
