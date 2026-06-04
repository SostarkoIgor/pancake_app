import { PancakeDto } from "./PancakeDto";
export class OrderDto{
    Id!: number;
    description!: string;
    orderTime!: string;
    price!: number;
    pancakes!: PancakeDto[];
}