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
				newType.setCateId(5);
			
				
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
				String exceptedMessage = "Category id cannot be zero or in negative";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
			//// TEST FOR CATEGORY DOESNOT EXISTS
			
			@Test 
			void testCreateTypeWithCategoryDoesnotExists() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Chudi");
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
			void testCreateTypeWithTypeNameNull() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName(null);
				newType.setCateId(8);
				
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
					newType.setCateId(5);
						
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
				newType.setCateId(5);
					
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
					newType.setTypeName("weieIC");
					newType.setCateId(5);
						
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
					updatedType.setCateId(5);
					
					assertDoesNotThrow(() -> {
						typeService.update(6, updatedType);
					});
					
				}
				
				//// TEST FOR DELETE TYPE
				
				@Test
				void deleteType() throws Exception {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						typeService.delete(9);
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
				
				@Test
				public void getAllTypesByCategoryId() throws ServiceException {
					
					TypeService typeService = new TypeService();
					assertDoesNotThrow(() -> {
						Set<TypeEntity> typeList = typeService.findAllTypesByCategoryId(5);
						System.out.println(typeList);
					});
					
				}
			
	
}
