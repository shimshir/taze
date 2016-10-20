import React from 'react';
import ProductCard from './productCard.js';

const ProductCardDeck = ({products}) => {

    const chicken = products.find(product => product.code == 'chicken');
    const honey = products.find(product => product.code == 'honey');
    const horse = products.find(product => product.code == 'horse');
    return (
        // TODO: Definitely needs to be changed, make the ProductCard drawing dynamic
        products.length == 3 ?
        <div className="card-deck-wrapper">
            <div className="card-deck">
                <ProductCard product={chicken}
                             titleText="Pilad"
                             paragraphText="Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard product={honey}
                             titleText="Med"
                             paragraphText="Činjenica da med nema rok trajanja dovoljno govori o kvaliteti ovog proizvoda."
                             smallText="Ovo je samo privremeni tekst."/>
                <ProductCard product={horse}
                             titleText="Konji"
                             paragraphText="Kažu da konjsko meso daje snagu i energiju za cijeli dan."
                             smallText="Ovo je samo privremeni tekst."/>
            </div>
        </div>
            :
        null
    );
};

ProductCardDeck.PropTypes = {
    products: React.PropTypes.array.isRequired
};

export default ProductCardDeck;
