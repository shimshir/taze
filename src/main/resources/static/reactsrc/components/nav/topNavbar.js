import React, { PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';

const TopNavbarView = () => {
    return (
        <nav className="navbar navbar-dark bg-inverse topnavbar">
            <Link className="navbar-brand" to="/">Taze</Link>
            <ul className="nav navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to="/about-us">O nama</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/gallery">Galerija</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/products">Proizvodi</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/contact">Kontakt</Link>
                </li>
            </ul>
        </nav>
    )
};

TopNavbarView.propTypes = {
};


const mapStateToProps = (state, ownProps) => {
    return {
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