import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const OrderConfirmationPage = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const order = state?.newOrder;

  return (
    <div className="container mt-5">
      <h2>🎉 Order Confirmed!</h2>
      {order ? (
        <>
          <p>Thank you for your purchase. Your order ID is <strong>{order.id}</strong>.</p>
          <p>Total: <strong>${order.totalPrice.toLocaleString()}</strong></p>
          <button className="btn btn-primary mt-3" onClick={() => navigate('/order-history')}>
            View Order History
          </button>
        </>
      ) : (
        <p>We received your order. Please check your order history for details.</p>
      )}
    </div>
  );
};

export default OrderConfirmationPage;
