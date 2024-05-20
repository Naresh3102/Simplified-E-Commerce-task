import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const ProductDetailsPage = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [selectedVariant, setSelectedVariant] = useState(null);
  const [uniqueColors, setUniqueColors] = useState([]);
  const [isAddedToCart, setIsAddedToCart] = useState(false);
  const [isAddingTocart, setIsAddingToCart] = useState(false);

  useEffect(() => {
    // Fetch product details from the server using productId
    fetch(`http://localhost:8080/api/products/${productId}`)
      .then((response) => response.json())
      .then((data) => {
        setProduct(data);
        setSelectedVariant(data.productVariants[0]); // Set the default variant
        const colors = Array.from(
          new Set(data.productVariants.map((variant) => variant.color.hexCode))
        );
        setUniqueColors(colors);
      })
      .catch((error) =>
        console.error("Error fetching product details:", error)
      );
  }, [productId]);

  const handleVariantChange = (variantId) => {
    const newVariant = product.productVariants.find(
      (variant) => variant.variantId === variantId
    );
    setSelectedVariant(newVariant);
  };

  const handleAddToCart = () => {
    const cartItem = {
      productId: product.productId,
      productName: product.name,
      productVariant: selectedVariant,
      quantity: 1,
    };

    fetch("http://localhost:8080/api/carts/1/items", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cartItem),
    })
      .then((response) => {
        if (response.ok) {
          setIsAddedToCart(true);
        } else {
          console.error("Error adding item to cart");
          setIsAddingToCart(false);
        }
      })
      .catch((error) => {
        console.error("Error adding item to cart:", error);
        setIsAddingToCart(false);
      });
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto flex flex-wrap items-start">
      <div className="w-full md:w-1/2 mb-4">
        <img
          src={`/${selectedVariant.imageUrl}`}
          alt={product.name}
          className="w-full h-auto md:h-96 object-contain mb-4"
        />
      </div>
      <div className="w-full md:w-1/2 md:pl-8">
        <h1 className="text-3xl text-left font-bold my-2">{product.name}</h1>
        <div className="text-gray-700 mb-2 text-left">
          {product.description}
        </div>
        <div className="flex items-center mb-4">
          <div className="flex items-center mr-4">
            <span className="mr-1">Rating:</span>
            <span>{product.rating}/5</span>
          </div>
          <div className="flex items-center">
            <span className="mr-1">Reviews:</span>
            <span>{product.numberOfReviews}</span>
          </div>
        </div>
        <div className="flex items-center mb-4">
          <span className="mr-2">Color:</span>
          <div className="flex">
            {uniqueColors.map((color, index) => (
              <div
                key={index}
                className={`w-6 h-6 rounded-full border border-gray-400 mr-2 cursor-pointer ${
                  selectedVariant.color.hexCode === color ? "border-black" : ""
                }`}
                style={{ backgroundColor: color }}
                onClick={() => {
                  const variantWithSelectedColor = product.productVariants.find(
                    (variant) => variant.color.hexCode === color
                  );
                  handleVariantChange(variantWithSelectedColor.variantId);
                }}
              ></div>
            ))}
          </div>
        </div>
        <div className="flex items-center mb-4">
          <span className="mr-2">Size:</span>
          <select
            value={selectedVariant.size}
            onChange={(e) => {
              const size = e.target.value;
              const variantWithSelectedSize = product.productVariants.find(
                (variant) =>
                  variant.color.hexCode === selectedVariant.color.hexCode &&
                  variant.size === size
              );
              handleVariantChange(variantWithSelectedSize.variantId);
            }}
            className="px-2 py-1 border border-gray-400"
          >
            {product.productVariants
              .filter(
                (variant) =>
                  variant.color.hexCode === selectedVariant.color.hexCode
              )
              .map((variant) => (
                <option key={variant.variantId} value={variant.size}>
                  {variant.size}
                </option>
              ))}
          </select>
        </div>
        <div className="text-gray-900 font-bold">${selectedVariant.price}</div>
        <button
          onClick={handleAddToCart}
          disabled={isAddingTocart || isAddedToCart}
          className={`px-4 py-2 mt-4 rounded ${
            isAddedToCart
              ? "bg-gray-300 text-gray-400 cursor-not-allowed"
              : "bg-blue-500 text-white"
          } rounded`}
        >
          {isAddedToCart ? "Added to Cart" : "Add to Cart"}
        </button>
      </div>
    </div>
  );
};

export default ProductDetailsPage;
