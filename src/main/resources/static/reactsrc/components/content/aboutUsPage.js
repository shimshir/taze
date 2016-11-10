import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';

class AboutUsPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('about-us');
    }

    render() {
        return (
            <div>
                <Stage headerText="O nama" stageBackgroundClass="about-us"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        )
    }
}

const AboutUsPage = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(AboutUsPageView);
export default AboutUsPage;