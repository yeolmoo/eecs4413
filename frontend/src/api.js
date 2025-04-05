import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL;

const api = axios.create({
  baseURL: BASE_URL || "http://localhost:8080",
  withCredentials: true, 
});

export default api;
