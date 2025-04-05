import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.css'; 
import api from '../api';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setErrorMsg('');
    try {
      const response = await api.post('/auth/login', {
        username,
        password,
      });
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('isLoggedIn', 'true');
      window.dispatchEvent(new Event("storage"));
      navigate('/');
    } catch (err) {
      setErrorMsg('Login failed. Please check your credentials.');
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-card">
        <h1>Login</h1>
        <form onSubmit={handleLogin} className="login-form">
          <input
            className="form-input"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            className="form-input"
            placeholder="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          {errorMsg && <p className="error-text">{errorMsg}</p>}
          <button type="submit" className="form-button">Login</button>
        </form>
      </div>
    </div>
  );
}
