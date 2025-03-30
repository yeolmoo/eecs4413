import React, { useEffect, useState } from 'react';
import axios from 'axios';
import VehicleCard from '../components/VehicleCard'; 

const CatalogPage = () => {
  const [vehicles, setVehicles] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/catalog/vehicles')
      .then(res => {
        console.log('Vehicle data:', res.data);
        setVehicles(res.data);
      })
      .catch(err => {
        console.error('Backend connection failed:', err);
      });
  }, []);

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Vehicle Catalog</h1>
      <div className="d-flex flex-wrap justify-content-start">
        {vehicles.map(vehicle => (
          <VehicleCard key={vehicle.id} vehicle={vehicle} />
        ))}
      </div>
    </div>
  );
};

export default CatalogPage;
