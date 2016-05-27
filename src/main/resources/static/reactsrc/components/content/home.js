import React, { Component } from 'react';
import { connect } from 'react-redux';

class HomeView extends Component {
    componentWillMount() {
        console.log("home mounted");
    }

    render() {
        return (<div><h1>Pocetna</h1></div>);
    }
}

const Home = connect(undefined, undefined)(HomeView);
export default Home;