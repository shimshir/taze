import React from 'react';
import { Link } from 'react-router';

const ProductCard = ({ product, titleText, paragraphText, smallText }) => {
    return (
        <div className="card">
            <Link to={`/products/${product.code}`}>
                <div className={'card-img-container ' + product.code}></div>
            </Link>
            <div className="card-block">
                <h4 className="card-title">{titleText}</h4>
                <p className="card-text">{paragraphText}</p>
                <p className="card-text">
                    <small className="text-muted">{smallText}</small>
                </p>
            </div>
        </div>
    );
};


ProductCard.propTypes = {
    product: React.PropTypes.object.isRequired,
    titleText: React.PropTypes.string.isRequired,
    paragraphText: React.PropTypes.string.isRequired,
    smallText: React.PropTypes.string
};

export default ProductCard;