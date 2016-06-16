import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changeActiveTopNavbarItemDispatchMapping} from '../../../common/commonMappings.js';
import ProductDetail from '../productDetail.js';
import Stage from '../../../stage/stage.js';
import ContentContainer from '../../../common/contentContainer.js';
import {CHICKEN} from '../../../../constants/constants.js';

class ChickenView extends Component {
    componentWillMount() {
        this.props.changeActiveTopNavbarItem('products')
    }
    render() {
        return (
            <div>
                <Stage headerText="Pilad" stageBackgroundClass="chicken"/>
                <ContentContainer>
                    <ProductDetail product={CHICKEN}
                                   imageSrc="/img/products/chicken-pdp.jpg"
                                   additionalText="* Kilaža se zaokružuje da bude cio broj piladi."/>
                </ContentContainer>
            </div>
        );
    }
}


const Chicken = connect(undefined, changeActiveTopNavbarItemDispatchMapping)(ChickenView);
export default Chicken;