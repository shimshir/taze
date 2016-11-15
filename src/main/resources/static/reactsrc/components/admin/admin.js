import React, {Component} from 'react';
import {connect} from 'react-redux';
import Sidebar from '../nav/sidebar.js';
import StageContainer from '../stage/stageContainer.js';
import {pageDispatchToPropsMappings} from '../common/pageDispatchToPropsMappings.js';

class AdminView extends Component {

    componentWillMount() {
        console.log("admin page mounted");
        this.props.getPage('/admin');
    }

    render() {
        const page = this.props.pages['/admin'];
        return (
            <div>
                <Sidebar/>
                <div className="container">
                    {page && <StageContainer page={page}/>}
                    {this.props.children}
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        pages: state.pages
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getPage: (pagePath) => pageDispatchToPropsMappings(dispatch, ownProps).getPage(pagePath)
    }
};

const Admin = connect(mapStateToProps, mapDispatchToProps)(AdminView);
export default Admin;
