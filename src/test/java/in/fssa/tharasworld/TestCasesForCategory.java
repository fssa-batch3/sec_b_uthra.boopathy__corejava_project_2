package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Order;
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
		@Order(1)
		void testCreateCategoryWithValidInput() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			
			String random = generateRandomString(7);
			newCategory.setCateName(random);
			newCategory.setImg("https://iili.io/HvZYe0x.webp");
			
			assertDoesNotThrow(()->{
				categoryService.create(newCategory);
			});
		
		}
		
		//// TEST FOR INVALID INPUT
		
		@Test
		@Order(2)
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
		@Order(3)
		void testCreateCategoryWithCategoryNameNull() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName(null);
			newCategory.setImg("https://iili.io/HvZYe0x.webp");
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR CATEGORY WITH EMPTY STRING
		
		@Test 
		@Order(4)
		void testCreateCategoryWithCategoryNameEmpty() {
			
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("");
			newCategory.setImg("https://iili.io/HvZYe0x.webp");
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR CATEGORY IMAGE WITH NULL
		
			@Test 
			@Order(5)
			void testCreateCategoryWithCategoryImageNull() {
				
				CategoryService categoryService = new CategoryService();
				
				CategoryEntity newCategory = new CategoryEntity();
				String random = generateRandomString(7);
				newCategory.setCateName(random);
				newCategory.setImg(null);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.create(newCategory);
				});
				String exceptedMessage = "Image url cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR CATEGORY WITH EMPTY STRING
			
			@Test 
			@Order(6)
			void testCreateCategoryWithCategoryImageEmpty() {
				
				CategoryService categoryService = new CategoryService();
				
				CategoryEntity newCategory = new CategoryEntity();
				String random = generateRandomString(7);
				newCategory.setCateName(random);
				newCategory.setImg("");
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.create(newCategory);
				});
				String exceptedMessage = "Image url cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
		
		////TEST FOR CATEGORY NAME WITH PATTERN	
		
		@Test 
		@Order(7)
		void testCreateCategoryWithNamePattern() {
				
			CategoryService categoryService = new CategoryService();
			
			CategoryEntity newCategory = new CategoryEntity();
			newCategory.setCateName("13245");
			newCategory.setImg("https://iili.io/J9CoLXV.webp");
				
			Exception exception = assertThrows(ValidationException.class, () -> {
				categoryService.create(newCategory);
			});
			String exceptedMessage = "Category name doesn't match the pattern";
			String actualMessage = exception.getMessage();
				
			assertEquals(exceptedMessage,actualMessage);
		}
		
			////TEST FOR CATEGORY NAME ALREADY EXISTS
		
			@Test 
			@Order(8)
			void testCreateCategoryWithCategoryNameAlredyExists() {
					
				CategoryService categoryService = new CategoryService();
				
				CategoryEntity newCategory = new CategoryEntity();
				newCategory.setCateName("Cosmetics");
				newCategory.setImg("https://iili.io/J9CoLXV.webp");
					
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.create(newCategory);
				});
				String exceptedMessage = "This category name is already exists";
				String actualMessage = exception.getMessage();
					
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR UPDATE CATEGORY
			
			@Test
			@Order(9)
			void testUpdateCategory() {

				CategoryService categoryService = new CategoryService();
				
				CategoryEntity updatedCategory = new CategoryEntity();
				String random = generateRandomString(7);
				updatedCategory.setCateName(random);
				updatedCategory.setImg("https://iili.io/J9CoLXV.webp");
				
				assertDoesNotThrow(() -> {
					categoryService.update(6, updatedCategory);
				});
				
			}
			
			@Test
			@Order(10)
			void testUpdateCategoryWithNameNull() {

				CategoryService categoryService = new CategoryService();
				
				CategoryEntity updatedCategory = new CategoryEntity();
				updatedCategory.setCateName(null);
				updatedCategory.setImg("https://iili.io/J9CoLXV.webp");
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.update(8, updatedCategory);
				});
				String exceptedMessage = "Category name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);				
				
			}
			
			@Test
			@Order(11)
			void testUpdateCategoryWithNameEmpty() {

				CategoryService categoryService = new CategoryService();
				
				CategoryEntity updatedCategory = new CategoryEntity();
				updatedCategory.setCateName("");
				updatedCategory.setImg("https://iili.io/J9CoLXV.webp");
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.update(8, updatedCategory);
				});
				String exceptedMessage = "Category name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
					
				
			}

			
			//// TEST FOR DELETE CATEGORY
			
			@Test
			@Order(12)
			void deleteCategory() throws Exception {
				
				CategoryService categoryService = new CategoryService();
				assertDoesNotThrow(() -> {
					categoryService.delete(12);
				});
				
			}
			
			@Test
			@Order(13)
			void deleteCategoryWithIdZero() throws Exception {
				
				CategoryService categoryService = new CategoryService();
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.delete(0);
				});
				String exceptedMessage = "Category id cannot be zero or in negative";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);				
				
			}
			
			@Test
			@Order(14)
			void deleteCategoryWithInvalidId() throws Exception {
				
				CategoryService categoryService = new CategoryService();
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					categoryService.delete(100);
				});
				String exceptedMessage = "Category does not exists";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);				
				
			}
		
		//// TEST FOR GET ALL CATEGORY
			
			@Test
			@Order(15)
			public void getAllCategories() throws ServiceException {
				
				CategoryService categoryService = new CategoryService();
				assertDoesNotThrow(() -> {
					Set<CategoryEntity> categoryList = categoryService.findAll();
					System.out.println(categoryList);
				});
				
			}
	
}
