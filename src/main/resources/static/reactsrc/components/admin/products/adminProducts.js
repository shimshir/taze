import React, {Component} from 'react';
import {connect} from 'react-redux';

class AdminProductsView extends Component {

    componentWillMount() {
    }

    render() {
        return (
            <div>
                {this.props.children}
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {}
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const AdminProducts = connect(mapStateToProps, mapDispatchToProps)(AdminProductsView);
export default AdminProducts;
