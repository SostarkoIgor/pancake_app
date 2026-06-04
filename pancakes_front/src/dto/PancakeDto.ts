import { IngredientDto } from "./IngredientDto";
export class PancakeDto{
    id!: number;
    price!: number;
    ingredients!: IngredientDto[];
    healthy!: boolean;
}