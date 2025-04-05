		EECS4413 TEAM E - EVStore
===========================================================

Netlify (Frontend) : https://eecs-evstore.netlify.app

EC2 (Backend) : https://34.202.116.9.sslip.io

Link to demo : https://youtu.be/eC0Jc8rp214

Features
- JWT-based Login/Signup
- Vehicle Catalog (filter/sort/search)
- Shopping Cart & Checkout
- Reviews & Ratings
- Chatbot using OpenAI Assistant API
- Responsive Design (Netlify Hosted)

============================================================

How to Run:

1. Clone the repo
2. Backend
	cd eecs4413 mvn spring-boot:run
3. Frontend
	cd frontend npm install npm run dev

=============================================================

Notes:

backend hosted on AWS EC2 (HTTPS + nginx)

Certbot used for SSL set up


Dont forget to add

OPENAI API key (openai.api.key) and jwt seceret key (app.jwt.secret) is stored in application.properties
