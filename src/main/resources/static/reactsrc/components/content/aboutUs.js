import React, { Component } from 'react';
import { connect } from 'react-redux';

class AboutUsView extends Component {
    componentWillMount() {
        console.log("about us mounted");
    }

    render() {
        return (<div><h1>O nama</h1></div>);
    }
}

const AboutUs = connect(undefined, undefined)(AboutUsView);
export default AboutUs;