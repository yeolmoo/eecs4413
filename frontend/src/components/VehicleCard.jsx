import React from 'react';
import './VehicleCard.css';

const VehicleCard = ({ vehicle }) => {
  return (
    <div className="card vehicle-card m-2">
      <div className="vehicle-image-wrapper">
        <img src={vehicle.vehicleImg} className="card-img-top" alt={vehicle.name} />
      </div>
      <div className="card-body d-flex flex-column justify-content-between">
        <div>
          <h5 className="card-title">{vehicle.brand} {vehicle.model}</h5>
          <p className="card-text price">${vehicle.price.toLocaleString()}</p>
          <p className="card-text text-muted">{vehicle.vehicleCondition.toUpperCase()} | {vehicle.modelYear}</p>
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
