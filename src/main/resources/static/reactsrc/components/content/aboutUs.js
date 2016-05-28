import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';

class AboutUsView extends Component {
    componentWillMount() {
        console.log("about us mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="O nama" stageBackgroundClass="about-us"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        );
    }
}

const AboutUs = connect(undefined, undefined)(AboutUsView);
export default AboutUs;