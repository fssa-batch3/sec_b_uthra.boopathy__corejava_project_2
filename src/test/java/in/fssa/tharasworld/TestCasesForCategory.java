package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.service.CategoryService;

public class TestCasesForCategory {

	///  TEST FOR VALID INPUT TO CREATE CATEGORY
	
		@Test
		public void testCreateCategoryWithValidInput() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("Dress");
		
			
			assertDoesNotThrow(()->{
				categoryService.create(newCategory);
			});
		
		}
		
		//// TEST FOR INVALID INPUT
		
		@Test
		public void testCreateCategoryWithInvalidInput() {
			
			CategoryService categoryService = new CategoryService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(null);
			});
			String exceptedMessage = "Invalid category input";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
		//// TEST FOR CATEGORY WITH NULL
		
		@Test 
		public void testCreateCategoryWithCategoryNameNull() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName(null);
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
		//// TEST FOR CATEGORY WITH EMPTY STRING
		
		@Test 
		public void testCreateCategoryWithCategoryNameEmpty() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("");
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
		////TEST FOR CATEGORY NAME WITH PATTERN	
		
		@Test 
		public void testCreateCategoryWithNamePattern() {
				
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("13245");
				
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name doesn't match the pattern";
			String actualMessage = exception.getMessage();
				
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
			////TEST FOR CATEGORY NAME ALREADY EXISTS
		
			@Test 
			public void testCreateCategoryWithCategoryNameAlredyExists() {
					
				CategoryService categoryService = new CategoryService();
				
				CategoryEntity newCategory = new CategoryEntity();
				newCategory.setCateName("Dress");
					
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.create(newCategory);
				});
				String exceptedMessage = "This category name is already exists";
				String actualMessage = exception.getMessage();
					
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
			//// TEST FOR UPDATE CATEGORY
			
			@Test
			public void testUpdateCategory() {

				CategoryService categoryService = new CategoryService();
				
				CategoryEntity updatedCategory = new CategoryEntity();
				updatedCategory.setCateName("Accessories");
				
				assertDoesNotThrow(() -> {
					categoryService.update(1, updatedCategory);
				});
				
			}
			
			//// TEST FOR DELETE CATEGORY
			
			@Test
			public void deleteCategory() throws Exception {
				
				CategoryService categoryService = new CategoryService();
				
				categoryService.delete(1);
				
			}
		
		//// TEST FOR GET ALL CATEGORY
			
			@Test
			public void getAllCategories() {
				CategoryService categoryService = new CategoryService();
				Set<CategoryEntity> categoryList = categoryService.findAll();
				System.out.println(categoryList);
			}
	
}
