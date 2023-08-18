package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.service.TypeService;

public class TestCasesForType {

	
	///  TEST FOR VALID INPUT TO CREATE TYPE
	
			@Test
			public void testCreateTypeWithValidInput() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Saree");
				newType.setCateId(3);
			
				
				assertDoesNotThrow(()->{
					typeService.create(newType);
				});
			
			}
			
			//// TEST FOR INVALID INPUT
			
			@Test
			public void testCreateTypeWithInvalidInput() {
				
				TypeService typeService = new TypeService();
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(null);
				});
				String exceptedMessage = "Invalid type input";
				String actualMessage = exception.getMessage();
				
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
			//// TEST FOR CATEGORY ID WITH 0
			
			@Test 
			public void testCreateTypeWithCategoryIdZero() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Chudi");
				newType.setCateId(0);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Invalid category id";
				String actualMessage = exception.getMessage();
				
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
			//// TEST FOR CATEGORY DOESNOT EXISTS
			
			@Test 
			public void testCreateTypeWithCategoryDoesnotExists() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("Chudi");
				newType.setCateId(11);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Category does not exists";
				String actualMessage = exception.getMessage();
				
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			public void testCreateTypeWithTypeNameNull() {
				
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName(null);
				newType.setCateId(1);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Type name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertTrue(exceptedMessage.equals(actualMessage));
			}

			
			//// TEST FOR TYPE NAME WITH NULL
			
			@Test 
			public void testCreateTypeWithTypeNameEmpty() {
						
					TypeService typeService = new TypeService();
				
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("");
					newType.setCateId(1);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "Type name cannot be null or empty";
					String actualMessage = exception.getMessage();
						
					assertTrue(exceptedMessage.equals(actualMessage));
				}
			
			////TEST FOR TYPE NAME WITH PATTERN	
			
			@Test 
			public void testCreateTypeWithTypeNamePattern() {
					
				TypeService typeService = new TypeService();
				
				TypeEntity newType = new TypeEntity();
				newType.setTypeName("1234");
				newType.setCateId(1);
					
				Exception exception = assertThrows(ValidationException.class, () -> {
					typeService.create(newType);
				});
				String exceptedMessage = "Type name doesn't match the pattern";
				String actualMessage = exception.getMessage();
					
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
				////TEST FOR TYPE NAME ALREADY EXISTS
			
				@Test 
				public void testCreateUserWithTypeNameAlredyExists() {
						
					TypeService typeService = new TypeService();
					
					TypeEntity newType = new TypeEntity();
					newType.setTypeName("Chudi");
					newType.setCateId(1);
						
					Exception exception = assertThrows(ValidationException.class, () -> {
						typeService.create(newType);
					});
					String exceptedMessage = "This type name is already exists";
					String actualMessage = exception.getMessage();
						
					assertTrue(exceptedMessage.equals(actualMessage));
				}

				
				//// TEST FOR UPDATE TYPE
				
				@Test
				public void testUpdateType() {

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
				public void deleteType() throws Exception {
					
					TypeService typeService = new TypeService();
					
					typeService.delete(1);
					
				}
				
			//// TEST FOR GET ALL TYPES
				
				@Test
				public void getAllTypes() {
					TypeService typeService = new TypeService();
					Set<TypeEntity> typeList = typeService.findAll();
					System.out.println(typeList);
				}
			
	
}
