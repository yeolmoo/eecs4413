import React from 'react';
import { useNavigate } from 'react-router-dom';
import './VehicleCard.css';
import api from "../api";

const VehicleCard = ({ vehicle }) => {
  const navigate = useNavigate(); 

  const handleCardClick = () => {
    navigate(`/vehicle/${vehicle.id}`); 
  };

  const handleAddToCart = (e) => {
    e.stopPropagation(); 
    const token = localStorage.getItem('token');
    if (!token) {
      alert(" Please login to add items to cart.");
      return;
    }

    api.post('/cart/add'), {
      vehicleId: vehicle.id,
      quantity: 1,
      customization: null
    }, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then(() => {
      alert(' Added to cart!');
    }).catch(err => {
      console.error('Add to cart failed:', err);
      alert("Failed to add to cart.");
    });
  };

  return (
    <div className="card vehicle-card m-2" onClick={handleCardClick} style={{ cursor: 'pointer' }}>
      <div className="vehicle-image-wrapper">
        <img src={vehicle.vehicleImg} className="card-img-top" alt={vehicle.name} />
      </div>
      <div className="card-body d-flex flex-column justify-content-between">
        <div>
          <h5 className="card-title">{vehicle.brand} {vehicle.model}</h5>
          <p className="card-text price">${vehicle.price.toLocaleString()}</p>
          <p className="card-text text-muted">{vehicle.vehicleCondition.toUpperCase()} | {vehicle.modelYear}</p>
        </div>

        <div className="mt-2">
          <button className="btn btn-success w-100" onClick={handleAddToCart}>
            Add to Cart
          </button>
        </div>

        {vehicle.hotDeal && (
          <div className="d-flex flex-wrap gap-2 mt-2">
            <span className="badge bg-danger">HOT DEAL</span>
            {vehicle.discount > 0 && (
              <span className="badge bg-warning text-dark">
                {Math.round(vehicle.discount * 100)}% OFF
              </span>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default VehicleCard;
