import React, { useEffect, useState } from 'react';
import axios from 'axios';
import VehicleCard from '../components/VehicleCard';

const HotDealsPage = () => {
  const [hotDeals, setHotDeals] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/catalog/vehicles')
      .then(res => {
        const filtered = res.data.filter(v => v.hotDeal === true);
        console.log('🔥 Hot Deals fetched:', filtered); 
        setHotDeals(filtered);
      })
      .catch(err => console.error("Unable to load hot deals:", err));
  }, []);
  

  return (
    <section className="container mt-5">
      <h2 className="mb-4"> All Hot Deals</h2>
      <div className="row">
        {hotDeals.length > 0 ? (
          hotDeals.map(vehicle => (
            <div className="col-md-4 mb-4" key={vehicle.id}>
              <VehicleCard vehicle={vehicle} />
            </div>
          ))
        ) : (
          <p>No hot deals available right now. Check back later!</p>
        )}
      </div>
    </section>
  );
};

export default HotDealsPage;
