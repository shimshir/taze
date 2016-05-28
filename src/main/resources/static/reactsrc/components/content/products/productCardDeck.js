import React from 'react';
import ProductCard from './productCard.js';

const ProductCardDeck = () => {
    return (
        <div className="card-deck-wrapper">
            <div className="card-deck">
                <ProductCard imgSrc="/img/products/cards/cat.jpg"
                             imgAlt="Mace"
                             titleText="Mačke"
                             paragraphText="Naše mačke su najukusnije mačke što ćete naći u cijeloj BiH."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard imgSrc="/img/products/cards/dog.jpg"
                             imgAlt="Cuke"
                             titleText="Cuke"
                             paragraphText="Ma hajde Boga ti, ionako ih ima previše po gradu."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard imgSrc="/img/products/cards/horse.jpg"
                             imgAlt="Konje"
                             titleText="Konji"
                             paragraphText="Kažu da konjsko meso daje snagu i energiju za cijeli dan."
                             smallText="Ovo je samo privremeni tekst."/>
            </div>
        </div>
    );
};

export default ProductCardDeck;
