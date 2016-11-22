import React from 'react';

const Price = ({value}) => {
    return (
        <span>
            {value.formatMoney(2, ',', '.')} KM
        </span>
    );
};

Price.propTypes = {
    value: React.PropTypes.number
};

export default Price;
