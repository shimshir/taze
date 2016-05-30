import React, {Component} from 'react';
import {connect} from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';
import {changeActiveTopNavbarItemDispatchMapping} from './common/commonMappings.js';

class RootView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('root');
    }

    render() {
        return (
            <div>
                <Stage headerText="Pocetna" stageBackgroundClass="root"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        );
    }
}

const Root = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(RootView);
export default Root;