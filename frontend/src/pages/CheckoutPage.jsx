import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CheckoutPage = () => {
  const [billingInfo, setBillingInfo] = useState({
    fullName: '',
    email: '',
    phone: '',
    address: '',
    city: '',
    province: '',
    postalCode: '',
    country: '',
    cardNumber: '',
    expirationDate: '',
    cvv: ''
  });

  const [message, setMessage] = useState('');
  const [orderCompleted, setOrderCompleted] = useState(false);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBillingInfo({ ...billingInfo, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post('http://localhost:8080/order/checkout', billingInfo, {
        headers: { Authorization: `Bearer ${token}` }
      });

      setOrderCompleted(true);
      setMessage('Order placed successfully!');
    } catch (err) {
      console.error('Checkout failed:', err);
      setMessage('Failed to place order.');
    }
  };

  return (
    <div className="container mt-5">
      <h2>Checkout</h2>
      {!orderCompleted ? (
        <form onSubmit={handleSubmit}>
          {/* Contact Info */}
          <div className="mb-3">
            <label>Full Name</label>
            <input type="text" className="form-control" name="fullName" value={billingInfo.fullName} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Email</label>
            <input type="email" className="form-control" name="email" value={billingInfo.email} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Phone Number</label>
            <input type="tel" className="form-control" name="phone" value={billingInfo.phone} onChange={handleChange} required />
          </div>

          {/* Address Info */}
          <div className="mb-3">
            <label>Street Address</label>
            <input type="text" className="form-control" name="address" value={billingInfo.address} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>City</label>
            <input type="text" className="form-control" name="city" value={billingInfo.city} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Province/State</label>
            <input type="text" className="form-control" name="province" value={billingInfo.province} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Postal Code</label>
            <input type="text" className="form-control" name="postalCode" value={billingInfo.postalCode} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Country</label>
            <input type="text" className="form-control" name="country" value={billingInfo.country} onChange={handleChange} required />
          </div>

          {/* Payment Info */}
          <div className="mb-3">
            <label>Card Number</label>
            <input type="text" className="form-control" name="cardNumber" value={billingInfo.cardNumber} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>Expiration Date (MM/YY)</label>
            <input type="text" className="form-control" name="expirationDate" value={billingInfo.expirationDate} onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label>CVV</label>
            <input type="text" className="form-control" name="cvv" value={billingInfo.cvv} onChange={handleChange} required />
          </div>

          <button type="submit" className="btn btn-primary">Confirm Order</button>
        </form>
      ) : (
        <div className="alert alert-success mt-4">
          <h4>{message}</h4>
          <p>Your order has been placed successfully.</p>
          <button className="btn btn-outline-primary mt-3" onClick={() => navigate('/')}>
            Go to Home
          </button>
        </div>
      )}

      {message && !orderCompleted && <p className="mt-3 text-danger">{message}</p>}
    </div>
  );
};

export default CheckoutPage;
