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
2. Copy `application.properties.example` → `application.properties`
3. Fill in:
   - DB username/password
   - OpenAI API key
   - JWT secret (at least 32 characters)
4. Run the backend:
   mvn clean package
   java -jar target/ecommerce-0.0.1-SNAPSHOT.jar

=============================================================

Notes:

backend hosted on AWS EC2 (HTTPS + nginx)

Certbot used for SSL set up


Dont forget to add

OPENAI API key (openai.api.key) and jwt seceret key (app.jwt.secret) is stored in application.properties
