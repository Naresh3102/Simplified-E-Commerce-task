import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const ProductListingPage = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/products")
      .then((response) => response.json())
      .then((data) => setProducts(data))
      .catch((error) => console.error("Error fetching products:", error));
  }, []);

  return (
    <div className="container mx-auto">
      <h1 className="text-3xl font-bold my-8">Product Listing</h1>
      <div className="grid grid-cols-3 gap-4">
        {products.map((product) => (
          <div key={product.productId} className="border p-4">
            <Link to={`/products/${product.productId}`}>
              <img
                src={product.productVariants[0].imageUrl}
                alt={product.name}
                className="w-full mb-2"
              />
              <div className="font-bold">{product.name}</div>
              <div className="text-gray-700">{product.description}</div>
              <div className="text-gray-900 font-bold">
                ${product.productVariants[0].price}
              </div>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductListingPage;
