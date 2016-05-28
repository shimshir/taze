import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';
import ContentContainer from './common/contentContainer.js';
import { LOREM_IPSUM_PARAGRAPHS } from '../../constants/constants.js';

class GalleryView extends Component {
    componentWillMount() {
        console.log("gallery mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Galerija" stageBackgroundClass="gallery"/>
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        );
    }
}

const Gallery = connect(undefined, undefined)(GalleryView);
export default Gallery;