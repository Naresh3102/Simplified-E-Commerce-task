# Simplified E-Commerce Platform

## Overview

This project is a practice task developed during an internship at GeekBase Technology. It is a simplified e-commerce platform aimed at learning full-stack web development. The platform allows users to browse products, view detailed product information, add products to a cart, and manage their cart items.

## Technologies Used

- **Backend:**
  - Java Spring Boot
  - Spring Data JPA
  - Hibernate
  - RESTful APIs
- **Frontend:**
  - React.js
  - React Router
  - Axios
  - HTML/CSS
- **Database:**
  - MySQL (configured with Spring Boot)
- **Additional Tools:**
  - Lombok (Java library for reducing boilerplate code)

## Features

- Product Listing: Displaying a list of available products.
- Product Details: Providing detailed information about each product.
- Cart Management: Allowing users to add and remove items from their cart.
- Spring Security (upcoming): Implementing authentication and authorization mechanisms for secure user access.

## API Endpoints

- **GET /api/products:** Retrieve a list of all products.
- **GET /api/products/{productId}:** Retrieve detailed information about a specific product.
- **POST /api/products:** Create a new product.
- **PUT /api/products/{productId}:** Update an existing product.
- **DELETE /api/products/{productId}:** Delete a product.
- **POST /api/carts/{cartId}/items:** Add an item to the cart.
- **GET /api/carts/{cartId}/items:** Retrieve all items in the cart.
- **DELETE /api/carts/{cartId}/items/{cartItemId}:** Remove an item from the cart.

## Getting Started

1. Clone the repository: `git clone https://github.com/your-username/e-commerce-platform.git`
2. Navigate to the backend folder and run `mvn spring-boot:run` to start the Spring Boot server.
3. Navigate to the frontend folder and run `npm install` followed by `npm start` to start the React development server.
4. Access the application at `http://localhost:3000`.

## Future Enhancements

- Implement user authentication and authorization with Spring Security.
- Enhance the user interface with additional features and styling.
- Integrate payment gateway for real transactions.
