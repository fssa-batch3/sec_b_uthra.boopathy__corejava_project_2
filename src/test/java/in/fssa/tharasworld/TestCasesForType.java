package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.Set;

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
			void testCreateTypeWithValidInput() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				
				String random= generateRandomString(6);
				newType.setTypeName(random);
				newType.setCateId(3);
			
				
				assertDoesNotThrow(()->{
					typeService.create(newType);
				});
			
			}
			
			//// TEST FOR INVALID INPUT
			
			@Test
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
			void testCreateTypeWithCategoryIdZero() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Chudi");
				newType.setCateId(0);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Invalid category id";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR CATEGORY DOESNOT EXISTS
			
			@Test 
			void testCreateTypeWithCategoryDoesnotExists() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Chudi");
				newType.setCateId(11);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Category does not exists";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			void testCreateTypeWithTypeNameNull() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName(null);
				newType.setCateId(1);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Type name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}

			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			void testCreateTypeWithTypeNameEmpty() {
						
					TypeService typeService = new TypeService();
				
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("");
					newType.setCateId(1);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "Type name cannot be null or empty";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
				}
			
			////TEST FOR TYPE NAME WITH PATTERN	
			
			@Test 
			void testCreateTypeWithTypeNamePattern() {
					
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("1234");
				newType.setCateId(1);
					
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Type name doesn't match the pattern";
				String actualMessage = exception.getMessage();
					
				assertEquals(exceptedMessage,actualMessage);
			}
			
				////TEST FOR TYPE NAME ALREADY EXISTS
			
				@Test 
				void testCreateTypeWithTypeNameAlredyExists() {
						
					TypeService typeService = new TypeService();
					
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("Saree");
					newType.setCateId(3);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "This type name is already exists";
					String actualMessage = exception.getMessage();
						
					assertEquals(exceptedMessage,actualMessage);
				}

				
				//// TEST FOR UPDATE TYPE
				
				@Test
				void testUpdateType() {

					TypeService typeService = new TypeService();
					
					TypeEntity updatedType = new TypeEntity();
					updatedType.setTypeName("Croptop");
					updatedType.setCateId(3);
					
					assertDoesNotThrow(() -> {
						typeService.update(1, updatedType);
					});
					
				}
				
				//// TEST FOR DELETE CATEGORY
				
				@Test
				void deleteType() throws Exception {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						typeService.delete(1);
					});
					
				}
				
			//// TEST FOR GET ALL TYPES
				
				@Test
				public void getAllTypes() throws ServiceException {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						Set<TypeEntity> typeList = typeService.findAll();
						System.out.println(typeList);
					});
					
				}
			
	
}
