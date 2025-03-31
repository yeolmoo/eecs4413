import React, { useState } from 'react';
import axios from 'axios';
import './SubmitReview.css';

const SubmitReview = ({ vehicleId, onReviewSubmitted }) => {
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  const [reviewText, setReviewText] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('token');

      await axios.post(`http://localhost:8080/reviews/${vehicleId}`, {
        rating,
        reviewText,
      }, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      setMessage('Review submitted successfully!');
      setRating(0);
      setReviewText('');

      if (typeof onReviewSubmitted === 'function') {
        onReviewSubmitted();
      }

    } catch (err) {
      console.error('Failed to submit review:', err);
      setMessage('Failed to submit review.');
    }
  };

  return (
    <div className="review-form mt-5">
      <h4>Leave a Review</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Your Rating</label>
          <div className="star-rating">
            {[1, 2, 3, 4, 5].map((star) => (
              <span
                key={star}
                className={`star ${star <= (hover || rating) ? 'filled' : ''}`}
                onClick={() => setRating(star)}
                onMouseEnter={() => setHover(star)}
                onMouseLeave={() => setHover(0)}
              >
                ★
              </span>
            ))}
          </div>
        </div>

        <div className="mb-3">
          <label>Your Comment</label>
          <textarea
            className="form-control"
            rows="3"
            value={reviewText}
            onChange={(e) => setReviewText(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary">Submit</button>
        {message && <p className="mt-2 text-info">{message}</p>}
      </form>
    </div>
  );
};

export default SubmitReview;
