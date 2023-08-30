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

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(6);
		
		assertDoesNotThrow(() -> {
			priceService.update(4, updatePrice);
		});
		
	}
	
	//// TEST UPDATE PRODUCT WITH INVALID PRICE ID
	
	@Test
	void testUpdatePriceWithPriceIdZero() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(6);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(0, updatePrice);
        });

        String expectedMessage = "Price id cannot be zero or in negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test
	void testUpdatePriceWithInvalidPriceId() {

		PriceService priceService = new PriceService();
		
		PriceEntity updatePrice = new PriceEntity();

		updatePrice.setActualPrice(10000);
		updatePrice.setCurrentPrice(5975);
		updatePrice.setDiscount(32);
		updatePrice.setSizeId(6);
		
        Exception exception = assertThrows(ValidationException.class, () -> {
        	priceService.update(3, updatePrice);
        });

        String expectedMessage = "Price id does not exists";
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
