import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';

class HomeView extends Component {
    componentWillMount() {
        console.log("home mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Pocetna" stageBackgroundClass="home"/>
            </div>
        );
    }
}

const Home = connect(undefined, undefined)(HomeView);
export default Home;