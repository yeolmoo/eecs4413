import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './RegisterPage.css';

function RegisterPage() {
  const [username, setUsername] = useState('');
  const [email, setEmail]     = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setErrorMsg('');

    try {
      await axios.post('http://localhost:8080/auth/register', {
        username,
        email,
        password,
      });
      navigate('/login');
    } catch (error) {
      if (error.response) {
        setErrorMsg(error.response.data);
      } else {
        setErrorMsg("Registration failed. Try again.");
      }
    }
  };
  return (
    <div className="register-wrapper">
      <div className="register-card">
        <h1>Register</h1>
        <form onSubmit={handleRegister} className="register-form">
          <input
            className="form-input"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            className="form-input"
            placeholder="Email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
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
          <button type="submit" className="form-button">Register</button>
        </form>
      </div>
    </div>
  );
};
export default RegisterPage;
