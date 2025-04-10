import React, { useEffect, useState } from 'react';

import VehicleCard from '../components/VehicleCard';
import api from '../api';

const HotDealsPage = () => {
  const [hotDeals, setHotDeals] = useState([]);

  useEffect(() => {
    api.get('/catalog/vehicles')
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
