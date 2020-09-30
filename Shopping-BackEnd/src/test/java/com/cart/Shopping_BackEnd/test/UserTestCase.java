package com.cart.Shopping_BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.Shopping_BackEnd.dao.UserDAO;
import com.cart.Shopping_BackEnd.dto.Address;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cart.Shopping_BackEnd");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

//	@Test
//	public void testAdd()
//	{
//		user = new User() ;
//		user.setFirstName("Abhishek");
//		user.setLastName("Verma");
//		user.setEmail("av@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("123456");
//
//		//add the user
//		assertEquals("Failed to add user",true,userDAO.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("12, mayur Nagar");
//		address.setAddressLineTwo("pukhraj city road");
//		address.setCity("Indore");
//		address.setState("Madhya Pradesh");
//		address.setCountry("India");
//		address.setPostalCode("452001");
//		address.setBilling(true);
//		
//		//link the user with address
//		address.setUserId(user.getId());
//		
//		//add the address
//		assertEquals("Failed to add address",true,userDAO.addAddress(address));
//		
//		if(user.getRole().equals("USER")) {
//			
//			//create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			
//			assertEquals("Failed to add cart",true,userDAO.addCart(cart));
//
//			//add a shipping address for this user
//			address = new Address();
//			address.setAddressLineOne("Pukhraj Society, mayur Nagar");
//			address.setAddressLineTwo("Near pink city");
//			address.setCity("Indore");
//			address.setState("Madhya Pradesh");
//			address.setCountry("India");
//			address.setPostalCode("452001");
//			address.setShipping(true);
//			
//			//link it with the user
//			address.setUserId(user.getId());
//			
//			assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
//		}
//	}

//	@Test
//	public void testAdd() {
//		user = new User();
//		user.setFirstName("Abhishek");
//		user.setLastName("Verma");
//		user.setEmail("av@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("123456");
//
//		if (user.getRole().equals("USER")) {
//
//			// create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			
//			//attach cart with the user
//			
//			user.setCart(cart);
//
//		}
//
//		assertEquals("Failed to add user", true, userDAO.addUser(user));
//
//	}

//	@Test
//	public void testUpdateCart(){
//		
//		//fetch the user by its mail
//		user = userDAO.getByEmail("av@gmail.com");
//		
//		cart = user.getCart();
//		
//		cart.setGrandTotal(555);
//		cart.setCartLines(2);
//		
//		assertEquals("Failed to add cart", true, userDAO.updateCart(cart));
//		
//	}

//	@Test
//	public void testAddAddress() {
//		// we need to add an user
//		user = new User();
//		user.setFirstName("Abhishek");
//		user.setLastName("Verma");
//		user.setEmail("av@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("123456");
//
//		// add the user
//		assertEquals("Failed to add user", true, userDAO.addUser(user));
//
//		// we are going to add the address
//		address = new Address();
//		address.setAddressLineOne("12, mayur Nagar");
//		address.setAddressLineTwo("pukhraj city road");
//		address.setCity("Indore");
//		address.setState("Madhya Pradesh");
//		address.setCountry("India");
//		address.setPostalCode("452001");
//		address.setBilling(true);
//
//		// attach the address to the user
//		address.setUser(user);
//
//		assertEquals("Failed to add billing address", true, userDAO.addAddress(address));
//
//		// we are also going to add shipping address
//		address = new Address();
//		address.setAddressLineOne("Pukhraj Society, mayur Nagar");
//		address.setAddressLineTwo("Near pink city");
//		address.setCity("Indore");
//		address.setState("Madhya Pradesh");
//		address.setCountry("India");
//		address.setPostalCode("452001");
//		address.setShipping(true);
//
//		// attach the address to the user
//		address.setUser(user);
//
//		assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
//	}
	
//	@Test
//	public void testAddAddress()
//	{
//		
//		user = userDAO.getByEmail("av@gmail.com");
//		
//		// we are also going to add shipping address
//		address = new Address();
//		address.setAddressLineOne("112 rajivegandhi square");
//		address.setAddressLineTwo("Near bhavarkua");
//		address.setCity("Banglore");
//		address.setState("Karnataka");
//		address.setCountry("India");
//		address.setPostalCode("452001");
//		address.setShipping(true);
//
//		// attach the address to the user
//		address.setUser(user);
//
//		assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
//		
//	}
	
	
	@Test
	public void tesGetAddresses()
	{
		user = userDAO.getByEmail("av@gmail.com");
		
		assertEquals("Failed to fetch the list of address and the size does not match", 2, userDAO.listShippingAddress(user.getId()).size());
		
		assertEquals("Failed to fetch the list of address and the size does not match", "Indore", userDAO.getBillingAddress(user.getId()).getCity());
	}
}
