import React, {Component} from 'react';
import {connect} from 'react-redux';
import ProductCard from './productCard.js';
import {asyncGetProductCardsAction} from '../../../actions/actions.js';

class ProductCardDeckView extends Component {

    componentWillMount() {
        console.log("mounting productCardDec");
        this.props.getProductCards();
    }

    render() {
        return (
            <div className="card-deck-wrapper">
                <div className="card-deck">
                    {this.props.productCards.map(productCard => <ProductCard key={productCard.id} productCard={productCard}/>)}
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        productCards: state.productCards
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getProductCards: () => asyncGetProductCardsAction(dispatch)
    }
};

const ProductCardDeck = connect(mapStateToProps, mapDispatchToProps)(ProductCardDeckView);
export default ProductCardDeck;
