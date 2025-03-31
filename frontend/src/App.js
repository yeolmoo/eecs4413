import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Navbar from './layout/Navbar';
import HomePage from './pages/HomePage';
import CatalogPage from './pages/CatalogPage';
import Login from './pages/Login';
import HotDealsPage from './components/HotDealsPage';
import RegisterPage from './pages/RegisterPage';
import Chatbot from './components/Chatbot';

function App() {
  const [chatOpen, setChatOpen] = useState(false);

  return (
    <Router>

      {chatOpen && <Chatbot onClose={() => setChatOpen(false)} />}

      <Routes>
    
        <Route path="/" element={<Navbar />}>
          <Route index element={<HomePage chatOpen={chatOpen} setChatOpen={setChatOpen} />} />
          <Route path="catalog" element={<CatalogPage />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<RegisterPage />} />
          <Route path="hot-deals" element={<HotDealsPage />} /> 
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
