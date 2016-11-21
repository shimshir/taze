import React, {Component} from "react";
import {connect} from "react-redux";
import ProductCard from '../../content/products/productCard.js';
import {asyncGetPreviewProductCardAction} from '../../../actions/admin/actions.js';

class TableCellPreviewView extends Component {
    renderContent = () => {
        switch (this.props.type) {
            case 'text':
                return <span className="cell-preview-text">{this.props.content}</span>;
            case 'image':
                return <img className="cell-preview-img" src={this.props.content}/>;
            case 'productCard':
                const productCard = this.props.productCards[this.props.content];
                return productCard ? <ProductCard productCard={productCard}/> : null;
            default:
                return <span>Unsupported content type</span>
        }
    };

    componentWillMount() {
        if (this.props.type == 'productCard') {
            this.props.getProductCardPreview(this.props.content);
        }
    }

    render() {
        return (
            (this.props.visible && this.props.content) ?
            <div className="table-cell-preview">
                {
                    this.renderContent()
                }
            </div> :
            null
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        productCards: state.previewProductCards
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getProductCardPreview: (productCode) => asyncGetPreviewProductCardAction(dispatch, productCode)
    }
};

const TableCellPreview = connect(mapStateToProps, mapDispatchToProps)(TableCellPreviewView);

TableCellPreview.propTypes = {
    content: React.PropTypes.string,
    type: React.PropTypes.string.isRequired,
    visible: React.PropTypes.bool
};

export default TableCellPreview;
