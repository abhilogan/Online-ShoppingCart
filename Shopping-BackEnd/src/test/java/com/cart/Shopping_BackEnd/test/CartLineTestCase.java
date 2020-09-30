package com.cart.Shopping_BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.Shopping_BackEnd.dao.CartLineDAO;
import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dao.UserDAO;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.CartLine;
import com.cart.Shopping_BackEnd.dto.Product;
import com.cart.Shopping_BackEnd.dto.User;

public class CartLineTestCase {
	
private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private Product  product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cart.Shopping_BackEnd");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddNewCartLine()
	{
		
		//get the user
		user = userDAO.getByEmail("sg@gmail.com");
		
		//fetch the cart
		cart = user.getCart();
		
		//get the product
		product = productDAO.get(1);
		
		//create a new cartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add a cartline", true,cartLineDAO.add(cartLine));
		
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to update a cart", true,cartLineDAO.updateCart(cart));
	}

}
