import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
});

api.interceptors.request.use((config) => {
  const token = 'Basic ' + btoa('customer:croz');
  config.headers['Authorization'] = token;
  return config;
});

export default api;