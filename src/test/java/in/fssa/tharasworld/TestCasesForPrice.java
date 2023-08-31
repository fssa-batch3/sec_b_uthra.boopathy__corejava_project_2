package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.service.PriceService;

public class TestCasesForPrice {

	

	//// TEST FOR UPDATE PRICE
	
	@Test
	void testUpdatePrice() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();
		
		int productId = 5;
		int sizeId = 6;

		updatePrice.setActualPrice(1500);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(6);
		
		assertDoesNotThrow(() -> {
			priceService.update(productId, sizeId, updatePrice);
			
		});
		
	}
	
	//// TEST UPDATE PRODUCT WITH INVALID PRICE ID
	
	@Test
	void testUpdatePriceWithProductIdZero() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();
		
		int productId = 0;
		int sizeId = 6;

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(sizeId);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(productId, sizeId, updatePrice);
        });

        String expectedMessage = "Product id cannot be zero or in negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test
	void testUpdatePriceWithsizeIdZero() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();
		
		int productId = 5;
		int sizeId = 0;

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(sizeId);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(productId, sizeId, updatePrice);
        });

        String expectedMessage = "Size id cannot be zero or in negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	/// product does not exists
	
	@Test
	void testUpdatePriceWithProductIdDoesNotExists() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();
		
		int productId = 11;
		int sizeId = 6;

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(sizeId);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(productId, sizeId, updatePrice);
        });

        String expectedMessage = "Product does not exixts";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	//// size does not exists
	
	@Test
	void testUpdatePriceWithSizeIdDoesNotExists() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();
		
		int productId = 10;
		int sizeId = 2;

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(sizeId);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(productId, sizeId, updatePrice);
        });

        String expectedMessage = "Size does not exists";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	//// TEST FOR DELETE PRICE
	
	@Test
	void deletePrice() throws ServiceException, ValidationException {
		
		PriceService priceService = new PriceService();
		assertDoesNotThrow ( () -> {
			priceService.delete(13);
		});
		
	}
	
	@Test
	void testDeletePriceWithPriceIdZero() {

		PriceService priceService = new PriceService();
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.delete(0);
        });

        String expectedMessage = "Price id cannot be zero or in negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test
	void testDeletePrricetWithInvalidPriceId() {

		PriceService priceService = new PriceService();
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.delete(2);
        });

        String expectedMessage = "Price id does not exists";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
		
	
}
