package com.cart.OnlineShopping.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cart.OnlineShopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result",required = false)String result)
	{
		ModelAndView mv = new ModelAndView("page");
		
		if(result != null) {
			switch (result) {
			case "updated":
				mv.addObject("message", "CartLine is updated successfully!");
				break;
				
			case "deleted":
				mv.addObject("message", "CartLine is deleted successfully!");
				break;
				
			case "added":
				mv.addObject("message", "CartLine is added successfully!");
				break;
				
			case "maximum":
				mv.addObject("message", "CartLine has reached to it's maximum count!");
				break;
				
			case "modified":
				mv.addObject("message", "One or more iteams inside cart is modified!");
				break;
				
			case "unavailable":
				mv.addObject("message", "Product quantity is not available!");
				break;
				
			case "error":
				mv.addObject("message", "SomeThing went wrong!");
				break;
			}
		}
		
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		
		mv.addObject("cartLines", cartService.getCartLine());
		
		return mv;
	}
	
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId , @RequestParam int count)
	{
		String response = cartService.manageCartLine(cartLineId,count);
		
		return "redirect:/cart/show?"+response;
		
	}
	
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable int cartLineId)
	{
		String response = cartService.deleteCartLine(cartLineId);
		
		return "redirect:/cart/show?"+response;
		
	}
	
	
	@RequestMapping("/add/{productId}/product")
	public String addCartLine(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
	
	//Validating the cart line
	//after validating it will redirect to check out if result recieve is
	//success proced to checkout else display the message to the user about the changes 
	// in the cart page
	@RequestMapping("/validate")
	public String validateCart()
	{
		String response = cartService.validateCartLine();
		
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
		
	}
	
	

}
