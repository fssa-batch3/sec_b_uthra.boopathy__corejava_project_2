package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

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
	 @Order(1)
	    void testCreateUserWithValidInput() {
	        UserService userService = new UserService();

	        UserEntity newUser = new UserEntity();

	        newUser.setName("Mathi");

	        String randomString = generateRandomString(8);
	        newUser.setEmail( randomString + "@gmail.com");

	        long randomPhoneNumber = generateRandomPhoneNumber(10);
	        newUser.setPhoneNumber(randomPhoneNumber);

	        newUser.setPassword("Mathi$123");
	        newUser.setRole("Buyer");
	        newUser.setAge(20);

	        assertDoesNotThrow(() -> {
	            userService.create(newUser);
	        });
	    }
	 
	//// TEST FOR INVALID INPUT
	  
	@Test
	@Order(2)
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
	@Order(3)
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
	@Order(4)
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
	@Order(5)
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
	@Order(6)
	void testCreateUserWithEmailExists() {
			
		UserService userService = new UserService();
			
		UserEntity newUser = new UserEntity();
		newUser.setName("Uthra");
		newUser.setEmail("uthra@gmail.com");
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
	@Order(7)
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
	@Order(8)
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
	@Order(9)
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
	@Order(10)
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
	@Order(11)
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
	@Order(12)
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
	@Order(13)
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
	@Order(14)
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
	@Order(15)
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
	@Order(16)
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
	@Order(17)
	public void testCreateUserWithPhoneNumberExists() {
			
		UserService userService = new UserService();
			
		 UserEntity newUser = new UserEntity();
	        newUser.setName("Uthra");
	        String randomString = generateRandomString(8);
	        newUser.setEmail(randomString + "@gmail.com");
	        newUser.setPhoneNumber(8499961849l); // Same phone number as existing user
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
	@Order(18)
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
	@Order(19)
	void getAllUsers() throws ServiceException {
		
		UserService userService = new UserService();
		assertDoesNotThrow (() -> {
			Set<UserEntity> userList = userService.findAll();
			System.out.println(userList);
		});
		
	}
	
	//// TEST FOR GET USER BY ID
	
	@Test
	@Order(20)
	void getById() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		
		assertDoesNotThrow (() -> {
			UserEntity userList = userService.findById(2);
		});
		
	}
	
	@Test
	@Order(21)
	void getByUserIdAsZero() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			UserEntity userList = userService.findById(0);
		});
		String expectedMessage = "Invalid user id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);		
		
	}
	
	@Test
	@Order(22)
	void getByUserIdAsInvalidId() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			UserEntity userList = userService.findById(100);
			System.out.println(userList);
		});
		String expectedMessage = "User does not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);		
		
	}
	
	//// TEST FOR UPDATE USER
	
	@Test
	@Order(23)
	void testUpdateUser() {

		UserService userService = new UserService();
		
		UserEntity updateUser = new UserEntity();
		
		updateUser.setName("Ramya");
		updateUser.setPassword("Ramya#08");
		updateUser.setAge(18);
		
		assertDoesNotThrow(() -> {
			userService.update(2, updateUser);
		});
		
	}
	
	@Test
	@Order(24)
	void testUpdateUserWithZero() {

		UserService userService = new UserService();
		
		UserEntity updateUser = new UserEntity();
		
		updateUser.setName("Ramya");
		updateUser.setPassword("Ramya#08");
		updateUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(0, updateUser);
		});
		String expectedMessage = "Invalid user id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);

	}
	
	@Test
	@Order(25)
	void testUpdateUserWithInvalidId() {

		UserService userService = new UserService();
		
		UserEntity updateUser = new UserEntity();
		
		updateUser.setName("Ramya");
		updateUser.setPassword("Ramya#08");
		updateUser.setAge(18);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(100, updateUser);
		});
		String expectedMessage = "User does not exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);

	}
	
	//// TEST FOR DELETE USER
	
	@Test
	@Order(26)
	void deleteUser() throws ServiceException, ValidationException {
		
		UserService userService = new UserService();
		assertDoesNotThrow ( () -> {
			userService.delete(4);
		});
		
	}
	

	@Test
	public void TestuserLoginWithValidInput() {
		UserService userService = new UserService();
		
		
		assertDoesNotThrow(() -> {
			UserEntity user  = userService.checkUserExistsWithPhoneNumber(8499961849l);
			if(!BCrypt.checkpw("Thamim@12", user.getPassword())) {
				throw new ValidationException("Incorrect Password");
			}
			
					});
	}
	
}
