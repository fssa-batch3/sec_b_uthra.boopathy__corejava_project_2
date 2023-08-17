package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.service.SizeService;

public class TestCasesForSize {

	///  TEST FOR VALID INPUT TO CREATE SIZE
	
		@Test
		public void testCreateSizeWithValidInput() {
			
			SizeService sizeService = new SizeService();
			
			SizeEntity newSize = new SizeEntity();
			newSize.setSizeName("XXL");
		 
			
			assertDoesNotThrow(()->{
				sizeService.create(newSize);
			});
		
		}
		
		//// TEST FOR INVALID INPUT
		
		@Test
		public void testCreateSizeWithInvalidInput() {
			
			SizeService sizeService = new SizeService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				sizeService.create(null);
			});
			String exceptedMessage = "Invalid size input";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
		//// TEST FOR SIZE NAME WITH NULL
		
		@Test 
		public void testCreateSizeWithSizenameNull() {
			
			SizeService sizeService = new SizeService();
			
			SizeEntity newSize = new SizeEntity();
			newSize.setSizeName(null);
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				sizeService.create(newSize);
			});
			String exceptedMessage = "Size name cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}
		
			//// TEST FOR SIZE NAME WITH EMPTY
		
			@Test 
			public void testCreateSizeWithSizenameEmpty() {
				
				SizeService sizeService = new SizeService();
				
				SizeEntity newSize = new SizeEntity();
				newSize.setSizeName("");
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					sizeService.create(newSize);
				});
				String exceptedMessage = "Size name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertTrue(exceptedMessage.equals(actualMessage));
			}
			
		//// TEST FOR SIZE NAME WITH PATTERN
			
				@Test 
				public void testCreateSizeWithSizenamePattern() {
					
					SizeService sizeService = new SizeService();
					
					SizeEntity newSize = new SizeEntity();
					newSize.setSizeName("133");
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						sizeService.create(newSize);
					});
					String exceptedMessage = "Size name doesn't match the pattern";
					String actualMessage = exception.getMessage();
					
					assertTrue(exceptedMessage.equals(actualMessage));
				}
				
			//// TEST FOR SIZE NAME WITH ALREADY EXISTS
				
					@Test 
					public void testCreateSizeWithSizenameExists() {
						
						SizeService sizeService = new SizeService();
						
						SizeEntity newSize = new SizeEntity();
						newSize.setSizeName("XL");
						
						Exception exception = assertThrows(ValidationException.class, () -> {
							sizeService.create(newSize);
						});
						String exceptedMessage = "This size is already exists";
						String actualMessage = exception.getMessage();
						
						assertTrue(exceptedMessage.equals(actualMessage));
					}
		
					
				//// TEST FOR GET ALL SIZES
					
					@Test
					public void getAllSizes() {
						SizeService sizeService = new SizeService();
						Set<SizeEntity> sizeList = sizeService.findAll();
						System.out.println(sizeList);
					}
	
}
