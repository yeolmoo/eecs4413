import React, { useState } from 'react';
import './LoanCalculator.css';

const LoanCalculator = ({ onClose }) => {
  const [price, setPrice] = useState('');
  const [term, setTerm] = useState('');
  const [interestRate, setInterestRate] = useState('');
  const [downPayment, setDownPayment] = useState('');
  const [monthlyPayment, setMonthlyPayment] = useState(null);

  const calculatePayment = () => {
    const loanAmount = price - downPayment;
    const monthlyRate = interestRate / 100 / 12;
    const numPayments = term;

    if (loanAmount <= 0 || monthlyRate <= 0 || numPayments <= 0) {
      setMonthlyPayment(0);
      return;
    }

    const payment =
      (loanAmount * monthlyRate) /
      (1 - Math.pow(1 + monthlyRate, -numPayments));

    setMonthlyPayment(payment.toFixed(2));
  };

  return (
    <div className="loan-calculator card p-4 shadow-sm position-relative">
      <button
  className="loan-close-btn"
  onClick={onClose}
  aria-label="Close"
>
  &times;
</button>

      <h3 className="mb-4">Loan Calculator</h3>

      <div className="mb-3">
        <label>Vehicle Price ($)</label>
        <input type="number" className="form-control" value={price} onChange={(e) => setPrice(Number(e.target.value))} />
      </div>

      <div className="mb-3">
        <label>Down Payment ($)</label>
        <input type="number" className="form-control" value={downPayment} onChange={(e) => setDownPayment(Number(e.target.value))} />
      </div>

      <div className="mb-3">
        <label>Loan Term (months)</label>
        <input type="number" className="form-control" value={term} onChange={(e) => setTerm(Number(e.target.value))} />
      </div>

      <div className="mb-3">
        <label>Annual Interest Rate (%)</label>
        <input type="number" className="form-control" value={interestRate} onChange={(e) => setInterestRate(Number(e.target.value))} />
      </div>

      <button className="btn btn-success w-100" onClick={calculatePayment}>Calculate</button>

      {monthlyPayment !== null && (
        <div className="alert alert-info mt-4">
          Estimated Monthly Payment: <strong>${monthlyPayment}</strong>
        </div>
      )}
    </div>
  );
};

export default LoanCalculator;
