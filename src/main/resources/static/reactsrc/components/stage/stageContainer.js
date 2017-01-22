import React, {Component} from 'react';
import Stage from './stage.js';
import {connect} from 'react-redux';
import Transition from '../common/transition';

class StageContainerView extends Component {
    render() {
        const stageComponents = this.props.page.closestStages ? this.props.page.closestStages.map((stage, index) => <Stage key={index} stage={stage}/>) : [];
        return (
            <div className="stage-container">
                <Transition itemHeight={200}>
                    {stageComponents}
                </Transition>
            </div>
        )
    }
}

StageContainerView.propTypes = {
    page: React.PropTypes.object.isRequired
};

const StageContainer = connect(undefined, undefined)(StageContainerView);
export default StageContainer;
