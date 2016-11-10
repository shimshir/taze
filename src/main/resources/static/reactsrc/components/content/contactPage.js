import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import {LOREM_IPSUM_PARAGRAPHS} from '../../constants/constants.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';

class ContactPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('contact');
    }

    render() {
        return (
            <div>
                <Stage headerText="Kontakt" stageBackgroundClass="contact"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        )
    }
}

const ContactPage = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(ContactPageView);
export default ContactPage;