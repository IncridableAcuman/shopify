# 🛍️ Shopify Clone

A modern full-stack e-commerce platform designed to provide a seamless online shopping experience. The application includes user authentication, product management, shopping cart functionality, order processing, and a scalable architecture suitable for real-world applications.

## 🚀 Features

### Customer Features

* 🔐 User Registration & Authentication
* 👤 User Profile Management
* 🛍️ Browse Products
* 🔎 Product Search & Filtering
* ❤️ Wishlist Management
* 🛒 Shopping Cart
* 💳 Checkout Process
* 📦 Order Tracking
* 📜 Order History
* ⭐ Product Reviews & Ratings

### Admin Features

* 📊 Admin Dashboard
* 📦 Product Management
* 🏷️ Category Management
* 👥 User Management
* 📈 Sales Analytics
* 📋 Order Management
* 🚚 Inventory Tracking

---

## 🏗️ Architecture

The project follows a layered architecture to ensure maintainability, scalability, and clean code practices.

```text
Client
   │
   ▼
Frontend (React)
   │
   ▼
REST API
   │
   ▼
Backend (Spring Boot)
   │
   ▼
PostgreSQL Database
```

---

## 🛠️ Tech Stack

### Frontend

* React
* TypeScript
* Vite
* Tailwind CSS
* Axios
* React Router

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication

### Database

* PostgreSQL

### DevOps

* Docker
* Docker Compose
* GitHub Actions (Optional)

---

## 📂 Project Structure

```text
shopify
├── frontend
│   ├── src
│   ├── components
│   ├── pages
│   ├── services
│   └── hooks
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── security
│   └── config
│
└── docker
```

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/IncridableAcuman/shopify.git
cd shopify
```

---

### Backend Setup

Configure database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/shopify
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
```

Run backend:

```bash
./gradlew bootRun
```

or

```bash
./mvnw spring-boot:run
```

---

### Frontend Setup

Install dependencies:

```bash
npm install
```

Start development server:

```bash
npm run dev
```

Application:

```text
Frontend: http://localhost:5173
Backend : http://localhost:8080
```

---

## 🔐 Authentication

Authentication is implemented using JWT tokens.

Features:

* Access Token
* Refresh Token
* Secure Route Protection
* Role-Based Authorization

Roles:

* USER
* ADMIN

---

## 📚 API Modules

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
POST /api/auth/logout
POST /api/auth/refresh
```

### Products

```http
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Orders

```http
GET    /api/orders
POST   /api/orders
GET    /api/orders/{id}
```

### Categories

```http
GET    /api/categories
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
```

---

## 🐳 Docker Support

Build containers:

```bash
docker compose up --build
```

Stop containers:

```bash
docker compose down
```

---

## 📈 Future Improvements

* Stripe Integration
* PayPal Integration
* Redis Caching
* Elasticsearch
* Real-time Notifications
* Multi-vendor Marketplace
* Recommendation System
* AI-powered Search

---

## 🎯 Learning Objectives

This project demonstrates:

* Enterprise Application Design
* Clean Architecture
* REST API Development
* Authentication & Authorization
* Database Design
* Docker Containerization
* Full-Stack Development

---

## 👨‍💻 Author

Izzat Abdusharipov

GitHub:
https://github.com/IncridableAcuman

---

## 📄 License

This project is licensed under the MIT License.
