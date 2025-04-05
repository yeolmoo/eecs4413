import React, { useEffect, useState } from 'react';
import api from "../api";

const ReviewsSection = ({ vehicleId }) => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    if (!vehicleId) return;

    const fetchReviews = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/catalog/vehicle/${vehicleId}`);
        const sorted = res.data.reviews.sort((a, b) => new Date(b.reviewDate) - new Date(a.reviewDate));
        setReviews(sorted.slice(0, 5));
      } catch (err) {
        console.error("Failed to load reviews:", err);
      }
    };

    fetchReviews();
  }, [vehicleId]);

  return (
    <section className="mt-5">
      <h4>Recent Reviews</h4>
      <div className="list-group">
        {reviews.length > 0 ? (
          reviews.map((review, idx) => (
            <div key={idx} className="list-group-item">
              <strong>{review.username}</strong> - {review.reviewDate?.split('T')[0]}
              <div>{"★".repeat(review.rating)}{"☆".repeat(5 - review.rating)}</div>
              <p className="mb-1">{review.reviewText}</p>
            </div>
          ))
        ) : (
          <p>No reviews yet.</p>
        )}
      </div>
    </section>
  );
};

export default ReviewsSection;
