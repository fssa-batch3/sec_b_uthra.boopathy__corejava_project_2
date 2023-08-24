package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.*;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.dto.*;
import in.fssa.tharasworld.entity.*;
import in.fssa.tharasworld.service.*;

public class TestCasesForProduct {
	
	
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
	
	///  TEST FOR VALID INPUT TO CREATE PRODUCT
	
			@Test
			void testCreateProductWithValidInput() {
				
				ProductService productService = new ProductService();
				
				ProductDetailDTO newproduct = new ProductDetailDTO();
				
				String random = generateRandomString(6);
				
				newproduct.setName(random);
				
				String random1 = generateRandomString(20);
				
				newproduct.setDescription(random1);
				newproduct.setSellerId(2);
				newproduct.setTypeId(3);
				
				List<PriceEntity> prices = new ArrayList<>();
				
				PriceEntity p1 = new PriceEntity();
				p1.setActualPrice(1000);
				p1.setCurrentPrice(500);
				p1.setDiscount(50.00);
				p1.setSizeId(5);
				
				prices.add(p1);
				
				PriceEntity p2 = new PriceEntity();
				p2.setActualPrice(1500);
				p2.setCurrentPrice(500);
				p2.setDiscount(40.00);
				p2.setSizeId(5);
				
				prices.add(p2);
				
				newproduct.setListOfPrices(prices);
				
				assertDoesNotThrow(()->{
					productService.create(newproduct);
				});
			
			}

			@Test
			void testCreateProductWithProductNameNull() {}
			
}
