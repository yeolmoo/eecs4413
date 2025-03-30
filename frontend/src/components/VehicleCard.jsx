import React from 'react';
import './VehicleCard.css'; 

const VehicleCard = ({ vehicle }) => {
  return (
    <div className="card m-2" style={{ width: '18rem' }}>
      <img src={vehicle.vehicleImg} className="card-img-top" alt={vehicle.name} />
      <div className="card-body">
        <h5 className="card-title">{vehicle.brand} {vehicle.model}</h5>
        <p className="card-text">${vehicle.price.toLocaleString()}</p>
        <p className="card-text text-muted">{vehicle.vehicleCondition} | {vehicle.modelYear}</p>
        {vehicle.hotDeal && <span className="badge bg-danger">HOT DEAL</span>}
      </div>
    </div>
  );
};

export default VehicleCard;
