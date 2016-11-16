import React, {Component} from "react";
import {connect} from "react-redux";
import TableCellPreview from './../../admin/common/tableCellPreview.js';

class ProductRowView extends Component {
    state = {showPreviewMap: {}};

    handleShowCellPreview = (key, value) => {
        const showPreviewMap = {...this.state.showPreviewMap};
        showPreviewMap[key] = value;
        this.setState({showPreviewMap});
    };

    componentWillMount() {
    }

    // TODO: Implement ProductCardPreview
    render() {
        const product = this.props.product;
        return (
            <tr>
                <th scope="row">{product.id}</th>
                <td>{product.code}</td>
                <td>{product.name}</td>
                <td>
                    <a href={product.listImage}
                       onMouseEnter={() => this.handleShowCellPreview('listImage', true)}
                       onMouseLeave={() => this.handleShowCellPreview('listImage', false)}>
                        <span>...{product.listImage.slice(-10)}</span>
                        <TableCellPreview content={product.listImage}
                                          type="image"
                                          visible={this.state.showPreviewMap['listImage']}/>
                    </a>
                </td>
                <td>
                    <a href={product.pdpImage}
                       onMouseEnter={() => this.handleShowCellPreview('pdpImage', true)}
                       onMouseLeave={() => this.handleShowCellPreview('pdpImage', false)}>
                        <span>...{product.pdpImage.slice(-10)}</span>
                        <TableCellPreview content={product.pdpImage}
                                          type="image"
                                          visible={this.state.showPreviewMap['pdpImage']}/>
                    </a>
                </td>
                <td>{product.pricePerUnit}</td>
                <td>{product.unitCode}</td>
                <td>
                    <div onMouseEnter={() => this.handleShowCellPreview('footnote', true)}
                         onMouseLeave={() => this.handleShowCellPreview('footnote', false)}>
                        <span>{product.footnote && product.footnote.slice(0, 10) + '...'}</span>
                        <TableCellPreview content={product.footnote}
                                          type="text"
                                          visible={this.state.showPreviewMap['footnote']}/>
                    </div>
                </td>
                <td><a href={product._links.productCard.href}>...{product._links.productCard.href.slice(-10)}</a></td>
            </tr>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
    }
};

const ProductRow = connect(mapStateToProps, mapDispatchToProps)(ProductRowView);

ProductRow.propTypes = {
    product: React.PropTypes.object.isRequired
};

export default ProductRow;
