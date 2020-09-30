package com.cart.Shopping_BackEnd.dao;

import java.util.List;

import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.CartLine;
import com.cart.Shopping_BackEnd.dto.OrderDetail;

public interface CartLineDAO {

	// the common methods
	public List<CartLine> list(int cartId);

	public CartLine get(int id);

	public boolean add(CartLine cartLine);

	public boolean update(CartLine cartLine);

	public boolean delete(CartLine cartLine);

	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);

	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);

	// update a cart
	boolean updateCart(Cart cart);

	// adding order details
	boolean addOrderDetails(OrderDetail orderDetail);
}
