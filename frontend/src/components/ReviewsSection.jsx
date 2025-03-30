import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ReviewsSection = () => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    // RECENT REVIEW ONLY
    const fetchSampleReviews = async () => {
      try {
        const res = await axios.get('http://localhost:8080/catalog/vehicle/1');
        const vehicle = res.data;
        if (vehicle && vehicle.reviews) {
          const sorted = [...vehicle.reviews].sort((a, b) =>
            new Date(b.reviewDate) - new Date(a.reviewDate)
          );
          setReviews(sorted.slice(0, 5)); // ONLY 5
        }
      } catch (err) {
        console.error("Fail to load review:", err);
      }
    };

    fetchSampleReviews();
  }, []);

  return (
    <section className="mt-5">
      <h3 className="mb-3"> Recent Reviews</h3>
      <div className="list-group">
        {reviews.length > 0 ? (
          reviews.map((review, idx) => (
            <div key={idx} className="list-group-item">
              <strong>{review.reviewerName}</strong> - {review.reviewDate?.split('T')[0]}
              <p className="mb-1">{review.reviewText}</p>
            </div>
          ))
        ) : (
          <p>There is no reveiw yet.</p>
        )}
      </div>
    </section>
  );
};

export default ReviewsSection;
