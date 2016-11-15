import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router';

class SidebarView extends Component {
    state = {
        collapseExpandClasses: {
            products: 'collapsed',
            pages: 'collapsed'
        }
    };

    componentWillMount() {
        console.log("sidebar page mounted");
    }

    toggleCollapseExpandClass = (parent) => {
        const classes = {...this.state.collapseExpandClasses};
        classes[parent] = classes[parent] == 'collapsed' ? 'expanded' : 'collapsed';
        this.setState({collapseExpandClasses: classes});
    };

    render() {
        return (
            <nav className="navbar navbar-dark bg-inverse sidebar">
                <ul className="nav navbar-nav">
                    <li className={`parent-list-item ${this.state.collapseExpandClasses.products}`}>
                        <div className="nav-link fake-link" onClick={() => this.toggleCollapseExpandClass('products')}>Products</div>
                        <ul>
                            <li>
                                <Link className="nav-link" to="/admin/products/search">Search</Link>
                            </li>
                            <li>
                                <Link className="nav-link" to="/admin/products/create">Create</Link>
                            </li>
                        </ul>
                    </li>
                    <li className={`parent-list-item ${this.state.collapseExpandClasses.pages}`}>
                        <div className="nav-link fake-link" onClick={() => this.toggleCollapseExpandClass('pages')}>Pages</div>
                        <ul>
                            <li>
                                <Link className="nav-link" to="/admin/pages/search">Search</Link>
                            </li>
                            <li>
                                <Link className="nav-link" to="/admin/pages/create">Create</Link>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        )
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {}
};

const Sidebar= connect(undefined, mapDispatchToProps)(SidebarView);
export default Sidebar;
