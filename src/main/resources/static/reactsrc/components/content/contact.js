import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';


class ContactView extends Component {
    componentWillMount() {
        console.log("contact mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Kontakt" stageBackgroundClass="contact"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        );
    }
}

const Contact = connect(undefined, undefined)(ContactView);
export default Contact;