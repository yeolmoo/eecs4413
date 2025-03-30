import React, { useState } from 'react';
import './FAB.css';

const FAB = () => {
  const [open, setOpen] = useState(false);
  const [showCalculator, setShowCalculator] = useState(false);
  const [showChatbot, setShowChatbot] = useState(false);

  return (
    <>
      {/* FAB */}
      <div className="fab-container">
        {open && (
          <div className="fab-actions">
            <button className="fab-action-btn" onClick={() => setShowCalculator(true)}>
               Calculator
            </button>
            <button className="fab-action-btn" onClick={() => setShowChatbot(true)}>
               Chatbot
            </button>
          </div>
        )}

        <button className="fab-main" onClick={() => setOpen(!open)}>
          {open ? '×' : '+'}
        </button>
      </div>

      {/* Calculator Modal */}
      {showCalculator && (
        <div className="fab-modal">
          <div className="fab-modal-content">
            <h4>Loan Calculator</h4>
            <p>(TODO: Calculator)</p>
            <button onClick={() => setShowCalculator(false)}>Close</button>
          </div>
        </div>
      )}

      {/* Chatbot Modal */}
      {showChatbot && (
        <div className="fab-modal">
          <div className="fab-modal-content">
            <h4>Chatbot</h4>
            <p>(TODO: chatbot)</p>
            <button onClick={() => setShowChatbot(false)}>Close</button>
          </div>
        </div>
      )}
    </>
  );
};

export default FAB;
