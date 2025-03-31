import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const SearchBar = () => {
  const [filters, setFilters] = useState({
    brand: '',
    modelYear: '',
    condition: '',
    keyword: '',
    sortBy: '',
    descending: 'false'
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFilters({ ...filters, [e.target.name]: e.target.value });
  };

  const handleSearch = () => {
    const params = new URLSearchParams();
    if (filters.brand) params.append('brand', filters.brand);
    if (filters.modelYear) params.append('modelYear', filters.modelYear);
    if (filters.condition) params.append('vehicleCondition', filters.condition);
    if (filters.keyword) params.append('keyword', filters.keyword);
    if (filters.sortBy) params.append('sortBy', filters.sortBy);
    if (filters.descending) params.append('descending', filters.descending);

    navigate(`/catalog?${params.toString()}`);
  };

  return (
    <div className="card p-4 mb-4">
      <h5>Find Vehicle</h5>
      <div className="row g-2 align-items-center">
        <div className="col-md-3">
          <label>Brand</label>
          <select name="brand" className="form-select" onChange={handleChange}>
            <option value="">ALL</option>
            <option value="TESLA">TESLA</option>
            <option value="BMW">BMW</option>
            <option value="KIA">KIA</option>
          </select>
        </div>

        <div className="col-md-3">
          <label>Make year</label>
          <select name="modelYear" className="form-select" onChange={handleChange}>
            <option value="">ALL</option>
            <option value="2020">2020</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
          </select>
        </div>

        <div className="col-md-3">
          <label>Condition</label>
          <select name="condition" className="form-select" onChange={handleChange}>
            <option value="">ALL</option>
            <option value="NEW">NEW</option>
            <option value="USED">USED</option>
          </select>
        </div>

        <div className="col-md-3">
          <label>Keyword</label>
          <input
            type="text"
            className="form-control"
            name="keyword"
            placeholder="Description"
            onChange={handleChange}
          />
        </div>

        <div className="col-md-3">
          <label>Sort By</label>
          <select name="sortBy" className="form-select" onChange={handleChange}>
            <option value="">Default</option>
            <option value="price">Price</option>
            <option value="modelYear">Model Year</option>
          </select>
        </div>

        <div className="col-md-3">
          <label>Order</label>
          <select name="descending" className="form-select" onChange={handleChange}>
            <option value="false">Ascending</option>
            <option value="true">Descending</option>
          </select>
        </div>

        <div className="mt-3 text-end">
          <button className="btn btn-primary" onClick={handleSearch}>
            Search
          </button>
        </div>
      </div>
    </div>
  );
};

export default SearchBar;
