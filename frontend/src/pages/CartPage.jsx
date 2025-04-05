import React, { useEffect, useState, useCallback } from 'react';

import { useNavigate } from 'react-router-dom';
import api from '../api';

const CartPage = () => {
  const [cart, setCart] = useState(null);
  const navigate = useNavigate();

  const token = localStorage.getItem('token');

const fetchCart = useCallback(() => {
  api.get('/cart', {
    headers: { Authorization: `Bearer ${token}` }
  }).then(res => {
    setCart(res.data);
  }).catch(err => {
    console.error('Failed to load cart:', err);
  });
}, [token]);

useEffect(() => {
  fetchCart();
}, [fetchCart]);


  const removeFromCart = (cartItemId) => {
    api.delete(`/cart/remove/${cartItemId}`, {
      headers: { Authorization: `Bearer ${token}` }
    }).then(() => {
      fetchCart();
    });
  };

  const updateQuantity = (cartItemId, quantity) => {
    api.put(`/cart/editQuantity/${cartItemId}?quantity=${quantity}`, null, {
      headers: { Authorization: `Bearer ${token}` }
    }).then(() => {
      fetchCart();
    });
  };

  return (
    <div className="container mt-5">
      <h2>Your Cart</h2>
      {cart && cart.cartItems.length > 0 ? (
        <>
          <ul className="list-group mb-4">
            {cart.cartItems.map(item => (
              <li key={item.id} className="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <strong>{item.vehicle.brand} {item.vehicle.model}</strong> (${item.vehicle.price.toLocaleString()})
                  <br />
                  Quantity:
                  <input
                    type="number"
                    value={item.quantity}
                    min={1}
                    style={{ width: '60px', marginLeft: '10px' }}
                    onChange={(e) => updateQuantity(item.id, e.target.value)}
                  />
                </div>
                <div className="d-flex gap-2">
                  <button className="btn btn-sm btn-danger" onClick={() => removeFromCart(item.id)}>Remove</button>
                </div>
              </li>
            ))}
          </ul>
          <h5>Total: ${cart.totalPrice.toLocaleString()}</h5>
          <button className="btn btn-primary" onClick={() => navigate('/checkout')}>
            Proceed to Checkout
          </button>
        </>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
};

export default CartPage;
