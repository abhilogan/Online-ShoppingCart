package com.cart.Shopping_BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.Shopping_BackEnd.dao.CategoryDAO;
import com.cart.Shopping_BackEnd.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cart.Shopping_BackEnd");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

//	@Test
//	public void testAddCategory()
//	{
//		category = new Category();
//		
//		category.setName("Food");
//		category.setDescription("dectription about Food");
//		category.setImageURL("CAT_105.png");
//		
//		assertEquals("successfullt added", true, categoryDAO.add(category));
//	}

//	@Test
//	public void testGetCategory()
//	{
//		category = categoryDAO.get(2);
//		
//		assertEquals("successfullt fetch single category", "LAptop", category.getName());
//	}

//	@Test
//	public void testUpdateCategory()
//	{
//		category = categoryDAO.get(2);
//		
//		category.setName("Mobile");
//		
//		assertEquals("successfullt update single category", true, categoryDAO.update(category));
//	}

//	@Test
//	public void testDeleteCategory()
//	{
//		category = categoryDAO.get(2);
//		
//		assertEquals("successfullt delete single category", true, categoryDAO.delete(category));
//	}

//	@Test
//	public void testListCategory()
//	{
//		
//		assertEquals("successfullt fetch List category",4, categoryDAO.list().size());
//	}

	@Test
	public void testCrudCategory() {

		
		//ADD 
		category = new Category();

		category.setName("Food");
		category.setDescription("dectription about Food");
		category.setImageURL("C11.png");

		assertEquals("successfullt added", true, categoryDAO.add(category));

		category = new Category();

		category.setName("TV");
		category.setDescription("dectription about Food");
		category.setImageURL("C12.png");

		assertEquals("successfullt added", true, categoryDAO.add(category));

		//GET
		
		category = categoryDAO.get(1);
		
		assertEquals("successfullt fetch single category", "Food", category.getName());
		
		
		//UPDATE
		
		category = categoryDAO.get(2);
		
		category.setName("electronics");
		
		assertEquals("successfullt added", true, categoryDAO.update(category));
		
		//DELETE
		
		category = categoryDAO.get(2);
		
		assertEquals("successfullt delete single category", true, categoryDAO.delete(category));
		
		//LIST
		
		assertEquals("successfullt fetch List category",1, categoryDAO.list().size());
		
	}

}
