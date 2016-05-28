import React from 'react';

const ProductCard = ({ imgSrc, imgAlt, titleText, paragraphText, smallText }) => {
    return (
        <div className="card">
            <img className="card-img-top" src={imgSrc}
                 alt={imgAlt}/>
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