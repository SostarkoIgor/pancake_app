import React from 'react';
import styles from '../styles/styles.module.css';
import PancakeCreator from '../components/PancakeCreator';
import { getPancakesNotInOrder } from '../services/pancakeService';
import type { PancakeDto } from '../dto/PancakeDto';
import { createOrder } from '../services/orderService';
import { OrderRequestDto } from '../dto/OrderRequestDto';
const CreateOrder = () => {
    const [availablePancakes, setAvailablePancakes] = React.useState<PancakeDto[]>([]);
    const [selectedPancakes, setSelectedPancakes] = React.useState<PancakeDto[]>([]);
    const [description, setDescription] = React.useState<string>('');
    React.useEffect(() => {
        getPancakesNotInOrder().then(setAvailablePancakes);
    }, []);
    const refreshData = (pancake: PancakeDto) => {
        setAvailablePancakes([...availablePancakes, pancake]);
    }
    const removePancake = (pancake: PancakeDto) => {
        setSelectedPancakes(selectedPancakes.filter((p) => p.id !== pancake.id));
        setAvailablePancakes([...availablePancakes, pancake]);
    }
    const addPancake = (pancake: PancakeDto) => {
        setSelectedPancakes([...selectedPancakes, pancake]);
        setAvailablePancakes(availablePancakes.filter((p) => p.id !== pancake.id));
    }
    const createOrder_ = () => {
        if (selectedPancakes.length === 0) {
            alert('Failed to create order!');
            return;
        }
        const dto: OrderRequestDto = {
            description,
            pancakeIds: selectedPancakes.map((p) => p.id)
        }
        createOrder(dto).then((res) => {
            setSelectedPancakes([]);
            setDescription('');
            getPancakesNotInOrder().then(setAvailablePancakes);
            alert('Order created successfully!');
            window.location.href = '/order/' + res.Id;
        }).catch(() => {
            alert('Failed to create order!');
        });
    }
    return (<>
        <div className={styles.container}>
            <h1 className={styles.title}>Create order</h1>
            <label className={styles.label}>Description:</label>
            <input className={styles.input} type="text" value={description} onChange={(e) => setDescription(e.target.value)} />
            <button className={styles.addButton} onClick={createOrder_}>Create order</button>
         </div>
        <h2 className={styles.title}>Selected pancakes</h2>
        <div className={styles.container}>
            {selectedPancakes.map((pancake) => (
                <div key={pancake.id} className={styles.pancakeContainer}>
                    <div className={styles.element}>
                        <p className={styles.itemElementTitle}>Pancake {pancake.id}</p>
                        <button className={styles.removeButton} onClick={() => removePancake(pancake)}>Remove</button>
                    </div>
                </div>
            ))}
            {selectedPancakes.length === 0 && <p>No pancakes selected.</p>}
        </div>
        <h2 className={styles.title}>Available pancakes</h2>
        <div className={styles.container}>
            {availablePancakes.map((pancake) => (
                <div key={pancake.id} className={styles.pancakeContainer}>
                    <div className={styles.element}>
                        <p className={styles.itemElementTitle}>Pancake {pancake.id}</p>
                        <p className={styles.itemElementValue}>Price: {pancake.price}</p>
                        <p className={styles.itemElementValue}>Healthy: {pancake.healthy ? 'Yes' : 'No'}</p>
                        <button className={styles.addButton} onClick={() => addPancake(pancake)}>Add</button>
                    </div>
                </div>
            ))}
            {availablePancakes.length === 0 && <p>No available pancakes.</p>}
        </div>
        <PancakeCreator refreshData={refreshData} />
    </>)
}


export default CreateOrder;