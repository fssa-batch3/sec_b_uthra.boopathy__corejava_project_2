package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.service.CategoryService;

public class TestCasesForCategory {
	
	 private String generateRandomString(int length) {
	        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuilder randomString = new StringBuilder();
	        java.util.Random random = new java.util.Random();

	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            randomString.append(characters.charAt(index));
	        }

	        return randomString.toString();
	    }

	///  TEST FOR VALID INPUT TO CREATE CATEGORY
	
		@Test
		void testCreateCategoryWithValidInput() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			
			String random = generateRandomString(7);
			newCategory.setCateName(random);
		
			
			assertDoesNotThrow(()->{
				categoryService.create(newCategory);
			});
		
		}
		
		//// TEST FOR INVALID INPUT
		
		@Test
		void testCreateCategoryWithInvalidInput() {
			
			CategoryService categoryService = new CategoryService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(null);
			});
			String exceptedMessage = "Invalid category input";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR CATEGORY WITH NULL
		
		@Test 
		void testCreateCategoryWithCategoryNameNull() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName(null);
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR CATEGORY WITH EMPTY STRING
		
		@Test 
		void testCreateCategoryWithCategoryNameEmpty() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("");
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		////TEST FOR CATEGORY NAME WITH PATTERN	
		
		@Test 
		void testCreateCategoryWithNamePattern() {
				
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("13245");
				
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name doesn't match the pattern";
			String actualMessage = exception.getMessage();
				
			assertEquals(exceptedMessage,actualMessage);
		}
		
			////TEST FOR CATEGORY NAME ALREADY EXISTS
		
			@Test 
			void testCreateCategoryWithCategoryNameAlredyExists() {
					
				CategoryService categoryService = new CategoryService();
				
				CategoryEntity newCategory = new CategoryEntity();
				newCategory.setCateName("Dress");
					
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.create(newCategory);
				});
				String exceptedMessage = "This category name is already exists";
				String actualMessage = exception.getMessage();
					
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR UPDATE CATEGORY
			
			@Test
			void testUpdateCategory() {

				CategoryService categoryService = new CategoryService();
				
				CategoryEntity updatedCategory = new CategoryEntity();
				updatedCategory.setCateName("Dresses");
				
				assertDoesNotThrow(() -> {
					categoryService.update(1, updatedCategory);
				});
				
			}
			
			//// TEST FOR DELETE CATEGORY
			
			@Test
			void deleteCategory() throws Exception {
				
				CategoryService categoryService = new CategoryService();
				assertDoesNotThrow(() -> {
					categoryService.delete(1);
				});
				
			}
		
		//// TEST FOR GET ALL CATEGORY
			
			@Test
			public void getAllCategories() throws ServiceException {
				
				CategoryService categoryService = new CategoryService();
				assertDoesNotThrow(() -> {
					Set<CategoryEntity> categoryList = categoryService.findAll();
					System.out.println(categoryList);
				});
				
			}
	
}
