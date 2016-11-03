import React from "react";

const SelectInput = ({id, label, options, onChange, hasError}) => {
    return (
        <div className="row">
            <div className="col-lg-3">
                <label htmlFor={id}>{label}</label>
            </div>
            <div className="col-lg-8">
                <select id={id} onChange={onChange} className={'form-control ' + (hasError ? 'alert-danger' : '')}>
                    {options.map(option => <option key={option.value} value={option.value}>{option.text}</option>)}
                </select>
            </div>
        </div>
    );
};

SelectInput.propTypes = {
    id: React.PropTypes.string.isRequired,
    label: React.PropTypes.string.isRequired,
    options: React.PropTypes.array.isRequired,
    onChange: React.PropTypes.func,
    hasError: React.PropTypes.bool
};

export default SelectInput;
