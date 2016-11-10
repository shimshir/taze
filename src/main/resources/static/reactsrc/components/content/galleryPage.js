import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from '../common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';
import {changeActiveTopNavbarItemDispatchMapping} from '../common/commonMappings.js';

class GalleryPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('gallery');
    }

    render() {
        return (
            <div>
                <Stage headerText="Galerija" stageBackgroundClass="gallery"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        )
    }
}

const GalleryPage = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(GalleryPageView);
export default GalleryPage;