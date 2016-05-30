import React from 'react';
import { Link } from 'react-router';

const ProductCard = ({ productsCode, imgSrc, imgAlt, titleText, paragraphText, smallText }) => {
    return (
        <div className="card">
            <Link to={`/products/${productsCode}`}>
                <div className={'card-img-container ' + productsCode}></div>
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

export default ProductCard;