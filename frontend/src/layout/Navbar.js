import React, { useState, useEffect } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import LoanCalculator from '../components/LoanCalculator';

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('isLoggedIn') === 'true');
  const navigate = useNavigate();
  const [showCalculator, setShowCalculator] = useState(false);

  useEffect(() => {
    const handleStorageChange = () => {
      setIsLoggedIn(localStorage.getItem('isLoggedIn') === 'true');
    };

    // Optional: sync across tabs too
    window.addEventListener('storage', handleStorageChange);
    return () => window.removeEventListener('storage', handleStorageChange);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('token');
    setIsLoggedIn(false);
    navigate('/login');
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-light bg-light px-4">
        <Link className="navbar-brand" to="/">EVShop</Link>

        <div className="ms-auto d-flex align-items-center gap-3">
          <Link to="/cart" className="btn btn-outline-secondary">Cart</Link>

          {!isLoggedIn ? (
            <>
              <Link className="btn btn-outline-primary" to="/login">Login</Link>
              <Link className="btn btn-primary" to="/register">Register</Link>
              <button className="btn btn-outline-success" onClick={() => setShowCalculator(true)}>Loan Calculator</button>
            </>
          ) : (
            <>
              <button className="btn btn-outline-danger" onClick={handleLogout}>Logout</button>
            </>
          )}
        </div>
      </nav>

      <div className="container mt-4">
        <Outlet />
      </div>

      {showCalculator && (
        <div className="loan-calculator-backdrop">
          <LoanCalculator onClose={() => setShowCalculator(false)} />
        </div>
      )}
    </>
  );
};

export default Navbar;
