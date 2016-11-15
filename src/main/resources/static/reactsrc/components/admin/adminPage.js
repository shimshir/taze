import React, {Component} from 'react';
import {connect} from 'react-redux';
import Sidebar from '../nav/sidebar.js';

class AdminPageView extends Component {

    componentWillMount() {
        console.log("admin page mounted");
    }

    render() {
        return (
            <div>
                <Sidebar/>
                <div className="container">
                    {this.props.children}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const AdminPage = connect(undefined, mapDispatchToProps)(AdminPageView);
export default AdminPage;
