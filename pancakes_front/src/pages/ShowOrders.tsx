
import React from 'react';
import { getOrders } from '../services/orderService';
import type { OrderDto } from '../dto/OrderDto';
import styles from '../styles/styles.module.css';

const ShowOrders = () => {
    const [orders, setOrders] = React.useState<OrderDto[]>([]);
    React.useEffect(() => {
        getOrders().then(setOrders);
    }, []);
    return (
        <div className={styles.container}>
            <h1 className={styles.title}>Orders</h1>
            <ul className={styles.items}>
                {orders.map((order) => (
                    <li key={order.Id} className={styles.item} onClick={() => window.location.href = `/order/${order.Id}`}>
                        <div className={styles.itemElement}>
                            <span className={styles.itemElementTitle}>Order ID:</span>
                            <span className={styles.itemElementValue}> {order.Id}</span>
                        </div>
                        <div className={styles.itemElement}>
                            <span className={styles.itemElementTitle}>Order time:</span>
                            <span className={styles.itemElementValue}> {new Date(order.orderTime).toLocaleString("hr-HR", {
                                        year: "numeric",
                                        month: "2-digit",
                                        day: "2-digit",
                                        hour: "2-digit",
                                        minute: "2-digit"
                                        })}</span>
                        </div>
                    </li>
                ))}
                {orders.length === 0 && <p>No orders found.</p>}
            </ul>
        </div>
    )
}

export default ShowOrders;