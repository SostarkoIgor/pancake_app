import api from './api';
import { OrderDto } from '../dto/OrderDto';
import { OrderRequestDto } from '../dto/OrderRequestDto';

export const getOrders = async (): Promise<OrderDto[]> => {
  const response = await api.get<OrderDto[]>('/orders');
  return response.data;
}

export const getOrder = async (id: number): Promise<OrderDto> => {
  const response = await api.get<OrderDto>(`/orders/${id}`);
  return response.data;
}

export const createOrder = async (dto: OrderRequestDto): Promise<OrderDto> => {
    const response = await api.post<OrderDto>('/orders', dto);
    return response.data;
}