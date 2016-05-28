import React, { Component } from 'react';
import { connect } from 'react-redux';
import Stage from '../stage/stage.js';

class GalleryView extends Component {
    componentWillMount() {
        console.log("gallery mounted");
    }

    render() {
        return (
            <div>
                <Stage headerText="Galerija" stageBackgroundClass="gallery"/>
            </div>
        );
    }
}

const Gallery = connect(undefined, undefined)(GalleryView);
export default Gallery;