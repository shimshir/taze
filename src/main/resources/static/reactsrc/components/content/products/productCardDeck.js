import React from 'react';
import ProductCard from './productCard.js';

const ProductCardDeck = () => {
    return (
        <div className="card-deck-wrapper">
            <div className="card-deck">
                <ProductCard productsCode="chicken"
                             imgSrc="/img/products/cards/chicken.jpg"
                             imgAlt="Pile"
                             titleText="Pilad"
                             paragraphText="Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard productsCode="dog" 
                             imgSrc="/img/products/cards/dog.jpg"
                             imgAlt="Cuke"
                             titleText="Cuke"
                             paragraphText="Ma hajde Boga ti, ionako ih ima previše po gradu."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard productsCode="horse"
                             imgSrc="/img/products/cards/horse.jpg"
                             imgAlt="Konje"
                             titleText="Konji"
                             paragraphText="Kažu da konjsko meso daje snagu i energiju za cijeli dan."
                             smallText="Ovo je samo privremeni tekst."/>
            </div>
        </div>
    );
};

export default ProductCardDeck;
