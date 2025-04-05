import React, { useEffect, useState } from 'react';

import VehicleCard from '../components/VehicleCard';
import SearchBar from '../components/SearchBar';
import { useSearchParams } from 'react-router-dom';
import api from '../api';

const CatalogPage = () => {
  const [vehicles, setVehicles] = useState([]);
  const [searchParams] = useSearchParams();

  useEffect(() => {
    const fetchVehicles = () => {
      const params = new URLSearchParams();

      const brand = searchParams.get('brand');
      const modelYear = searchParams.get('modelYear');
      const condition = searchParams.get('vehicleCondition');
      const keyword = searchParams.get('keyword');
      const sortBy = searchParams.get('sortBy');
      const descending = searchParams.get('descending');

      if (brand) params.append('brand', brand);
      if (modelYear) params.append('modelYear', modelYear);
      if (condition) params.append('vehicleCondition', condition);
      if (keyword) params.append('keyword', keyword);
      if (sortBy) params.append('sortBy', sortBy);
      if (descending) params.append('descending', descending);

      api.get(`/catalog/search?${params.toString()}`)
        .then(res => {
          console.log('Filtered vehicle data:', res.data);
          setVehicles(res.data);
        })
        .catch(err => {
          console.error('Failed to fetch vehicles:', err);
        });
    };

    fetchVehicles();
  }, [searchParams]);

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Vehicle Catalog</h1>

    
      <SearchBar />

      <div className="d-flex flex-wrap justify-content-start">
        {vehicles.length > 0 ? (
          vehicles.map(vehicle => (
            <VehicleCard key={vehicle.id} vehicle={vehicle} />
          ))
        ) : (
          <p>No vehicles found.</p>
        )}
      </div>
    </div>
  );
};

export default CatalogPage;
