import React, { Component } from 'react';
import { connect } from 'react-redux';

class ContactView extends Component {
    componentWillMount() {
        console.log("contact mounted");
    }

    render() {
        return (<div><h1>Kontakt</h1></div>);
    }
}

const Contact = connect(undefined, undefined)(ContactView);
export default Contact;