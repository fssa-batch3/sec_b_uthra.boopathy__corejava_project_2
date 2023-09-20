package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.service.AddressService;
import in.fssa.tharasworld.service.CategoryService;

public class TestCasesForAddress {

	
	@Test
	@Order(1)
	void testCreateAddressWithValidInput() {
		
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("456, Middle street, Tirunelveli");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
		
		assertDoesNotThrow(()->{
			addressService.create(newAddress);
		});
	
	}
	
	@Test
	@Order(2)
	void testCreateAddressWithInvalidInput() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(null);
		});
		String exceptedMessage = "Invalid address input";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(3)
	void testCreateAddressWithNameNull() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName(null);
		newAddress.setAddress("34 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(4)
	void testCreateAddressWithNameEmpty() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("");
		newAddress.setAddress("34 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	
	@Test 
	@Order(5)
	void testCreateAddressWithAddressNull() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress(null);
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(6)
	void testCreateAddressWithAddressEmpty() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(7)
	void testCreateAddressWithAddressAlredyExists() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("34 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Address already exists";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(8)
	void testCreateAddressWithStateNull() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("94 B, Melaputhu street, Ambasamudram");
		newAddress.setState(null);
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "State cannot be null or empty";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(9)
	void testCreateAddressWithPincodeZero() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("64 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(0);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Pincode cannot be zero or in negative";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(10)
	void testCreateAddressWithInvalidPincode() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("64 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(727234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(1);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "Invalid pincode";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(11)
	void testCreateAddressWithUserIdZero() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("64 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(0);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "User Id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(11)
	void testCreateAddressWithInvalidUserId() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setName("Uthra");
		newAddress.setAddress("64 B, Melaputhu street, Ambasamudram");
		newAddress.setState("Tamil Nadu");
		newAddress.setPincode(627234);
		newAddress.setSetAsDefaultStatus(true);
		newAddress.setUserId(100);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.create(newAddress);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test 
	@Order(12)
	void testUpdateAddressWithValidInputs() {
			
		AddressService addressService = new AddressService();
		
		AddressEntity updatedAddress = new AddressEntity();
		updatedAddress.setName("Uthra");
		updatedAddress.setAddress("104 B, Melaputhu street, Ambasamudram");
		updatedAddress.setState("Tamil Nadu");
		updatedAddress.setPincode(627234);
			
		assertDoesNotThrow(() -> {
			addressService.update(2, updatedAddress);
		});
	}
	
	@Test
	@Order(13)
	void deleteAddress() throws Exception {
		
		AddressService addressService = new AddressService();
		assertDoesNotThrow(() -> {
			addressService.delete(3);
		});
		
	}
	
	@Test
	@Order(14)
	void deleteAddressIdWithIdZero() throws Exception {
		
		AddressService addressService = new AddressService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.delete(0);
		});
		String exceptedMessage = "Address id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);				
		
	}
	
	@Test
	@Order(15)
	void deleteAddressWithInvalidId() throws Exception {
		
		AddressService addressService = new AddressService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.delete(100);
		});
		String exceptedMessage = "Address id does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);				
		
	}
	
	@Test
	@Order(16)
	void findByAddressId() {
		
		AddressService addressService = new AddressService();
		assertDoesNotThrow(() -> {
			addressService.findByAddressId(4);
		});
		
	}
	
	@Test
	@Order(17)
	void findByAddressIdWithZero() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.findByAddressId(0);
		});
		String exceptedMessage = "Address id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(18)
	void findByAddressIdWithInvalidId() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.findByAddressId(100);
		});
		String exceptedMessage = "Address id does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(19)
	void findAddressesByUserId() {
		
		AddressService addressService = new AddressService();
		assertDoesNotThrow(() -> {
			addressService.findAddressesByUserId(3);
		});
		
	}
	
	@Test
	@Order(20)
	void findByUserIdWithZero() throws Exception {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.findAddressesByUserId(0);
		});
		String exceptedMessage = "User Id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(21)
	void findByUserIdWithInvalidId() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.findAddressesByUserId(100);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(22)
	void testSetAsDefaultAddressWithValidId() {
		
		AddressService addressService = new AddressService();
		assertDoesNotThrow(() -> {
			addressService.setAsDefaultAddress(3, 3);
		});
		
		
	}
	
	@Test
	@Order(23)
	void testSetAsDefaultAddressWithUserIdZero() throws Exception {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.setAsDefaultAddress(0, 3);
		});
		String exceptedMessage = "User Id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(24)
	void testSetAsDefaultAddressWithInvalidUserId() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.setAsDefaultAddress(100, 3);
		});
		String exceptedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(25)
	void testSetAsDefaultAddressWithAddressIdZero() throws Exception {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.setAsDefaultAddress(3, 0);
		});
		String exceptedMessage = "Address id cannot be zero or in negative";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
	@Test
	@Order(26)
	void testSetAsDefaultAddressWithInvalidAddressId() {
		
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.setAsDefaultAddress(3, 100);
		});
		String exceptedMessage = "Address id does not exists";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
		
	}
	
}
