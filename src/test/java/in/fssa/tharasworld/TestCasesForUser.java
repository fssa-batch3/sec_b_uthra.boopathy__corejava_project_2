package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.service.UserService;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;

public class TestCasesForUser {
	
	
	////  GENERATE AUTOMATIC STRING FOR EMAIL
	
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
	 
	 //// GENERATE AUTOMATIC LONG FOR PHONE NUMBER
	 
	 private long generateRandomPhoneNumber(int length) {
	        java.util.Random random = new java.util.Random();

	        // Define the range for the random phone number
	        long minPhoneNumber = 600_000_0000L; // Replace with the desired minimum value
	        long maxPhoneNumber = 999_999_9999L; // Replace with the desired maximum value

	        // Generate a random long number within the specified range
	        long phoneNumber = minPhoneNumber + (long) (random.nextDouble() * (maxPhoneNumber - minPhoneNumber + 1));

	        return phoneNumber;
	    }
	   
	 @Test
	    void testCreateUserWithValidInput() {
	        UserService userService = new UserService();

	        UserEntity newUser = new UserEntity();

	        newUser.setName("Ramya");

	        String randomString = generateRandomString(8);
	        newUser.setEmail(randomString + "@gmail.com");

	        long randomPhoneNumber = generateRandomPhoneNumber(10);
	        newUser.setPhoneNumber(randomPhoneNumber);

	        newUser.setPassword("Thamim@12");
	        newUser.setRole("Buyer");
	        newUser.setAge(18);

	        assertDoesNotThrow(() -> {
	            userService.create(newUser);
	        });
	    }
	 
	//// TEST FOR INVALID INPUT
	  
	@Test
	void testCreateUserWithInvalidInput() {
		
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String exceptedMessage = "Invalid user input";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	}
	
	//// TEST FOR EMAIL WITH NULL
	
	@Test 
	void testCreateUserWithEmailNull() {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		newUser.setName("Uthra");
		newUser.setEmail(null);
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	}
	
	//// TEST FOR EMAIL WITH EMPTY STRING
	
	@Test 
	void testCreateUserWithEmailEmpty() {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		newUser.setName("Uthra");
		newUser.setEmail("");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage,actualMessage);
	}
	
	////TEST FOR EMAIL WITH PATTERN	
	@Test 
	void testCreateUserWithEmailPattern() {
			
		UserService userService = new UserService();
			
		UserEntity newUser = new UserEntity();
		newUser.setName("Uthra");
		newUser.setEmail("uthragmailcom");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Email doesn't match the pattern";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	////TEST FOR EMAIL WITH ALREADY EXISTS	
	
	@Test 
	void testCreateUserWithEmailExists() {
			
		UserService userService = new UserService();
			
		UserEntity newUser = new UserEntity();
		newUser.setName("Uthra");
		newUser.setEmail("uthra12@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
			
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "This user is already exist";
		String actualMessage = exception.getMessage();
			
		assertEquals(exceptedMessage,actualMessage);
	}
	
	
	/// TEST FOR PASSWORD WITH NULL
	
	@Test
	void testCreateUserWithPasswordNull() {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword(null);
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	//// TEST FOR PASSWORD WITH EMPTY STRING

	@Test
	void testCreateUserWithPasswordEmpty() {
		
		UserService userService = new UserService();
	
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	////TEST FOR PASSWORD WITH PASSWORD LENGTH 

	@Test
	void testCreateUserWithPasswordLength() {
		
		UserService userService = new UserService();
	
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("thra!29");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password must contain atleast 8 characters";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	
	////TEST FOR PASSWORD WITH PATTERN 

	@Test
	public void testCreateUserWithPasswordPattern() {
		
		UserService userService = new UserService();
	
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthrakannan");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	//// TEST FOR NAME WITH NULL

	@Test
	void testCreateUserWithNameNull() {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();

		newUser.setName(null);
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	//// TEST FOR NAME WITH EMPTY STRING

	@Test
	void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();

		newUser.setName("");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	////TEST FOR NAME WITH PATTERN

	@Test
	void testCreateUserWithNamePattern() {
		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();

		newUser.setName("1245");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(7908946112l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	//// TEST FOR PHONE NUMBER WITH 0
	
	@Test
	void testCreateUserWithPhoneNumber () {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(0l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
		
	}
	
	//// TEST FOR PHONE NUMBER WITH LENGHT
	
	@Test
	void testCreateUserWithPhoneNumberLength () {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(9781246678324l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
		
	}
	
	//// TEST FOR PHONE NUMBER WITH PATTERN
	
	@Test
	public void testCreateUserWithInvalidPhoneNumber () {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
		newUser.setPhoneNumber(2348588980l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
		
	}
	
	////TEST FOR PHONE NUMBER WITH ALREADY EXISTS	
	
	@Test 
	public void testCreateUserWithPhoneNumberExists() {
			
		UserService userService = new UserService();
			
		 UserEntity newUser = new UserEntity();
	        newUser.setName("Uthra");
	        String randomString = generateRandomString(8);
	        newUser.setEmail("cbuhs435@gmail.com");
	        newUser.setPhoneNumber(9340697100l); // Same phone number as existing user
	        newUser.setPassword("Uthra@12");
	        newUser.setRole("Buyer");
	        newUser.setAge(18);

	        Exception exception = assertThrows(ValidationException.class, () -> {
	            userService.create(newUser);
	        });

	        String expectedMessage = "This user is already exist";
	        String actualMessage = exception.getMessage();

	        assertEquals(expectedMessage, actualMessage);
	}
	
	
	////TEST FOR AGE
	
	@Test
	void testCreateUserWithInvalidAge () {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("thamim@gmail.com");
		newUser.setPhoneNumber(9321674598l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(5);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Invalid age";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
		 
	}
	
	//// TEST FOR GET ALL USERS
	
	@Test
	void getAllUsers() throws ServiceException {
		
		UserService userService = new UserService();
		assertDoesNotThrow (() -> {
			Set<UserEntity> userList = userService.findAll();
			System.out.println(userList);
		});
		
	}
	
	//// TEST FOR GET USER BY ID
	
	@Test
	void getById() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		
		assertDoesNotThrow (() -> {
			UserEntity userList = userService.findById(2);
			System.out.println(userList);
		});
		
	}
	
	//// TEST FOR UPDATE USER
	
	@Test
	void testUpdateUser() {

		UserService userService = new UserService();
		
		UserEntity updateUser = new UserEntity();
		
		updateUser.setName("UthraKannan");
		updateUser.setPassword("Ramya@12");
		updateUser.setAge(18);
		
		assertDoesNotThrow(() -> {
			userService.update(2, updateUser);
		});
		
	}
	
	//// TEST FOR DELETE USER
	
	@Test
	void deleteUser() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		assertDoesNotThrow ( () -> {
			userService.delete(4);
		});
		
	}
	
}
