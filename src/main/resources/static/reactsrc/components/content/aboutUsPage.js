import React, {Component} from 'react';
import {connect} from 'react-redux';
import StageContainer from '../stage/stageContainer.js';
import ContentContainer from '../common/contentContainer.js';
import {LOREM_IPSUM_PARAGRAPHS} from '../../constants/constants.js';
import {pageDispatchToPropsMappings} from '../common/pageDispatchToPropsMappings.js';

class AboutUsPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('about-us');
        this.props.getPage('about-us');
    }

    render() {
        return (
            <div>
                {this.props.page && <StageContainer page={this.props.page}/>}
                <ContentContainer>
                    <LOREM_IPSUM_PARAGRAPHS />
                </ContentContainer>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        page: state.pages['about-us']
    }
};

const AboutUsPage = connect(mapStateToProps, pageDispatchToPropsMappings)(AboutUsPageView);
export default AboutUsPage;