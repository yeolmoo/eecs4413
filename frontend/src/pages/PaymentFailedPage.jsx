import React from 'react';
import { Link } from 'react-router-dom';

const PaymentFailedPage = () => {
  return (
    <div className="container mt-5 text-center">
      <h2> Credit Card Authorization Failed</h2>
      <p>Please check your payment information and try again.</p>
      <Link to="/cart" className="btn btn-outline-primary mt-3">Return to Cart</Link>
    </div>
  );
};

export default PaymentFailedPage;
