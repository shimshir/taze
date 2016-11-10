import React, {Component} from "react";
import {connect} from "react-redux";
import TextInput from "../../common/textInput.js";
import FileInput from "../../common/fileInput.js";
import ContentContainer from '../../common/contentContainer.js';
import {
    asyncCreateProductAction,
    asyncCreateProductCardAction,
    updateCreateProductFormAction,
    updateCreateProductCardFormAction
} from '../../../actions/admin/actions.js'

class ProductCreatePageView extends Component {

    reader = new FileReader();

    componentWillMount() {
    }

    handleFormSubmit = () => {
        this.props.createProduct(this.props.createProductForm)
            .then(res => this.props.createProductCard(this.props.createProductCardForm));
    };

    handleProductInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreateProductForm({id, value});
        if (id == 'code') {
            this.props.updateCreateProductCardForm({id: 'productCode', value});
        }
    };

    handleProductCardInputChange = (event) => {
        const id = event.target.id;
        const value = event.target.value;
        this.props.updateCreateProductCardForm({id, value});
    };

    handleFileInputChange = (inputChangeEvent, formUpdateHandler) => {
        const id = inputChangeEvent.target.id;
        this.reader.onload = (onLoadEvent) => {
            formUpdateHandler({id, value: onLoadEvent.target.result});
        };

        this.reader.readAsDataURL(inputChangeEvent.target.files[0]);
    };

    render() {
        return (
            <ContentContainer>
                <div className="form-container">
                    <h2>Proizvod</h2>
                    <p>Unesite podatke o proizvodu</p>
                    <form id="createProductForm">
                        <TextInput id="code"
                                   label="K&#333;d"
                                   onChange={this.handleProductInputChange}
                                   defaultValue={this.props.createProductForm.code}/>
                        <TextInput id="name"
                                   label="Ime"
                                   onChange={this.handleProductInputChange}
                                   defaultValue={this.props.createProductForm.name}/>
                        <TextInput id="pricePerUnit"
                                   label="Cijena po jedinici"
                                   onChange={this.handleProductInputChange}
                                   defaultValue={this.props.createProductForm.pricePerUnit}/>
                        <TextInput id="unitCode"
                                   label="Mjerna jedinica"
                                   onChange={this.handleProductInputChange}
                                   defaultValue={this.props.createProductForm.unitCode}/>
                        <TextInput id="footnote"
                                   label="Fusnota"
                                   onChange={this.handleProductInputChange}
                                   defaultValue={this.props.createProductForm.footnote}/>
                        <FileInput id="pdpImageData"
                                   label="Slika, detalji"
                                   accept="image/*"
                                   onChange={(event) => this.handleFileInputChange(event, this.props.updateCreateProductForm)}/>
                        <FileInput id="listImageData"
                                   label="Slika, lista"
                                   accept="image/*"
                                   onChange={(event) => this.handleFileInputChange(event, this.props.updateCreateProductForm)}/>
                    </form>
                </div>
                <div className="form-container">
                    <h2>Kartica</h2>
                    <p>Unesite podatke o kartici</p>
                    <form id="createProductCardForm">
                        <TextInput id="title"
                                   label="Naslov"
                                   onChange={this.handleProductCardInputChange}
                                   defaultValue={this.props.createProductCardForm.title}/>
                        <TextInput id="paragraph"
                                   label="Paragraf tekst"
                                   onChange={this.handleProductCardInputChange}
                                   defaultValue={this.props.createProductCardForm.paragraph}/>
                        <TextInput id="small"
                                   label="Mali tekst"
                                   onChange={this.handleProductCardInputChange}
                                   defaultValue={this.props.createProductCardForm.small}/>
                        <FileInput id="imageData"
                                   label="Slika"
                                   accept="image/*"
                                   onChange={(event) => this.handleFileInputChange(event, this.props.updateCreateProductCardForm)}/>
                    </form>
                </div>
                <button onClick={this.handleFormSubmit}>Sacuvaj</button>
            </ContentContainer>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        createProductForm: state.createProductForm,
        createProductCardForm: state.createProductCardForm
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        createProduct: (createProductForm) => asyncCreateProductAction(dispatch, createProductForm),
        createProductCard: (createProductCardForm) => asyncCreateProductCardAction(dispatch, createProductCardForm),
        updateCreateProductForm: (input) => dispatch(updateCreateProductFormAction(input)),
        updateCreateProductCardForm: (input) => dispatch(updateCreateProductCardFormAction(input))
    }
};

const ProductCreatePage = connect(mapStateToProps, mapDispatchToProps)(ProductCreatePageView);
export default ProductCreatePage;
