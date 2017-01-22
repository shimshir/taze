import React from 'react';

const Stage = ({stage}) => {
    return (
        <div className="stage" style={{backgroundImage: `url(${stage.image})`}}>
            <div className="stage-headline-container">
                <h1 style={{fontSize: "4.5em"}}><b>Taze</b></h1>
                <h1>&nbsp;{stage.headline}</h1>
            </div>
        </div>
    )
};

Stage.propTypes = {
    stage: React.PropTypes.object.isRequired
};

export default Stage;
