import axios from 'axios';
import type { IngredientDto } from '../dto/IngredientDto';

const api = axios.create({
  baseURL: '/api',
});

api.interceptors.request.use((config) => {
  config.headers.Authorization =
    'Basic ' + btoa('employee:palacinke');
  return config;
});

export const getIngredients = async (): Promise<IngredientDto[]> => {
    const response = await api.get<IngredientDto[]>('/ingredients');
    console.log(response.data);
    return response.data;
}
