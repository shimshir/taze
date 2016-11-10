import React, {Component} from 'react';
import Stage from './stage.js';
import {connect} from 'react-redux';

class StageContainerView extends Component {

    render() {
        // TODO: Implement multiple stages handling
        return (
            this.props.page.closestStages ?
            <div>
                {this.props.page.closestStages.map((stage, index) => <Stage key={index} stage={stage}/>)}
            </div> :
            null
        )
    }
}

StageContainerView.propTypes = {
    page: React.PropTypes.object.isRequired
};

const StageContainer = connect(undefined, undefined)(StageContainerView);
export default StageContainer;
