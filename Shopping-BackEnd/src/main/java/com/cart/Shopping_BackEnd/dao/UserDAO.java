package com.cart.Shopping_BackEnd.dao;

import java.util.List;

import com.cart.Shopping_BackEnd.dto.Address;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.User;

public interface UserDAO {

	boolean addUser(User user);

	User getByEmail(String email);

	boolean addAddress(Address address);

	// get an address
	Address getAddress(int addressId);
	
	//alternative for so many queries running in background in console
	
		//to find the billing address for particular user
		Address getBillingAddress(int userId);
		
		//list of shipping address
		List<Address> listShippingAddress(int userId);
		
		/*
		//to find the billing address for particular user
		Address getBillingAddress(User user);
			
		//list of shipping address
		List<Address> listShippingAddress(User user);
		*/

	// updating the new address
	boolean updateAddress(Address address);
}
