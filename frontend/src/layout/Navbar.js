import React, { useState } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import LoanCalculator from '../components/LoanCalculator'; 

const Navbar = () => {
  const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
  const navigate = useNavigate();


  const [showCalculator, setShowCalculator] = useState(false);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    navigate('/login');
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-light bg-light px-4">
        <Link className="navbar-brand" to="/">EVShop</Link>

        <div className="ms-auto d-flex align-items-center gap-3">
          {!isLoggedIn ? (
            <>
              <Link className="btn btn-outline-primary" to="/login">Login</Link>
              <Link className="btn btn-primary" to="/register">Register</Link>
              <button className="btn btn-outline-success" onClick={() => setShowCalculator(true)}>Loan Calculator</button>
            </>
          ) : (
            <>
              <button className="btn btn-outline-danger" onClick={handleLogout}>Logout</button>
              <Link to="/cart" className="btn btn-outline-secondary">Cart</Link>
            </>
          )}
        </div>
      </nav>

      <div className="container mt-4">
        <Outlet />
      </div>

      {/* Loan Calculator */}
      {showCalculator && (
        <div className="loan-calculator-backdrop">
          <LoanCalculator onClose={() => setShowCalculator(false)} />
        </div>
      )}
    </>
  );
};

export default Navbar;
