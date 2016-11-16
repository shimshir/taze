import React, {Component} from "react";
import {connect} from "react-redux";

class TableCellPreviewView extends Component {
    renderContent = () => {
        switch (this.props.type) {
            case 'text':
                return <span className="cell-preview-text">{this.props.content}</span>;
            case 'image':
                return <img className="cell-preview-img" src={this.props.content}/>;
            default:
                return <span>Unsupported content type</span>
        }
    };

    componentWillMount() {
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
    return {}
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const TableCellPreview = connect(mapStateToProps, mapDispatchToProps)(TableCellPreviewView);

TableCellPreview.propTypes = {
    content: React.PropTypes.string,
    type: React.PropTypes.string.isRequired,
    visible: React.PropTypes.bool
};

export default TableCellPreview;
