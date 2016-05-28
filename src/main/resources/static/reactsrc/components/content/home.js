import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';

class HomeView extends Component {
    componentWillMount() {
        console.log("home mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Pocetna" stageBackgroundClass="home"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        );
    }
}

const Home = connect(undefined, undefined)(HomeView);
export default Home;