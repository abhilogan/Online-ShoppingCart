package com.cart.OnlineShopping.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cart.OnlineShopping.exception.ProductNotFoundException;
import com.cart.Shopping_BackEnd.dao.CategoryDAO;
import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dto.Category;
import com.cart.Shopping_BackEnd.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home","/homePage", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		//passing the list Categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome", true);

		return mv;
	}
	
	@RequestMapping(value ="/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);

		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView abontactout() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);

		return mv;
	}
	
	/**methods to load all the products and category **/

	
	@RequestMapping(value = "show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		//passing the list Categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts", true);

		return mv;
	}
	
	@RequestMapping(value = "show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		//categoryDAO to fetch single category..
		
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		
		//passing the list Categories
		mv.addObject("categories", categoryDAO.list());
		
		//passing the single Categories
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts", true);

		return mv;
	}
	
	/** Vewing the single products.**/
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews() +1);
		productDAO.update(product);
		//-----------------------------
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}
	
	//login page
	
	@RequestMapping(value ="/login")
	public ModelAndView login(@RequestParam(name = "error" , required = false)String error,
			@RequestParam(name = "logout" , required = false)String logout
			) {
		ModelAndView mv = new ModelAndView("login");
		
		if(error != null ) {
			mv.addObject("message", "Invalid Username and Password !");
		}
		
		if(logout != null ) {
			mv.addObject("logout", "User has succseefully logout !");
		}
		
		mv.addObject("title", "Login");

		return mv;
	}
	
	//access denied
	@RequestMapping(value ="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403- Access Denied");
		mv.addObject("errorTitle", "Aha ! Caught you.");
		mv.addObject("errorDescription", "you are not authorized to view this page..");

		return mv;
	}
	
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request , HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		
		return "redirect:/login?logout";
	}
	
}
