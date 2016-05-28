import React from 'react';

const Stage = ({ headerText, stageBackgroundClass }) => {
    return (
        <div className={'stage ' + stageBackgroundClass}>
            <div className="stage-header-container">
                <h1 style={{fontSize: "4.5em"}}><b>Taze</b></h1>
                <h1>&nbsp;{ headerText }</h1>
            </div>
        </div>
    );
};

export default Stage;