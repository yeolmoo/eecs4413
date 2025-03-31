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
import CartPage from './pages/CartPage';
import CheckoutPage from './pages/CheckoutPage';
import OrderConfirmationPage from './pages/OrderConfirmationPage';
import PaymentFailedPage from './pages/PaymentFailedPage';
import OrderHistoryPage from './pages/OrderHistoryPage';
import VehicleDetail from './components/VehicleDetail';

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
        <Route path="cart" element={<CartPage />} /> 
        <Route path="checkout" element={<CheckoutPage />} />
        <Route path="order-confirmation" element={<OrderConfirmationPage />} />
        <Route path="/payment-failed" element={<PaymentFailedPage />} />
        <Route path="/order-history" element={<OrderHistoryPage />} />
        <Route path="/vehicle/:id" element={<VehicleDetail />} />

      </Route>
    </Routes>
  </Router>


  );
}

export default App;
