package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.service.TypeService;

public class TestCasesForType {
	
	
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

	
	///  TEST FOR VALID INPUT TO CREATE TYPE
	
			@Test
			@Order(1)
			void testCreateTypeWithValidInput() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				
				String random= generateRandomString(6);
				newType.setTypeName(random);
				newType.setImg("https://iili.io/HNO3V94.jpg");
				newType.setCateId(5);
				
				assertDoesNotThrow(()->{
					typeService.create(newType);
				});
			
			}
			
			//// TEST FOR INVALID INPUT
			
			@Test
			@Order(2)
			void testCreateTypeWithInvalidInput() {
				
				TypeService typeService = new TypeService();
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(null);
				});
				String exceptedMessage = "Invalid type input";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR CATEGORY ID WITH 0
			
			@Test 
			@Order(3)
			void testCreateTypeWithCategoryIdZero() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("kurti");
				newType.setImg("https://iili.io/HNO3V94.jpg");
				newType.setCateId(0);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Category id cannot be zero or in negative";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR CATEGORY DOESNOT EXISTS
			
			@Test 
			@Order(4)
			void testCreateTypeWithCategoryDoesnotExists() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Leggins");
				newType.setImg("https://iili.io/HNO3V94.jpg");
				newType.setCateId(21);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Category does not exists";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			@Order(5)
			void testCreateTypeWithTypeNameNull() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName(null);
				newType.setImg("https://iili.io/HNO3V94.jpg");
				newType.setCateId(4);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Type name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}

			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			@Order(6)
			void testCreateTypeWithTypeNameEmpty() {
						
					TypeService typeService = new TypeService();
				
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("");
					newType.setImg("https://iili.io/HNO3V94.jpg");
					newType.setCateId(5);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "Type name cannot be null or empty";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
				}
			
			////TEST FOR TYPE NAME WITH PATTERN	
//			
//			@Test 
//			void testCreateTypeWithTypeNamePattern() {
//					
//				TypeService typeService = new TypeService();
//				
//				TypeEntity newType = new TypeEntity();
//				newType.setTypeName("1234");
//				newType.setImg("https://iili.io/HNO3V94.jpg");
//				newType.setCateId(5);
//					
//				Exception exception = assertThrows(ValidationException.class, () -> {
//					typeService.create(newType);
//				});
//				String exceptedMessage = "Type name doesn't match the pattern";
//				String actualMessage = exception.getMessage();
//					
//				assertEquals(exceptedMessage,actualMessage);
//			}
			
				////TEST FOR TYPE NAME ALREADY EXISTS
			
				@Test 
				@Order(7)
				void testCreateTypeWithTypeNameAlredyExists() {
						
					TypeService typeService = new TypeService();
					
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("Saree");
					newType.setImg("https://iili.io/HNO3V94.jpg");
					newType.setCateId(5);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "This type name is already exists";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
				}

			//// TEST FOR TYPE IMAGE WITH NULL
				
				@Test 
				@Order(8)
				void testCreateTypeWithImageNameNull() {
					
					TypeService typeService = new TypeService();
					
					TypeEntity newType = new TypeEntity();
					String random= generateRandomString(6);
					newType.setTypeName(random);
					newType.setImg(null);
					newType.setCateId(8);
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "Image url cannot be null or empty";
					String actualMessage = exception.getMessage();
					
					assertEquals(exceptedMessage,actualMessage);
				}

				
				//// TEST FOR TYPE IMAGE WITH EMPTY STRING
				
				@Test 
				@Order(9)
				void testCreateTypeWithTypeImageEmpty() {
							
						TypeService typeService = new TypeService();
					
						TypeEntity newType = new TypeEntity();
						String random= generateRandomString(6);
						newType.setTypeName(random);
						newType.setImg("");
						newType.setCateId(5);
							
						Exception exception = assertThrows(ValidationException.class, () -> {
							typeService.create(newType);
						});
						String exceptedMessage = "Image url cannot be null or empty";
						String actualMessage = exception.getMessage();
							
						assertEquals(exceptedMessage,actualMessage);
					}
				
				//// TEST FOR UPDATE TYPE
				
				@Test
				@Order(10)
				void testUpdateType() {

					TypeService typeService = new TypeService();
					
					TypeEntity updatedType = new TypeEntity();
					String random= generateRandomString(6);
					updatedType.setTypeName(random);
					updatedType.setImg("https://iili.io/HNO3V94.jpg");
					updatedType.setCateId(1);
					
					assertDoesNotThrow(() -> {
						typeService.update(32, updatedType);
					});
					
				}
				
				//// TEST FOR DELETE TYPE
				
				@Test
				@Order(11)
				void deleteType() throws Exception {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						typeService.delete(35);
					});
					
				}
				
			//// TEST FOR GET ALL TYPES
				
				@Test
				@Order(12)
				void getAllTypes() throws ServiceException {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						Set<TypeEntity> typeList = typeService.findAll();
						System.out.println(typeList);
					});
					
				}
				
				@Test
				@Order(13)
				void getAllTypesByCategoryId() throws ServiceException {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						Set<TypeEntity> typeList = typeService.findAllTypesByCategoryId(5);
						System.out.println(typeList);
					});
					
				}
			
				@Test
				@Order(14)
				void getAllTypesByCategoryIdWithZero() throws ServiceException {
					
					TypeService typeService = new TypeService();
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						Set<TypeEntity> typeList = typeService.findAllTypesByCategoryId(0);
						System.out.println(typeList);
					});
					String exceptedMessage = "Category id cannot be zero or in negative";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
					
				}
				
				@Test
				@Order(15)
				void getAllTypesByCategoryIdWithInvalidid() throws ServiceException {
					
					TypeService typeService = new TypeService();
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						Set<TypeEntity> typeList = typeService.findAllTypesByCategoryId(10);
						System.out.println(typeList);
					});
					String exceptedMessage = "Category does not exists";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
					
				}
	
}
