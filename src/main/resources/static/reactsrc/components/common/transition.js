import React, {Component} from 'react';
import {connect} from 'react-redux';

class TransitionView extends Component {
    state = {transitionClasses: this.props.children.map((_, index) => index == 0 ? 'transition-fade-in' : 'transition-fade-out')};
    intervalId = null;

    componentDidMount() {
        this.intervalId = setInterval(this.shiftTransitionClass, 10000);
    }

    componentWillUnmount() {
        clearInterval(this.intervalId);
    }

    shiftTransitionClass = () => {
        const transitionClassesCopy = Array.from(this.state.transitionClasses);
        transitionClassesCopy.unshift(transitionClassesCopy.pop());
        this.setState({transitionClasses: transitionClassesCopy});
    };

    render() {
        return (
            <div>
                {
                    this.props.children.map((child, index) =>
                        <div key={index} className={this.state.transitionClasses[index]} style={index != 0 ? {marginTop: `-${this.props.itemHeight}px`} : {}}>
                            {child}
                        </div>
                    )
                }
            </div>
        )
    }
}

TransitionView.propTypes = {
    itemHeight: React.PropTypes.number.isRequired
};

const Transition = connect(undefined, undefined)(TransitionView);
export default Transition;
