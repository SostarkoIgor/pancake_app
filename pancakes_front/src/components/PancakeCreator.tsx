import React from 'react'
import { getIngredients } from '../services/ingredientService';
import type { IngredientDto } from '../dto/IngredientDto';
import styles from '../styles/styles.module.css';
import { createPancake } from '../services/pancakeService';

const PancakeCreator = ({ refreshData }: { refreshData: (pancake: any) => void }) => {
    const [ingredients, setIngredients] = React.useState<IngredientDto[]>([]);
    const [selectedIngredients, setSelectedIngredients] = React.useState<number[]>([]);
    React.useEffect(() => {
        getIngredients().then(setIngredients);
    }, []);
    const createPancake_ = () => {
        createPancake(selectedIngredients)
        .then((res) => {
            const pancakeDto = res;

            setSelectedIngredients([]);

            refreshData(pancakeDto);

            alert('Pancake created successfully!');
        })
        .catch(() => {
            setSelectedIngredients([]);
            alert('Failed to create pancake!');
        });
    }
    return (
        <div className={styles.container}>
            <h1 className={styles.title}>Create pancake</h1>
            <div className={styles.ingredientList}>
                {ingredients.map((ingredient) => (
                    <div key={ingredient.id} data-id={ingredient.id} className={selectedIngredients.includes(ingredient.id) ? styles.selectedIngredient : styles.ingredient} onClick={(e) => {
                            const id = parseInt(e.currentTarget.dataset.id || '0');
                            if (!selectedIngredients.includes(id)) {
                                setSelectedIngredients([...selectedIngredients, id]);
                            } else {
                                setSelectedIngredients(selectedIngredients.filter((i) => i !== id));
                            }
                        }}>
                        <a>{ingredient.name}</a>
                        <span className={styles.price}>Price: {ingredient.price}</span>
                        <span className={styles.category}>Category: {ingredient.category}</span>
                        <span className={styles.healthy}>Healthy: {ingredient.healthy ? 'Yes' : 'No'}</span>
                    </div>
                ))}
                {ingredients.length === 0 && <p>No ingredients found.</p>}
            </div>
            <button className={styles.createButton} onClick={createPancake_}>Create pancake</button>
        </div>

    )
}

export default PancakeCreator;