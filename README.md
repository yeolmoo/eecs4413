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

5. Front end:
   Create a `.env` file in the root of your frontend folder:
   Then, replace the placeholder with your actual backend server URL, e.g.: REACT_APP_API_BASE_URL=https://34.202.116.9.sslip.io
   If you're using Vite instead of CRA, use:
   VITE_API_BASE_URL=https://34.202.116.9.sslip.io

=============================================================

Notes:

backend hosted on AWS EC2 (HTTPS + nginx)

Certbot used for SSL set up


Dont forget to add

MySQL credential, OPENAI API key (openai.api.key) and jwt seceret key (app.jwt.secret) must be set in application.properties.

Please contact via email if you need the file => hongseu4@gmail.com
