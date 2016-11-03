import React from "react";

const TextInput = ({id, label, defaultValue, placeHolderText, onChange, hasError}) => {
    return (
        <div className="row">
            <div className="col-lg-3">
                <label htmlFor={id}>{label}</label>
            </div>
            <div className="col-lg-8">
                <input type="text"
                       className={'form-control ' + (hasError ? 'alert-danger' : '')}
                       id={id}
                       defaultValue={defaultValue}
                       placeholder={placeHolderText}
                       onChange={onChange}/>
            </div>
        </div>
    );
};

export default TextInput;
