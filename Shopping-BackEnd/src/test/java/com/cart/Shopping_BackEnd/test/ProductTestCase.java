package com.cart.Shopping_BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.Shopping_BackEnd.dao.ProductDAO;
import com.cart.Shopping_BackEnd.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cart.Shopping_BackEnd");
		context.refresh();

		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	/*
	 * @Test public void testCRUDProduct() { //ADD product = new Product();
	 * product.setName("Oppo F5 plus"); product.setBrand("Oppo");
	 * product.setDescription("This is some description about oppo");
	 * product.setUnitPrice(25000); product.setActive(true);
	 * product.setCategoryId(3); product.setSupplierId(3);
	 * 
	 * assertEquals("Something went wrong", true, productDAO.add(product));
	 * 
	 * //GET product = productDAO.get(2);
	 * 
	 * assertEquals("sucessfully added ", "Samsung s7" , product.getName());
	 * 
	 * //UPDATE product = productDAO.get(6);
	 * 
	 * product.setName("Oppo F5 plus changed");
	 * 
	 * assertEquals("Something went wrong", true, productDAO.update(product));
	 * 
	 * //DELETE product = productDAO.get(6);
	 * 
	 * assertEquals("Something went wrong", true, productDAO.delete(product));
	 * 
	 * //LIST
	 * 
	 * assertEquals("Something went wrong", 6, productDAO.list().size());
	 * 
	 * }
	 */

	/*
	 * @Test public void testAddProduct() { product = new Product();
	 * 
	 * product.setName("Oppo Selfie S53"); product.setBrand("Oppo");
	 * product.setDescription("This is some description for oppo mobile phones!");
	 * product.setUnitPrice(25000); product.setActive(true);
	 * product.setCategoryId(3); product.setSupplierId(3);
	 * 
	 * assertEquals("Something went wrong while inserting a new product!",
	 * true,productDAO.add(product)); }
	 */

	@Test
	public void testlistActiveProducts() {
		assertEquals("Something went wrong", 5, productDAO.listActiveProducts().size());
	}

	@Test
	public void testlistActiveProductsByCategory() {
		assertEquals("Something went wrong", 3, productDAO.listActiveProductsByCategory(3).size());

		assertEquals("Something went wrong", 2, productDAO.listActiveProductsByCategory(1).size());
	}

	@Test
	public void testlatestActiveProducts() {
		assertEquals("Something went wrong", 3, productDAO.getLatestActiveProducts(3).size());
	}

}
