package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.dto.*;
import in.fssa.tharasworld.entity.*;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.service.*;

public class TestCasesForProduct {
	
	
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
				newproduct.setTypeId(7);
				
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

			///  TEST FOR INVALID INPUT TO CREATE PRODUCT
			
			@Test
			void testCreateProductWithInValidInput() {
				
				ProductService productService = new ProductService();
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					productService.create(null);
				});
				String exceptedMessage = "Invalid product input";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
				
			}
			
			@Test
			void testCreateProductWithNameNull() {
				
				ProductService productService = new ProductService();
				
				ProductDetailDTO newProduct = new ProductDetailDTO();
				
				String random = generateRandomString(15);
				
				newProduct.setName(null);
								
				newProduct.setDescription(random);
				newProduct.setSellerId(2);
				newProduct.setTypeId(3);
				
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
				
				newProduct.setListOfPrices(prices);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					productService.create(newProduct);
				});
				String exceptedMessage = "Product name cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
				
			}
			
			@Test
			void testCreateProductWithNameEmpty() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        // Set the product name to null
		        newProduct.setName(null);

		        String random = generateRandomString(15);
		        newProduct.setDescription(random);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(3);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Product name cannot be null or empty";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithInvalidName() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        newProduct.setName("243");

		        String random = generateRandomString(15);
		        newProduct.setDescription(random);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(3);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Product name doesn't match the pattern";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithDescriptionNull() {
				
				ProductService productService = new ProductService();
				
				ProductDetailDTO newProduct = new ProductDetailDTO();
				
				String random = generateRandomString(6);
				
				newProduct.setName(random);
								
				newProduct.setDescription(null);
				newProduct.setSellerId(2);
				newProduct.setTypeId(3);
				
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
				
				newProduct.setListOfPrices(prices);
				
				Exception exception = assertThrows(ValidationException.class, () -> {
					productService.create(newProduct);
				});
				String exceptedMessage = "Description cannot be null or empty";
				String actualMessage = exception.getMessage();
				
				assertEquals(exceptedMessage,actualMessage);
				
			}
			
			@Test
			void testCreateProductWithDescriptionEmpty() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);

		        newProduct.setDescription("");
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(3);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Description cannot be null or empty";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithSellerIdWithZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(0);
		        newProduct.setTypeId(3);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Seller id connot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithSellerIdAsBuyer() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(4);
		        
		        newProduct.setTypeId(3);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Seller does not exist";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithTypeIdWithZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(0);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Type id cannot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithTypeIdDoesnotExists() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(17);

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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Type id does not exists";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithPriceNull() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();
		        
		        prices.add(null);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Price cannot be null or empty";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithActualPriceZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(0);
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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Actual price connot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			

			@Test
			void testCreateProductWithActualPriceGreaterThanOrEqualToLakh() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(100000);
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

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Actual price must be less than 1 lakh";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithCurrentPriceZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(0);
		        p1.setDiscount(50.00);
		        p1.setSizeId(5);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Current price connot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			

			@Test
			void testCreateProductWithCurrentPriceGreaterThanOrEqualToLakh() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(100000);
		        p1.setDiscount(50.00);
		        p1.setSizeId(5);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Current price must be less than 1 lakh";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithDiscountLessThanZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(500);
		        p1.setDiscount(-5);
		        p1.setSizeId(5);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Invalid Discount";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithDiscountGreaterThanOrEqualTo100() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(100);
		        p1.setDiscount(100.00);
		        p1.setSizeId(5);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Discount must be less than 100";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithSizeIdZero() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(100);
		        p1.setDiscount(10.00);
		        p1.setSizeId(0);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Size id cannot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testCreateProductWithSizeIdDoesNotExist() {
				
				ProductService productService = new ProductService();

		        ProductDetailDTO newProduct = new ProductDetailDTO();

		        String random = generateRandomString(6);

		        newProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        newProduct.setDescription(random1);
		        newProduct.setSellerId(2);
		        newProduct.setTypeId(5);

		        List<PriceEntity> prices = new ArrayList<>();

		        PriceEntity p1 = new PriceEntity();
		        p1.setActualPrice(1000);
		        p1.setCurrentPrice(100);
		        p1.setDiscount(10.00);
		        p1.setSizeId(2);

		        prices.add(p1);

		        PriceEntity p2 = new PriceEntity();
		        p2.setActualPrice(1500);
		        p2.setCurrentPrice(500);
		        p2.setDiscount(40.00);
		        p2.setSizeId(5);

		        prices.add(p2);

		        newProduct.setListOfPrices(prices);

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.create(newProduct);
		        });

		        String expectedMessage = "Size does not exists";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
		//// TEST FOR GET ALL PRODUCTS
			
			@Test
			void getAllProducts() {
				
				ProductService productService = new ProductService();
				assertDoesNotThrow (() -> {
					Set<ProductDetailDTO> productList = productService.findAll();
					System.out.println(productList);
				});
				
			}
			
			//// TEST FOR GET PRODUCT BY ID
			
			@Test
			void getByProductId() {
				
				ProductService productService = new ProductService();
				
				assertDoesNotThrow (() -> {
					ProductDetailDTO productList = productService.findByProductId(1);
					System.out.println(productList);
				});
				
			}
			
			@Test
			void getByProductIdWithZero() {
				
				ProductService productService = new ProductService();
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						
						System.out.println(productService.findByProductId(0));

			        });

			        String expectedMessage = "Product id cannot be zero or in negative";
			        String actualMessage = exception.getMessage();

			        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void getByProductIdWithInvalidId() {
				
				ProductService productService = new ProductService();
					
					Exception exception = assertThrows(ValidationException.class, () -> {
						
						System.out.println(productService.findByProductId(2));

			        });

			        String expectedMessage = "Product does not exixts";
			        String actualMessage = exception.getMessage();

			        assertEquals(expectedMessage, actualMessage);
				
			}
			
			//// TEST FOR UPDATE USER
			
			@Test
			void testUpdateProduct() {

				ProductService productService = new ProductService();
				
				ProductEntity updateProduct = new ProductEntity();

				String random = generateRandomString(6);

				updateProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        updateProduct.setDescription(random1);
		        updateProduct.setSellerId(2);
		        updateProduct.setTypeId(5);
				
				assertDoesNotThrow(() -> {
					productService.update(4, updateProduct);
				});
				
			}
			
			//// TEST UPDATE PRODUCT WITH INVALID PRODUCT ID
			
			@Test
			void testUpdateProductWithProductIdZero() {

				ProductService productService = new ProductService();
				
				ProductEntity updateProduct = new ProductEntity();

				String random = generateRandomString(6);

				updateProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        updateProduct.setDescription(random1);
		        updateProduct.setSellerId(2);
		        updateProduct.setTypeId(5);
				
		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.update(0, updateProduct);
		        });

		        String expectedMessage = "Product id cannot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testUpdateProductWithInvalidProductId() {

				ProductService productService = new ProductService();
				
				ProductEntity updateProduct = new ProductEntity();

				String random = generateRandomString(6);

				updateProduct.setName(random);
		        
		        String random1 = generateRandomString(16);

		        updateProduct.setDescription(random1);
		        updateProduct.setSellerId(2);
		        updateProduct.setTypeId(5);
				
		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.update(2, updateProduct);
		        });

		        String expectedMessage = "Product does not exixts";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			
			//// TEST FOR DELETE PRODUCT
			
			@Test
			void deleteProduct() throws ServiceException, ValidationException {
				
				ProductService productService = new ProductService();
				assertDoesNotThrow ( () -> {
					productService.delete(7);
				});
				
			}
			
			@Test
			void testDeleteProductWithProductIdZero() {

				ProductService productService = new ProductService();
				
		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.delete(0);
		        });

		        String expectedMessage = "Product id cannot be zero or in negative";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			@Test
			void testDeleteProductWithInvalidProductId() {

				ProductService productService = new ProductService();
				
				ProductEntity updateProduct = new ProductEntity();
				
		        Exception exception = assertThrows(ValidationException.class, () -> {
		            productService.delete(2);
		        });

		        String expectedMessage = "Product does not exixts";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
				
			}
			
			
		//// TEST FOR GET PRODUCTS BY CATEGORY ID
			
					@Test
					void getAllProductsByCategoryId() {
						
						ProductService productService = new ProductService();
						
						assertDoesNotThrow (() -> {
							Set<ProductDetailDTO> productList = productService.findByCategoryId(4);
							System.out.println(productList);
						});
						
					}
					
					@Test
					void getAllProductsByCategoryIdWithZero() {
						
						ProductService productService = new ProductService();
							
							Exception exception = assertThrows(ValidationException.class, () -> {
								
								System.out.println(productService.findByCategoryId(0));

					        });

					        String expectedMessage = "Category id cannot be zero or in negative";
					        String actualMessage = exception.getMessage();

					        assertEquals(expectedMessage, actualMessage);
						
					}
					
					@Test
					void getAllProductsByCategoryIdInvalidCategoryId() {
						
						ProductService productService = new ProductService();
							
							Exception exception = assertThrows(ValidationException.class, () -> {
								
								System.out.println(productService.findByCategoryId(2));

					        });

					        String expectedMessage = "Category does not exists";
					        String actualMessage = exception.getMessage();

					        assertEquals(expectedMessage, actualMessage);
						
					}
		
				//// TEST FOR GET PRODUCTS BY TYPE ID
					
							@Test
							void getAllProductsByTypeId() {
								
								ProductService productService = new ProductService();
								
								assertDoesNotThrow (() -> {
									Set<ProductDetailDTO> productList = productService.findByTypeId(6);
									System.out.println(productList);
								});
								
							}
							
							@Test
							void getAllProductsByTypeIdWithZero() {
								
								ProductService productService = new ProductService();
									
									Exception exception = assertThrows(ValidationException.class, () -> {
										
										System.out.println(productService.findByTypeId(0));

							        });

							        String expectedMessage = "Type id cannot be zero or in negative";
							        String actualMessage = exception.getMessage();

							        assertEquals(expectedMessage, actualMessage);
								
							}
							
							@Test
							void getAllProductsByTypeIdInvalidTypeId() {
								
								ProductService productService = new ProductService();
									
									Exception exception = assertThrows(ValidationException.class, () -> {
										
										System.out.println(productService.findByTypeId(2));

							        });

							        String expectedMessage = "Type id does not exists";
							        String actualMessage = exception.getMessage();

							        assertEquals(expectedMessage, actualMessage);
								
							}
				
}
