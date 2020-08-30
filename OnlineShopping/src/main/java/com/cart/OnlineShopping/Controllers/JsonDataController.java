package com.cart.OnlineShopping.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody // convert data into JSON
	public List<Product> getAllProducts()
	{
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody // convert data into JSON
	public List<Product> getAllProductsForAdmin()
	{
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody // convert data into JSON
	public List<Product> getProductsByCategory( @PathVariable int id)
	{
		return productDAO.listActiveProductsByCategory(id);
	}

}
