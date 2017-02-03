import {RECEIVE_ORDERS_ACTION} from '../../actions/admin/actions.js';

const ordersReducer = (orders = [], action) => {
    switch (action.type) {
        case RECEIVE_ORDERS_ACTION:
            return action.orders;
        default:
            return orders
    }
};

export default ordersReducer;
