package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.entity.OrderEntity;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.service.AddressService;
import in.fssa.tharasworld.service.OrderService;

public class TestCasesForOrders {
	
	
	@Test
	@Order(1)
	void testCreateOrdersWithValidInput() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(1);
		newOrder.setUserId(3);
		newOrder.setAddressId(1);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		assertDoesNotThrow(()->{
			orderService.create(newOrder);
		});
	
	}
	
	@Test
	@Order(2)
	void testCreateAddressWithInvalidInput() {
		
		OrderService orderService = new OrderService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(null);
		});
		String exceptedMessage = "Invalid order input";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test
	@Order(3)
	void testCreateOrdersWithInvalidPhoneNumber() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(2765432108l);
		newOrder.setQuantity(1);
		newOrder.setUserId(3);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	@Test
	@Order(4)
	void testCreateOrdersWithInvalidPhoneNumberLength() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(276548l);
		newOrder.setQuantity(1);
		newOrder.setUserId(3);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	@Test
	@Order(5)
	void testCreateOrdersWithQuantityZero() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(0);
		newOrder.setUserId(3);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Quantity cannot be less than or equal to zero or greater than 10";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	@Test
	@Order(6)
	void testCreateOrdersWithQuantityAboveTen() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(20);
		newOrder.setUserId(3);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Quantity cannot be less than or equal to zero or greater than 10";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}

	
	@Test
	@Order(7)
	void testCreateOrdersWithUserIdZero() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(0);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Invalid user id";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	@Test
	@Order(8)
	void testCreateOrdersWithInvalidUserId() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(100);
		newOrder.setAddressId(5);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	
	@Test
	@Order(9)
	void testCreateOrdersWithAddressIdZero() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(0);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Address id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}

	@Test
	@Order(10)
	void testCreateOrdersWithInvalidAddressId() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(100);
		newOrder.setPriceId(5);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Address id does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	@Test
	@Order(11)
	void testCreateOrdersWithPriceIdZero() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(2);
		newOrder.setPriceId(0);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Invalid id";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}

	@Test
	@Order(12)
	void testCreateOrdersWithInvalidPriceId() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(1);
		newOrder.setPriceId(100);
		newOrder.setSellerId(2);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Price id does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	
	@Test
	@Order(13)
	void testCreateOrdersWithSellerIdZero() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(1);
		newOrder.setPriceId(1);
		newOrder.setSellerId(0);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Seller id connot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	
	@Test
	@Order(14)
	void testCreateOrdersWithInvalidSellerId() {
		
		OrderService orderService = new OrderService();
		
		OrderEntity newOrder = new OrderEntity();
		newOrder.setPhoneNumber(8765432108l);
		newOrder.setQuantity(2);
		newOrder.setUserId(3);
		newOrder.setAddressId(1);
		newOrder.setPriceId(1);
		newOrder.setSellerId(3);
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String exceptedMessage = "Seller does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	
	}
	
	
	@Test 
	@Order(15)
	void testUpdateOrderStatusWithValidInputs() {
			
		OrderService orderService = new OrderService();
			
		assertDoesNotThrow(() -> {
			orderService.acceptOrder(1);
		});
	}
	
	@Test 
	@Order(16)
	void testUpdateOrderStatusWithOrderIdZero() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.acceptOrder(0);
		});
		String exceptedMessage = "Order id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(17)
	void testUpdateOrderStatusWithInvalidOrderId() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.acceptOrder(10);
		});
		String exceptedMessage = "Order does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test
	@Order(18)
	void cancelOrder() throws Exception {
		
		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
			orderService.cancelOrder(2);
		});
		
	}
	
	@Test 
	@Order(19)
	void testcancelOrderWithOrderIdZero() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.cancelOrder(0);
		});
		String exceptedMessage = "Order id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(20)
	void testcancelOrdersWithInvalidOrderId() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.cancelOrder(2);
		});
		String exceptedMessage = "Order does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	
	@Test 
	@Order(21)
	void testGetAllOrdersWithValidUserId() {
			
		OrderService orderService = new OrderService();
					
			assertDoesNotThrow(() -> {
				orderService.findOrdersByUserId(3);
			});

	}
	
	@Test 
	@Order(22)
	void testcancelOrdersWithUserIdZero() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.findOrdersByUserId(0);
		});
		String exceptedMessage = "Invalid user id";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(23)
	void testcancelOrdersWithInvalidUserId() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.findOrdersByUserId(100);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(24)
	void testGetAllOrdersWithValidSellerId() {
			
		OrderService orderService = new OrderService();
					
			assertDoesNotThrow(() -> {
				orderService.findOrdersByUserId(2);
			});

	}
	
	@Test 
	@Order(25)
	void testGetAllOrdersWithValidSellerIdZero() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.findOrdersByUserId(0);
		});
		String exceptedMessage = "Invalid user id";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(26)
	void testGetAllOrdersWithInvalidSellerId() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.findOrdersByUserId(200);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test
	@Order(27)
	void testOrderDelivered() throws Exception {
		
		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
			orderService.orderDelivered(2);
		});
		
	}
	
	@Test 
	@Order(28)
	void testOrderDeliveredWithOrderIdZero() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.orderDelivered(0);
		});
		String exceptedMessage = "Order id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
	@Test 
	@Order(29)
	void testOrderDeliveredWithInvalidOrderId() {
			
		OrderService orderService = new OrderService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.orderDelivered(200);
		});
		String exceptedMessage = "Order does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);

	}
	
}
