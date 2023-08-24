package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.service.SizeService;

public class TestCasesForSize {
	
	
	 private String generateRandomString(int length) {
	        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuilder randomString = new StringBuilder();
	        java.util.Random random = new java.util.Random();

	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            randomString.append(characters.charAt(index));
	        }

	        return randomString.toString();
	    }

	///  TEST FOR VALID INPUT TO CREATE SIZE
	
		@Test
		void testCreateSizeWithValidInput() {
			
			SizeService sizeService = new SizeService();
			
			SizeEntity newSize = new SizeEntity();
			String random = generateRandomString(3);
			newSize.setSizeName(random);
		 
			
			assertDoesNotThrow(()->{ 
				sizeService.create(newSize);
			});
		
		}
		
		//// TEST FOR INVALID INPUT
		
		@Test
		void testCreateSizeWithInvalidInput() {
			
			SizeService sizeService = new SizeService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				sizeService.create(null);
			});
			String exceptedMessage = "Invalid size input";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR SIZE NAME WITH NULL
		
		@Test 
		void testCreateSizeWithSizenameNull() {
			
			SizeService sizeService = new SizeService();
			
			SizeEntity newSize = new SizeEntity();
			newSize.setSizeName(null);
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				sizeService.create(newSize);
			});
			String exceptedMessage = "Size name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
			//// TEST FOR SIZE NAME WITH EMPTY
		
			@Test 
			void testCreateSizeWithSizenameEmpty() {
				
				SizeService sizeService = new SizeService();
				
				SizeEntity newSize = new SizeEntity();
				newSize.setSizeName("");
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					sizeService.create(newSize);
				});
				String exceptedMessage = "Size name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
			}
			
		//// TEST FOR SIZE NAME WITH PATTERN
			
				@Test 
				void testCreateSizeWithSizenamePattern() {
					
					SizeService sizeService = new SizeService();
					
					SizeEntity newSize = new SizeEntity();
					newSize.setSizeName("&$");
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						sizeService.create(newSize);
					});
					String exceptedMessage = "Size name doesn't match the pattern";
					String actualMessage = exception.getMessage();
					
					assertEquals(exceptedMessage,actualMessage);
				}
				
			//// TEST FOR SIZE NAME WITH ALREADY EXISTS
				
					@Test 
					void testCreateSizeWithSizenameExists() {
						
						SizeService sizeService = new SizeService();
						
						SizeEntity newSize = new SizeEntity();
						newSize.setSizeName("XXL");
						
						Exception exception = assertThrows(ValidationException.class, () -> {
							sizeService.create(newSize);
						});
						String exceptedMessage = "This size is already exists";
						String actualMessage = exception.getMessage();
						
						assertEquals(exceptedMessage,actualMessage);
					}
		
					
				//// TEST FOR GET ALL SIZES
					
					@Test
					void getAllSizes() throws ServiceException {
						
						SizeService sizeService = new SizeService();
						
						assertDoesNotThrow(() -> {
							Set<SizeEntity> sizeList = sizeService.findAll();
							System.out.println(sizeList);
						});
						
					}
	
}
