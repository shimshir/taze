import React, { PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';

const TopNavbarView = () => {
    return (
        <nav className="navbar navbar-inverse topnavbar">
            <div className="navbar-header">
                <Link className="navbar-brand" to="/">Taze</Link>
            </div>

            <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul className="nav navbar-nav navbar-left">
                    <li>
                        <Link to="/home">Početna</Link>
                    </li>
                    <li>
                        <Link to="/about-us">O nama</Link>
                    </li>
                    <li>
                        <Link to="/gallery">Galerija</Link>
                    </li>
                    <li>
                        <Link to="/products">Proizvodi</Link>
                    </li>
                    <li>
                        <Link to="/contact">Kontakt</Link>
                    </li>
                </ul>
            </div>
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