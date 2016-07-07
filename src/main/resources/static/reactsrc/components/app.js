import React, {Component} from 'react';
import TopNavbar from './nav/topNavbar.js';
import {asyncCheckSessionAction} from '../actions/actions.js';
import {connect} from 'react-redux';

class AppView extends Component {

    componentWillMount() {
        this.props.checkSession();
    }

    render() {
        return (
            <div className="react-root">
                <TopNavbar/>
                <div className="container">
                    {this.props.children}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        checkSession: () => {
            asyncCheckSessionAction(dispatch);
        }
    }
};

const App = connect(undefined, mapDispatchToProps)(AppView);
export default App;
