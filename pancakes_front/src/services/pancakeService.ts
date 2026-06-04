import api from './api';
import type { PancakeDto } from '../dto/PancakeDto';
import { PancakeRequestDto } from '../dto/PancakeRequestDto';

export const removePancakeFromOrder = async (pancakeId: number): Promise<PancakeDto> => {
    const response = await api.delete<PancakeDto>(`/pancakes/${pancakeId}/order`);
    return response.data;
}

export const moveToOrder = async (pancakeId: number, orderId: number): Promise<PancakeDto> => {
    const response = await api.post<PancakeDto>(`/pancakes/${pancakeId}/order/${orderId}`);
    return response.data;
}

export const createPancake = async (ingredientIds: number[]): Promise<PancakeDto> => {
    const response = await api.post<PancakeDto>('/pancakes', new PancakeRequestDto(ingredientIds));
    return response.data;
}

export const getPancakesNotInOrder = async (): Promise<PancakeDto[]> => {
    const response = await api.get<PancakeDto[]>('/pancakes/unassigned');
    return response.data;
}