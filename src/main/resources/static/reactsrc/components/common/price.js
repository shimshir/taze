import React from 'react';

const Price = ({value}) => {
    return (
        value ?
            <span>
                {value.formatMoney(2, ',', '.')} KM
            </span>
            :
            null
    );
};

Price.propTypes = {
    value: React.PropTypes.number
};

export default Price;
