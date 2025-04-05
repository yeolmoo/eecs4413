import React, { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import SubmitReview from './SubmitReview';
import ReviewsSection from './ReviewsSection';

import api from '../api';

const VehicleDetail = () => {
  const { id } = useParams(); // /vehicle/:id
  const [vehicle, setVehicle] = useState(null);
  const [message, setMessage] = useState('');
  const token = localStorage.getItem('token');

  useEffect(() => {
    if (!id) return;

    const fetchVehicle = async () => {
      try {
        const res = await api.get(`/catalog/vehicle/${id}`);
        setVehicle(res.data);
      } catch (err) {
        console.error('Failed to load vehicle', err);
      }
    };

    fetchVehicle();
  }, [id]);

  const handleAddToCart = async () => {
    if (!token) {
      alert('Please log in to add to cart.');
      return;
    }

    try {
      await api.post(
        '/cart/add',
        {
          vehicleId: vehicle.id,
          quantity: 1,
          customization: null,
        },
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setMessage(`${vehicle.name} added to cart!`);
    } catch (err) {
      console.error('Failed to add to cart', err);
      setMessage('Failed to add to cart.');
    }
  };

  if (!vehicle) return <p>Loading vehicle details...</p>;

  return (
    <div className="container mt-4">
      <h2>{vehicle.name}</h2>
      <img
        src={vehicle.vehicleImg || 'https://via.placeholder.com/400x250'}
        alt={vehicle.name}
        className="img-fluid mb-3"
        style={{ maxWidth: '400px' }}
      />
      <p><strong>Brand:</strong> {vehicle.brand}</p>
      <p><strong>Model:</strong> {vehicle.model} ({vehicle.modelYear})</p>
      <p><strong>Condition:</strong> {vehicle.vehicleCondition}</p>
      <p><strong>Price:</strong> ${vehicle.price.toFixed(2)}</p>
      {vehicle.discount > 0 && (
        <p><strong>Discount:</strong> {Math.round(vehicle.discount * 100)}% OFF!</p>
      )}
      <p><strong>Description:</strong> {vehicle.description}</p>

      <button className="btn btn-success" onClick={handleAddToCart}>
        Add to Cart
      </button>
      {message && <p className="text-info mt-2">{message}</p>}

      <hr />

      {/* Review Components */}
      <SubmitReview vehicleId={vehicle.id} onReviewSubmitted={() => window.location.reload()} />

      <ReviewsSection vehicleId={vehicle.id} />
    </div>
  );
};

export default VehicleDetail;
