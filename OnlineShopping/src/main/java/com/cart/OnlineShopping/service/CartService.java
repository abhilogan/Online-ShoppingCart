package com.cart.OnlineShopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.OnlineShopping.Model.UserModel;
import com.cart.Shopping_BackEnd.dao.CartLineDAO;
import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.CartLine;
import com.cart.Shopping_BackEnd.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProductDAO productDAO;
	
	//returns the cart of the user who has logged in
	private Cart getCart()
	{
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	
	//return the entire cart lines
	public List<CartLine> getCartLine()
	{
		Cart cart = this.getCart();
		
		return cartLineDAO.list(cart.getId());
	}


	public String manageCartLine(int cartLineId, int count) {

		//fetch the cart line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null)
		{
			return "result=error";
		}else {
			
			Product product = cartLine.getProduct();
			
			double oldTotal = cartLine.getTotal();
			
			//checking if the product is available
			if(product.getQuantity() < count) {
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
			
		}
		
		
	}


	public String deleteCartLine(int cartLineId) {

		//fetch the cart line
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		}
		else {
			
			//update the cart
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			//remove the cartline
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";
		}
	}


	public String addCartLine(int productId) {		
		Cart cart = this.getCart();
		String response = null;
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine==null) {
			// add a new cartLine if a new product is getting added
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());
			
			// insert a new cartLine
			cartLineDAO.add(cartLine);
			
			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			cartLineDAO.updateCart(cart);

			response = "result=added";						
		} 
		else {
			//check if the cartline reached the maximum
			
			if(cartLine.getProductCount() <10)
			{
				
				response = this.manageCartLine(cartLine.getId() , cartLine.getProductCount() +1);
				
			}else {
				response = "result=maximum";
			}
		}
				
		return response;
	}


	public String validateCartLine() {
		//fetch the current cart
		Cart cart = this.getCart();
		
		//fetch the cartLine
		List<CartLine> cartLines = cartLineDAO.list(cart.getId());
		
		//Initilizing defaults
		double grandTotal = 0.0;
		int lineCount = 0;
		String response = "result=success";
		boolean changed=false;
		Product product = null;
		
		//for each cartLine in the cartLines list
		for(CartLine cartLine : cartLines) {
			//get the product
			product = cartLine.getProduct();
			changed = false;
			
			//check if the product is active or not and has zero quantity & cartline available
			//and if it is not active make the availability of the cartline as false
			if((!product.isActive() && product.getQuantity()==0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}
			
			//check weather the product is active and atleast one quantity is available
			//check if cartline is not available
			if((product.isActive() && product.getQuantity()>0) && !(cartLine.isAvailable())) {
				cartLine.setAvailable(true);
				changed = true;
			}
			
			//check if the buying price of the product is changed
			if(cartLine.getBuyingPrice() != product.getUnitPrice()) {
				
				//set the buyingprice to the new product price
				cartLine.setBuyingPrice(product.getUnitPrice());
				
				//calculate the new total and set
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
				
			}
			
			//check if that much quantity if product is available or not
			if(cartLine.getProductCount() > product.getQuantity()) {
				
				//set the product count to the quantity
				cartLine.setProductCount(product.getQuantity());
				
				//calculating and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
			}
			
			/*
			 * If changes has happened
			 */
			if(changed) {
				//update the cartLine
				cartLineDAO.update(cartLine);
				
				//set the result as modified
				response = "result=modified";
			}
			
			//setting the new grand total for next line 
			
			grandTotal += cartLine.getTotal();
			lineCount ++;
		}
		
		
		cart.setCartLines(lineCount++);
		cart.setGrandTotal(grandTotal);
		
		//update the cart
		cartLineDAO.updateCart(cart);
		
		return response;
	}

}
