import React, { Component } from 'react';
import { connect } from 'react-redux';

class GalleryView extends Component {
    componentWillMount() {
        console.log("gallery mounted");
    }

    render() {
        return (<div><h1>Galerija</h1></div>);
    }
}

const Gallery = connect(undefined, undefined)(GalleryView);
export default Gallery;