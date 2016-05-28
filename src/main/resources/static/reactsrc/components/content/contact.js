import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';


class ContactView extends Component {
    componentWillMount() {
        console.log("contact mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Kontakt" stageBackgroundClass="contact"/>
            </div>
        );
    }
}

const Contact = connect(undefined, undefined)(ContactView);
export default Contact;