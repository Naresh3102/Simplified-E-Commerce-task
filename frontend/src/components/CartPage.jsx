import React, { useState, useEffect } from "react";

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    // Fetch cart items from the server
    fetch("http://localhost:8080/api/carts/1/items") // Assuming cart ID is 1 for simplicity
      .then((response) => response.json())
      .then((data) => setCartItems(data))
      .catch((error) => console.error("Error fetching cart items:", error));
  }, []);

  const updateQuantity = (cartItemId, newQuantity) => {
    fetch(`http://localhost:8080/api/carts/1/items/${cartItemId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ quantity: newQuantity }),
    })
      .then((response) => {
        if (response.ok) {
          setCartItems((prevItems) =>
            prevItems.map((item) =>
              item.cartItemId === cartItemId
                ? { ...item, quantity: newQuantity }
                : item
            )
          );
        } else {
          console.error("Error updating quantity");
        }
      })
      .catch((error) => console.error("Error updating quantity:", error));
  };

  const removeItem = (cartItemId) => {
    fetch(`http://localhost:8080/api/carts/1/items/${cartItemId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          setCartItems((prevItems) =>
            prevItems.filter((item) => item.cartItemId !== cartItemId)
          );
        } else {
          console.error("Error removing item from cart");
        }
      })
      .catch((error) => console.error("Error removing item from cart:", error));
  };

  const calculateTotalPrice = () => {
    return cartItems
      .reduce(
        (total, item) => total + item.productVariant.price * item.quantity,
        0
      )
      .toFixed(2);
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Cart</h1>
      {cartItems.length === 0 ? (
        <div>Your cart is empty.</div>
      ) : (
        <div className="flex flex-col space-y-4">
          {cartItems.map((item) => (
            <div
              key={item.cartItemId}
              className="flex items-center justify-between border p-4"
            >
              <div className="flex items-center">
                <img
                  src={`/${item.productVariant.imageUrl}`}
                  alt={item.productName}
                  className="w-20 h-20 object-contain mr-4"
                />
                <div>
                  <h2 className="text-lg font-bold">{item.productName}</h2>
                  <p className="text-gray-600">
                    Size: {item.productVariant.size}
                  </p>
                  <p className="text-gray-600">
                    Color: {item.productVariant.color.name}
                  </p>
                </div>
              </div>
              <div className="flex items-center space-x-2">
                <button
                  onClick={() =>
                    updateQuantity(item.cartItemId, item.quantity - 1)
                  }
                  disabled={item.quantity <= 1}
                  className="px-2 py-1 bg-gray-300 text-gray-700 rounded"
                >
                  -
                </button>
                <span>{item.quantity}</span>
                <button
                  onClick={() =>
                    updateQuantity(item.cartItemId, item.quantity + 1)
                  }
                  className="px-2 py-1 bg-gray-300 text-gray-700 rounded"
                >
                  +
                </button>
                <button
                  onClick={() => removeItem(item.cartItemId)}
                  className="px-2 py-1 bg-red-500 text-white rounded"
                >
                  Remove
                </button>
              </div>
            </div>
          ))}
          <div className="flex justify-end border-t pt-4 mt-4">
            <h2 className="text-xl font-bold">
              Total: ${calculateTotalPrice()}
            </h2>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartPage;
