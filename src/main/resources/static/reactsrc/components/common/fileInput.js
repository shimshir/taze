import React from "react";

const FileInput = ({id, label, accept, onChange}) => {
    return (
        <div className="row">
            <div className="col-lg-3">
                <label htmlFor={id}>{label}</label>
            </div>
            <div className="col-lg-8">
                <input id={id}
                       type="file"
                       accept={accept}
                       onChange={onChange}/>
            </div>
        </div>
    );
};

FileInput.propTypes = {
    id: React.PropTypes.string.isRequired,
    label: React.PropTypes.string.isRequired,
    accept: React.PropTypes.string,
    onChange: React.PropTypes.func
};


export default FileInput;
