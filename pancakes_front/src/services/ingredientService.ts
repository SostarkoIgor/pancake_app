import axios from 'axios';
import type { IngredientDto } from '../dto/IngredientDto';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

api.interceptors.request.use((config) => {
  config.headers.Authorization =
    'Basic ' + btoa('employee:palacinke');
  return config;
});

export const getIngredients = async (): Promise<IngredientDto[]> => {
    const response = await api.get<IngredientDto[]>('/ingredients');
    return response.data;
}
