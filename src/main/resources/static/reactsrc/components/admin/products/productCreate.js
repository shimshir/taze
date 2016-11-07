import React, {Component} from "react";
import {connect} from "react-redux";
import TextInput from "../../common/textInput.js";
import FileInput from "../../common/fileInput.js";
import axios from 'axios';
import ContentContainer from '../../common/contentContainer.js';
import {API_ADMIN_PATH} from '../../../constants/constants.js';

class ProductCreateView extends Component {
    // TODO: Use redux state instead
    state = {createProductForm: {}};

    reader = new FileReader();

    componentWillMount() {
    }

    handleProductInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.updateCreateProductForm(id, value);
    };

    handleFormSubmit = (event) => {
        // TODO: Do this in a proper action
        axios.post(API_ADMIN_PATH + "/products", this.state.createProductForm)
            .then(res => console.log(res.data));
    };

    handleFileInputChange = (inputChangeEvent) => {
        const id = inputChangeEvent.target.id;
        this.reader.onload = (onLoadEvent) => {
            this.updateCreateProductForm(id, onLoadEvent.target.result);
        };

        this.reader.readAsDataURL(inputChangeEvent.target.files[0]);
    };

    updateCreateProductForm = (id, value) => {
        this.state.createProductForm[id] = value;
    };

    render() {
        return (
            <ContentContainer>
                <form id="createProductForm" onSubmit={this.handleFormSubmit}>
                    <TextInput id="code"
                               label="Code"
                               onChange={this.handleProductInputChange}/>
                    <TextInput id="name"
                               label="Name"
                               onChange={this.handleProductInputChange}/>
                    <TextInput id="pricePerUnit"
                               label="Price per unit"
                               onChange={this.handleProductInputChange}/>
                    <TextInput id="unitCode"
                               label="Unit code"
                               onChange={this.handleProductInputChange}/>
                    <TextInput id="footnote"
                               label="Footnote"
                               onChange={this.handleProductInputChange}/>
                    <FileInput id="pdpImageData"
                               label="Detail image"
                               accept="image/*"
                               onChange={this.handleFileInputChange}/>
                    <FileInput id="listImageData"
                               label="List image"
                               accept="image/*"
                               onChange={this.handleFileInputChange}/>
                </form>
                <button onClick={this.handleFormSubmit}>Submit</button>
            </ContentContainer>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {}
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const ProductCreate = connect(mapStateToProps, mapDispatchToProps)(ProductCreateView);
export default ProductCreate;
