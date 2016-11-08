import React, {Component} from "react";
import {connect} from "react-redux";
import TextInput from "../../common/textInput.js";
import FileInput from "../../common/fileInput.js";
import ContentContainer from '../../common/contentContainer.js';
import {asyncCreateProductAction, updateCreateProductFormAction} from '../../../actions/admin/actions.js'

class ProductCreateView extends Component {

    reader = new FileReader();

    componentWillMount() {
    }

    handleProductInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreateProductForm({id, value});
    };

    handleFormSubmit = () => {
        this.props.createProduct(this.props.createProductForm);
    };

    handleFileInputChange = (inputChangeEvent) => {
        const id = inputChangeEvent.target.id;
        this.reader.onload = (onLoadEvent) => {
            this.props.updateCreateProductForm({id, value: onLoadEvent.target.result});
            console.log(onLoadEvent.target.result)
        };

        this.reader.readAsDataURL(inputChangeEvent.target.files[0]);
    };

    render() {
        return (
            <ContentContainer>
                <form id="createProductForm" onSubmit={this.handleFormSubmit}>
                    <TextInput id="code"
                               label="Code"
                               onChange={this.handleProductInputChange}
                               defaultValue={this.props.createProductForm.code}/>
                    <TextInput id="name"
                               label="Name"
                               onChange={this.handleProductInputChange}
                               defaultValue={this.props.createProductForm.name}/>
                    <TextInput id="pricePerUnit"
                               label="Price per unit"
                               onChange={this.handleProductInputChange}
                               defaultValue={this.props.createProductForm.pricePerUnit}/>
                    <TextInput id="unitCode"
                               label="Unit code"
                               onChange={this.handleProductInputChange}
                               defaultValue={this.props.createProductForm.unitCode}/>
                    <TextInput id="footnote"
                               label="Footnote"
                               onChange={this.handleProductInputChange}
                               defaultValue={this.props.createProductForm.footnote}/>
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
    return {
        createProductForm: state.createProductForm
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        createProduct: (createProductForm) => asyncCreateProductAction(dispatch, createProductForm),
        updateCreateProductForm: (input) => dispatch(updateCreateProductFormAction(input))
    }
};

const ProductCreate = connect(mapStateToProps, mapDispatchToProps)(ProductCreateView);
export default ProductCreate;
