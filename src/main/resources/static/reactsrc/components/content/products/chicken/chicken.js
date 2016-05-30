import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';
import ProductDetail from '../productDetail.js';

class ChickenView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products')
    }
    render() {
        return (
            <ProductDetail unitCode="kg"/>
        );
    }
}


const Chicken = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(ChickenView);
export default Chicken;