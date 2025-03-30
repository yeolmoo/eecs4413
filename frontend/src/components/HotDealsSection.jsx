import React, { useEffect, useState } from 'react';
import axios from 'axios';
import VehicleCard from './VehicleCard';

const HotDealsSection = () => {
  const [hotDeals, setHotDeals] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/catalog/vehicles') 
      .then(res => {
        const filtered = res.data.filter(v => v.hotDeal === true); 
        setHotDeals(filtered);
      })
      .catch(err => {
        console.error("Unable to load hot deals:", err);
      });
  }, []);
  

  return (
    <section className="mt-5">
      <h3 className="mb-3">🔥 Hot Deals</h3>
      <div className="row">
        {hotDeals.length > 0 ? (
          hotDeals.map(vehicle => (
            <div className="col-md-4" key={vehicle.id}>
              <VehicleCard vehicle={vehicle} />
            </div>
          ))
        ) : (
          <p>Currently there is no hot deal. Please check later.</p>
        )}
      </div>
    </section>
  );
};

export default HotDealsSection;
