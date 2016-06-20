import React from 'react';

const AmountSelect = ({ id, onChange, amounts, unitCode, defaultSelected = (amount) => false }) => {
    return (
        <select className="form-control amount-select"
                    id={id}
                    onChange={onChange}>
            {
                amounts.map(amount => {
                        if (defaultSelected(amount))
                            return (
                                <option key={amount}
                                        value={amount}
                                        selected="selected">
                                    {`${amount} ${unitCode}`}
                                </option>);
                        else
                            return (
                                <option key={amount}
                                        value={amount}>
                                    {`${amount} ${unitCode}`}
                                </option>);
                    }
                )
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