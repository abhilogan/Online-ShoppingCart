package com.cart.Shopping_BackEnd.dao;

import java.util.List;

import com.cart.Shopping_BackEnd.dto.Address;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);

	boolean addAddress(Address address);

	boolean updateCart(Cart cart);
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddress(User user);

}
