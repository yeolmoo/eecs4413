import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Navbar from './layout/Navbar';
import HomePage from './pages/HomePage';
import CatalogPage from './pages/CatalogPage';
import Login from './pages/Login';
import ChatPanel from './components/ChatPanel';
import RegisterPage from './pages/RegisterPage';

function App() {
  const [chatOpen, setChatOpen] = useState(false);

  return (
    <Router>
      {chatOpen && <ChatPanel onClose={() => setChatOpen(false)} />}
      <Routes>
        <Route path="/" element={<Navbar />}>
          <Route index element={<HomePage chatOpen={chatOpen} setChatOpen={setChatOpen} />} />
          <Route path="catalog" element={<CatalogPage />} />
          <Route path="login" element={<Login />} />
          <Route path="/register" element={<RegisterPage />} />

        </Route>
      </Routes>
    </Router>
  );
}

export default App;
