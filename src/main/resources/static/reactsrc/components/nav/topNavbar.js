import React, { PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';

const TopNavbarView = ({ activeTopNavbarItem, cart }) => {
    const cartEntriesAmount = cart.cartEntries ? cart.cartEntries.length : 0;
    return (
        <nav className="navbar navbar-dark bg-inverse topnavbar">
            <Link className="navbar-brand" to="/">Taze</Link>
            <ul className="nav navbar-nav">
                <li className={'nav-item ' + (activeTopNavbarItem === 'about-us' ? 'active' : '')}>
                    <Link className="nav-link" to="/about-us">O nama</Link>
                </li>
                <li className={'nav-item ' + (activeTopNavbarItem === 'gallery' ? 'active' : '')}>
                    <Link className="nav-link" to="/gallery">Galerija</Link>
                </li>
                <li className={'nav-item ' + (activeTopNavbarItem === 'products' ? 'active' : '')}>
                    <Link className="nav-link" to="/products">Proizvodi</Link>
                </li>
                <li className={'nav-item ' + (activeTopNavbarItem === 'contact' ? 'active' : '')}>
                    <Link className="nav-link" to="/contact">Kontakt</Link>
                </li>
                <li className={'nav-item pull-xs-right ' + (activeTopNavbarItem === 'cart' ? 'active' : '')}>
                    <Link className="nav-link shopping-cart-link" to="/cart">
                        <i className="fa fa-shopping-cart nav-shopping-cart"/>
                        <span className={'shopping-cart-amount ' + (cartEntriesAmount > 0 ? 'nonempty' : '')}>
                            {cartEntriesAmount > 0 ? cartEntriesAmount : ''}
                        </span>
                    </Link>
                </li>
            </ul>
        </nav>
    );
};

TopNavbarView.propTypes = {
};


const mapStateToProps = (state, ownProps) => {
    return {
        activeTopNavbarItem: state.activeTopNavbarItem,
        cart: state.cart
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
    }
};

const TopNavbar = connect(
    mapStateToProps,
    mapDispatchToProps
)(TopNavbarView);

export default TopNavbar;