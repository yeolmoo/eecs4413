import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import VehicleCard from './VehicleCard';
import './HotDealsSection.css';
import { useNavigate } from 'react-router-dom';
import api from '../api';

const HotDealsSection = () => {
  const [hotDeals, setHotDeals] = useState([]);
  const scrollRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/catalog/vehicles')
      .then(res => {
        const filtered = res.data.filter(v => v.hotDeal === true);
        setHotDeals(filtered);
      })
      .catch(err => {
        console.error("Unable to load hot deals:", err);
      });
  }, []);

  const scrollByAmount = 500;

  const scrollLeft = () => {
    if (scrollRef.current) {
      scrollRef.current.scrollBy({ left: -scrollByAmount, behavior: 'smooth' });
    }
  };

  const scrollRight = () => {
    if (scrollRef.current) {
      scrollRef.current.scrollBy({ left: scrollByAmount, behavior: 'smooth' });
    }
  };

  return (
    <section className="hot-deals-section mt-5">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3 className="hot-title">🔥 Hot Deals</h3>
        <button className="btn btn-outline-primary" onClick={() => navigate('/hot-deals')}>
          Shop All Hot Deals →
        </button>
      </div>

      {hotDeals.length > 0 ? (
        <div className="carousel-container">
          <button className="nav-arrow left" onClick={scrollLeft}>‹</button>
          <div className="hot-deals-carousel" ref={scrollRef}>
            {hotDeals.slice(0, 10).map(vehicle => (
              <div className="hot-deal-item" key={vehicle.id}>
                <VehicleCard vehicle={vehicle} showDiscount={true} />
              </div>
            ))}
          </div>
          <button className="nav-arrow right" onClick={scrollRight}>›</button>
        </div>
      ) : (
        <p>Currently there is no hot deal. Please check later.</p>
      )}
    </section>
  );
};

export default HotDealsSection;
