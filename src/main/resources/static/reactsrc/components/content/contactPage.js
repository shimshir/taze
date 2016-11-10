import React, {Component} from 'react';
import {connect} from 'react-redux';
import StageContainer from '../stage/stageContainer.js';
import ContentContainer from '../common/contentContainer.js';
import {LOREM_IPSUM_PARAGRAPHS} from '../../constants/constants.js';
import {pageDispatchToPropsMappings} from '../common/pageDispatchToPropsMappings.js';

class ContactPageView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('contact');
        this.props.getPage('contact');
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
        page: state.pages['contact']
    }
};

const ContactPage = connect(mapStateToProps, pageDispatchToPropsMappings)(ContactPageView);
export default ContactPage;