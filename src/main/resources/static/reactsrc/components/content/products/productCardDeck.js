import React from 'react';
import ProductCard from './productCard.js';

const ProductCardDeck = () => {
    return (
        <div className="card-deck-wrapper">
            <div className="card-deck">
                <ProductCard productsCode="chicken"
                             titleText="Pilad"
                             paragraphText="Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard productsCode="honey"
                             titleText="Med"
                             paragraphText="Činjenica da med nema rok trajanja dovoljno govori o kvaliteti ovog proizvoda."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard productsCode="horse"
                             titleText="Konji"
                             paragraphText="Kažu da konjsko meso daje snagu i energiju za cijeli dan."
                             smallText="Ovo je samo privremeni tekst."/>
            </div>
        </div>
    );
};

export default ProductCardDeck;
