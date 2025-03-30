import React from 'react';
import './FAB.css';

const FAB = ({ isChatOpen, toggleChat }) => {
  return (
    <div className="fab-container">
      <button className="fab-main" onClick={toggleChat}>
        {isChatOpen ? '✖' : '💬'}
      </button>
    </div>
  );
};

export default FAB;
