// HomePage.jsx
import React from 'react';
import SearchBar from '../components/SearchBar';
import HotDealsSection from '../components/HotDealsSection';
import ReviewsSection from '../components/ReviewsSection';
import FAB from '../components/FAB';

const HomePage = ({ chatOpen, setChatOpen }) => {
  return (
    <>
      <main className="container mt-4">
        <SearchBar onSearch={() => {}} />
        <hr />
        <HotDealsSection />
        <hr />
        <ReviewsSection />
      </main>
      <FAB isChatOpen={chatOpen} toggleChat={() => setChatOpen(!chatOpen)} />
    </>
  );
};

export default HomePage;
