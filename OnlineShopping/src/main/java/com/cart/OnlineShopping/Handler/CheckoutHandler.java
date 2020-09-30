package com.cart.OnlineShopping.Handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.OnlineShopping.Model.CheckoutModel;
import com.cart.OnlineShopping.Model.UserModel;
import com.cart.Shopping_BackEnd.dao.CartLineDAO;
import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dao.UserDAO;
import com.cart.Shopping_BackEnd.dto.Address;
import com.cart.Shopping_BackEnd.dto.Cart;
import com.cart.Shopping_BackEnd.dto.CartLine;
import com.cart.Shopping_BackEnd.dto.OrderDetail;
import com.cart.Shopping_BackEnd.dto.OrderItem;
import com.cart.Shopping_BackEnd.dto.Product;
import com.cart.Shopping_BackEnd.dto.User;

@Component
public class CheckoutHandler {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

	public CheckoutModel init(String name) throws Exception {

		// Fetch the User by name(email)
		User user = userDAO.getByEmail(name);

		// Initializing the object
		CheckoutModel checkoutModel = null;

		// If user is not null
		if (user != null) {

			// a new object of checkout model is created
			checkoutModel = new CheckoutModel();

			// setting the user in checkoutModel
			checkoutModel.setUser(user);

			// setting the cart of user in checkoutModel
			checkoutModel.setCart(user.getCart());

			// Initializing the default in checkoutModel
			double checkoutTotal = 0.0;

			// Fetching the available list of cartlines by cartId
			List<CartLine> cartLines = cartLineDAO.listAvailable(user.getCart().getId());

			if (cartLines.size() == 0) {
				throw new Exception("There are no products available for checkout now!");
			}

			// for each cartLines in cartLine
			for (CartLine cartLine : cartLines) {

				// the checkout total will be the cartLine total
				checkoutTotal += cartLine.getTotal();
			}

			// setting the cartLines in checkoutModel
			checkoutModel.setCartLines(cartLines);

			// setting the checkout total in checkoutModel
			checkoutModel.setCheckoutTotal(checkoutTotal);
		}

		// returning the final checkout Model data
		return checkoutModel;
	}

	public String saveAddress(CheckoutModel checkoutModel, Address shipping) {

		String transitionValue = "success";

		// set the userId
		shipping.setUserId(checkoutModel.getUser().getId());

		// set the shipping as true
		shipping.setShipping(true);

		// add the shipping address
		userDAO.addAddress(shipping);

		// set the shipping id to flowScope object
		checkoutModel.setShipping(shipping);

		return transitionValue;
	}

	public List<Address> getShippingAddresses(CheckoutModel model) {

		// Fetch the shipping address
		List<Address> addresses = userDAO.listShippingAddress(model.getUser().getId());

		// If there is no shipping address
		if (addresses.size() == 0) {

			// create a new list
			addresses = new ArrayList<>();
		}

		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser().getId()));

		return addresses;
	}

	public String saveAddressSelection(CheckoutModel checkoutModel, int shippingId) {

		String transitionValue = "success";

		// Fetch the address
		Address shipping = userDAO.getAddress(shippingId);

		// setting the shipping address in model
		checkoutModel.setShipping(shipping);

		return transitionValue;
	}
	
	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue="success";
		
		//create a new order object
		OrderDetail orderDetail=new OrderDetail();
		
		//attach the user
		orderDetail.setUser(checkoutModel.getUser());
		
		//attach the shipping address
		orderDetail.setShipping(checkoutModel.getShipping());
		
		//fetch the billing address
		Address billing=userDAO.getBillingAddress(checkoutModel.getUser().getId());
		
		//attach the billing address
		orderDetail.setBilling(billing);
		
		//Getting the Cartlines list
		List<CartLine> cartLines=checkoutModel.getCartLines();
		
		//Initializing some variables
		OrderItem orderItem=null;
		double orderTotal=0.0;
		int orderCount=0;
		Product product=null;
		
		//for each cartLine in CartLines
		for(CartLine cartLine : cartLines) {
			
			//new orderItem object
			orderItem=new OrderItem();
			
			//setting the buying price
			orderItem.setBuyingPrice(cartLine.getBuyingPrice());
			
			//setting the product
			orderItem.setProduct(cartLine.getProduct());
			
			//setting the product count
			orderItem.setProductCount(cartLine.getProductCount());
			
			//setting the total
			orderItem.setTotal(cartLine.getTotal());
			
			//attach order detail
			orderItem.setOrderDetail(orderDetail);
			
			orderDetail.getOrderItems().add(orderItem);
			
			//setting the order total
			orderTotal+=orderItem.getTotal();
			
			//for next order
			orderCount++;
			
			//Reduce the quantity of product and update the product after buying
			product=cartLine.getProduct();
			product.setQuantity(product.getQuantity()-cartLine.getProductCount());
			product.setPurchases(product.getPurchases()+cartLine.getProductCount());
			productDAO.update(product);
			
			//delete the cartLine after payment
			cartLineDAO.delete(cartLine);
		}
		
		//setting the order total
		orderDetail.setOrderTotal(orderTotal);
		
		//setting the order count
		orderDetail.setOrderCount(orderCount);
		
		//setting the order date
		orderDetail.setOrderDate(new Date());
		
		//save the order
		cartLineDAO.addOrderDetails(orderDetail);
		
		//set it to the orderDetails of checkoutModel
		checkoutModel.setOrderDetail(orderDetail);
		
		//update the cart
		Cart cart=checkoutModel.getCart();
		cart.setGrandTotal(cart.getGrandTotal()-orderTotal);
		cart.setCartLines(cart.getCartLines()-orderCount);
		cartLineDAO.updateCart(cart);
		
		//update the cart if its in the session
		UserModel userModel=(UserModel)session.getAttribute("userModel");
		
		if(userModel!=null) {
			userModel.setCart(cart);
		}
		
		return transitionValue;
	}
	
	public OrderDetail getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}

}
