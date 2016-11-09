import React from 'react';
import {Link} from 'react-router';

const ProductCard = ({productCard}) => {
    return (
        <div className="card">
            <Link to={`/products/${productCard.transientProductCode}`}>
                <div className="card-img-container" style={{backgroundImage: `url(${productCard.image})`}}></div>
            </Link>
            <div className="card-block">
                <h4 className="card-title">{productCard.title}</h4>
                <p className="card-text">{productCard.paragraph}</p>
                <p className="card-text">
                    <small className="text-muted">{productCard.small}</small>
                </p>
            </div>
        </div>
    );
};

ProductCard.propTypes = {
    productCard: React.PropTypes.object.isRequired
};

export default ProductCard;