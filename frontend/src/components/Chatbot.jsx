// src/components/Chatbot.jsx
import React, { useState } from 'react';
import axios from 'axios';
import './Chatbot.css'; // 스타일 따로 만들면 좋아

const Chatbot = ({ onClose }) => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  const handleSend = async () => {
    if (!input.trim()) return;

    const userMessage = { sender: 'user', text: input };
    setMessages(prev => [...prev, userMessage]);

    try {
      const response = await axios.post('http://localhost:8080/chatbot/message', null, {
        params: { message: input }
      });
      const botMessage = { sender: 'bot', text: response.data };
      setMessages(prev => [...prev, botMessage]);
    } catch (error) {
      setMessages(prev => [...prev, { sender: 'bot', text: 'Error: Could not reach server.' }]);
    }

    setInput('');
  };

  return (
    <div className="chatbot-modal">
      <div className="chatbot-box">
        <button className="btn-close" onClick={onClose}></button>
        <div className="chat-messages">
          {messages.map((msg, i) => (
            <div key={i} className={`chat-message ${msg.sender}`}>
              {msg.text}
            </div>
          ))}
        </div>
        <div className="chat-input">
          <input value={input} onChange={(e) => setInput(e.target.value)} onKeyDown={(e) => e.key === 'Enter' && handleSend()} />
          <button onClick={handleSend}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default Chatbot;
