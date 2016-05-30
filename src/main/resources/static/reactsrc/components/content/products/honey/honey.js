import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changeActiveTopNavbarItemDispatchMapping} from '../../common/commonMappings.js';
import ProductDetail from '../productDetail.js';
import Stage from '../../../stage/stage.js';
import ContentContainer from '../../common/contentContainer.js';

class HoneyView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products')
    }
    render() {
        return (
            <div>
                <Stage headerText="Med" stageBackgroundClass="honey"/>
                <ContentContainer>
                    <ProductDetail pricePerUnit={10}
                                   unitCode="l"
                                   imageSrc="/img/products/honey-pdp.jpg"
                                   headerText="Med"/>
                </ContentContainer>
            </div>
        );
    }
}


const Honey = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(HoneyView);
export default Honey;