import React from "react";

const SelectInput = ({id, label, defaultValue, options, onChange, hasError}) => {
    return (
        <div className="row">
            <div className="col-lg-3">
                <label htmlFor={id}>{label}</label>
            </div>
            <div className="col-lg-8">
                <select id={id}
                        onChange={onChange}
                        className={'form-control ' + (hasError ? 'alert-danger' : '')}>
                    {
                        options.map(
                            // select#defaultValue does not work, react does not register its change, so option#selected is used
                            option => {
                                return (defaultValue && (defaultValue == option.value)) ?
                                       <option key={option.value} value={option.value} selected>
                                           {option.text}
                                       </option>
                                    :
                                       <option key={option.value} value={option.value}>
                                           {option.text}
                                       </option>
                            }
                        )
                    }
                </select>
            </div>
        </div>
    );
};

SelectInput.propTypes = {
    id: React.PropTypes.string.isRequired,
    label: React.PropTypes.string.isRequired,
    defaultValue: React.PropTypes.string,
    options: React.PropTypes.array.isRequired,
    onChange: React.PropTypes.func,
    hasError: React.PropTypes.bool
};

export default SelectInput;
