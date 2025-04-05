import React, { useEffect, useState } from 'react';
import api from "../api";
import { useLocation } from 'react-router-dom';

const OrderHistoryPage = () => {
  const [orders, setOrders] = useState([]);
  const token = localStorage.getItem('token');
  const location = useLocation();

  useEffect(() => {
    const newOrder = location.state?.newOrder;
    if (newOrder) {
      setOrders([newOrder]); // show just the recent one
    } else {
      api.get('/order/history'), {
        headers: { Authorization: `Bearer ${token}` }
      }).then(res => {
        setOrders(res.data);
      }).catch(err => {
        console.error('Failed to fetch order history:', err);
      });
    }
  }, [token, location.state]);

  return (
    <div className="container mt-5">
      <h2>Your Order History</h2>
      {orders.length > 0 ? (
        orders.map(order => (
          <div key={order.id} className="card mb-4">
            <div className="card-header">
              <strong>Order #{order.id}</strong> — {new Date(order.orderDate).toLocaleString()}
              <span className="ms-3 badge bg-info">{order.status}</span>
            </div>
            <ul className="list-group list-group-flush">
  {order.items?.map((item, idx) => (
    <li key={idx} className="list-group-item d-flex justify-content-between align-items-center">
      <span>{item.vehicle?.brand} {item.vehicle?.model} x{item.quantity}</span>
      <span>${item.price.toLocaleString()}</span>
    </li>
  ))}
</ul>
            <div className="card-footer text-end">
              <strong>Total:</strong> ${order.totalPrice.toLocaleString()}
            </div>
          </div>
        ))
      ) : (
        <p>No orders found.</p>
      )}
    </div>
  );
};

export default OrderHistoryPage;
