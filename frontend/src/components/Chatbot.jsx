import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Chatbot.css';

const Chatbot = ({ onClose }) => {
  const [messages, setMessages] = useState(() => {
    const saved = localStorage.getItem('chatMessages');
    return saved ? JSON.parse(saved) : [];
  });

  const [input, setInput] = useState('');

  // Save messages to localStorage every time it changes
  useEffect(() => {
    localStorage.setItem('chatMessages', JSON.stringify(messages));
  }, [messages]);

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
      setMessages(prev => [
        ...prev,
        { sender: 'bot', text: 'Error: Could not reach server.' }
      ]);
    }

    setInput('');
  };

  return (
    <div className="chatbot-modal">
      <div className="chatbot-box">
        <button className="btn-close" onClick={onClose}>×</button>

        <div className="chat-messages">
          {messages.map((msg, i) => (
            <div key={i} className={`chat-message ${msg.sender}`}>
              <div className="chat-message-label">
                {msg.sender === 'user' ? 'You' : 'EV Assistant'}
              </div>
              <div>{msg.text}</div>
            </div>
          ))}
        </div>

        <div className="chat-input">
          <input
            placeholder="Ask me anything..."
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => e.key === 'Enter' && handleSend()}
          />
          <button onClick={handleSend}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default Chatbot;
