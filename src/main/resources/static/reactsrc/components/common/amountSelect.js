import React from 'react';

const AmountSelect = ({ id, onChange, amounts, unitCode, defaultValue }) => {
    return (
        <select className="form-control amount-select"
                id={id}
                onChange={onChange}
                defaultValue={defaultValue}>
            {
                amounts.map(amount => 
                    <option key={amount}
                            value={amount}>
                        {`${amount} ${unitCode}`}
                    </option>)
            }
        </select>
    );
};

AmountSelect.propTypes = {
    id: React.PropTypes.string,
    onChange: React.PropTypes.func,
    amounts: React.PropTypes.array.isRequired,
    unitCode: React.PropTypes.string.isRequired,
    defaultSelected: React.PropTypes.func
};

export default AmountSelect;