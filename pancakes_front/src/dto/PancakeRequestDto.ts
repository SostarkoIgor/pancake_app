export class PancakeRequestDto {
    ingredients!: number[];

    constructor(ingredients: number[]) {
        this.ingredients = ingredients;
    }
}