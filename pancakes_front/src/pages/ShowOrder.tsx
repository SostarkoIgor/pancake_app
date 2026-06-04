import React from 'react'
import { useParams } from 'react-router-dom';
import type { OrderDto } from '../dto/OrderDto';
import { getOrder } from '../services/orderService';
import styles from '../styles/styles.module.css';
import PancakeCreator from '../components/PancakeCreator';
import { getPancakesNotInOrder, moveToOrder, removePancakeFromOrder } from '../services/pancakeService';
import type { PancakeDto } from '../dto/PancakeDto';
const ShowOrder = () => {
    const { id } = useParams();
    const [order, setOrder] = React.useState<OrderDto | null>(null);
    const [availablePancakes, setAvailablePancakes] = React.useState<PancakeDto[]>([]);
    React.useEffect(() => {
        if (!id) return;
        getOrder(parseInt(id)).then(setOrder);
        getPancakesNotInOrder().then(setAvailablePancakes);
    }, [id]);
    const refreshData = (pancake : PancakeDto) => {
        if (!id) return;
        setAvailablePancakes([...availablePancakes, pancake]);
        getOrder(parseInt(id)).then(setOrder);
    }
    const removePancake = (pancakeId: number) => {
        removePancakeFromOrder(pancakeId).then(() => {
            if (!id) return;
            getOrder(parseInt(id)).then(setOrder);
            getPancakesNotInOrder().then(setAvailablePancakes);
        }).catch(() => {
            alert('Failed to remove pancake from order!');
        });
    }
    const addPancake = (pancakeId: number) => {
        if (!id) return;
        moveToOrder(pancakeId, parseInt(id)).then(() => {
            if (!id) return;
            getOrder(parseInt(id)).then(setOrder);
            getPancakesNotInOrder().then(setAvailablePancakes);
        }).catch(() => {
            alert('Failed to add pancake to order!');
        });
    }
    return (<>
        <div className={styles.container}>
            <h1 className={styles.title}>Order {id}</h1>
            {order && (
                <div>
                    <p className={styles.element}>
                        <span className={styles.itemElementTitle}>Order ID:</span> {order.Id}
                    </p>
                    <p className={styles.element}>
                        <span className={styles.itemElementTitle}>Description:</span> {order.description}
                    </p>
                    <p className={styles.element}>
                        <span className={styles.itemElementTitle}>Order time:</span> {new Date(order.orderTime).toLocaleString("hr-HR", {
                            year: "numeric",
                            month: "2-digit",
                            day: "2-digit",
                        hour: "2-digit",
                        minute: "2-digit"
                        })}</p>
                    <p className={styles.element}>
                        <span className={styles.itemElementTitle}>Price:</span> {order.price}
                    </p>
                    <h3 className={styles.minorTitle}>Pancakes in order</h3>
                    {order.pancakes.map((pancake) => (
                        <div key={pancake.id} className={styles.pancakeContainer}>
                            <div className={styles.element}>
                                <p className={styles.itemElementTitle}>Pancake {pancake.id}</p>
                                <p className={styles.itemElementValue}>Price: {pancake.price}</p>
                                <p className={styles.itemElementValue}>Healthy: {pancake.healthy ? 'Yes' : 'No'}</p>
                                <button className={styles.removeButton} onClick={() => removePancake(pancake.id)}>Remove</button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
        <h2 className={styles.title}>Available pancakes</h2>
        <div className={styles.container}>
            {availablePancakes.map((pancake) => (
                <div key={pancake.id} className={styles.pancakeContainer}>
                    <div className={styles.element}>
                        <p className={styles.itemElementTitle}>Pancake {pancake.id}</p>
                        <p className={styles.itemElementValue}>Price: {pancake.price}</p>
                        <p className={styles.itemElementValue}>Healthy: {pancake.healthy ? 'Yes' : 'No'}</p>
                        <button className={styles.addButton} onClick={() => addPancake(pancake.id)}>Add</button>
                    </div>
                </div>
            ))}
        </div>
        <PancakeCreator refreshData={refreshData} />
    </>)
}

export default ShowOrder;