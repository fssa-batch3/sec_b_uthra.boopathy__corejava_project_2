package in.fssa.tharasworld;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.service.UserService;
import in.fssa.tharasworld.exception.ValidationException;

public class TestCasesForUser {
	
	
	///  TEST FOR VALID INPUT TO CREATE USER
	
	@Test
	public void testCreateUserWithValidInput() {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		newUser.setName("Thamim");
		newUser.setEmail("thamim@gmail.com");
		newUser.setPhoneNumber(7418822895l);
		newUser.setPassword("Thamim@12");
		newUser.setRole("Buyer");
		newUser.setAge(18);
	
		 
		assertDoesNotThrow(()->{ 
			userService.create(newUser);
		});
	
	}
	 
	//// TEST FOR INVALID INPUT
	  
	@Test
	public void testCreateUserWithInvalidInput() {
		
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String exceptedMessage = "Invalid user input";
		String actualMessage = exception.getMessage();
		
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	//// TEST FOR EMAIL WITH NULL
	
	@Test 
	public void testCreateUserWithEmailNull() {
		
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
		
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	//// TEST FOR EMAIL WITH EMPTY STRING
	
	@Test 
	public void testCreateUserWithEmailEmpty() {
		
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
		
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	////TEST FOR EMAIL WITH PATTERN	
	@Test 
	public void testCreateUserWithEmailPattern() {
			
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
			
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	////TEST FOR EMAIL WITH ALREADY EXISTS	
	
	@Test 
	public void testCreateUserWithEmailExists() {
			
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
			
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	/// TEST FOR PASSWORD WITH NULL
	
	@Test
	public void testCreateUserWithPasswordNull() {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//// TEST FOR PASSWORD WITH EMPTY STRING

	@Test
	public void testCreateUserWithPasswordEmpty() {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	////TEST FOR PASSWORD WITH PASSWORD LENGTH 

	@Test
	public void testCreateUserWithPasswordLength() {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//// TEST FOR NAME WITH NULL

	@Test
	public void testCreateUserWithNameNull() {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//// TEST FOR NAME WITH EMPTY STRING

	@Test
	public void testCreateUserWithNameEmpty() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	////TEST FOR NAME WITH PATTERN

	@Test
	public void testCreateUserWithNamePattern() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//// TEST FOR PHONE NUMBER WITH 0
	
	@Test
	public void testCreateUserWithPhoneNumber () {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	//// TEST FOR PHONE NUMBER WITH LENGHT
	
	@Test
	public void testCreateUserWithPhoneNumberLength () {
		
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

		assertTrue(expectedMessage.equals(actualMessage));
		
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

		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	////TEST FOR PHONE NUMBER WITH ALREADY EXISTS	
	
	@Test 
	public void testCreateUserWithPhoneNumberExists() {
			
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
			
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	////TEST FOR AGE
	
	@Test
	public void testCreateUserWithInvalidAge () {
		
		UserService userService = new UserService();
		
		UserEntity newUser = new UserEntity();
		
		newUser.setName("Uthra");
		newUser.setEmail("thamim@gmail.com");
		newUser.setPhoneNumber(9321674598l);
		newUser.setPassword("Uthra@12");
		newUser.setRole("Buyer");
		newUser.setAge(-54);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Invalid age";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	//// TEST FOR GET ALL USERS
	
	@Test
	public void getAllUsers() {
		UserService userService = new UserService();
		Set<UserEntity> userList = userService.findAll();
		System.out.println(userList);
	}
	
	//// TEST FOR GET USER BY ID
	
	@Test
	public void getById() {
		UserService userService = new UserService();
		UserEntity userList = userService.findById(2);
		System.out.println(userList);
	}
	
	//// TEST FOR UPDATE USER
	
	@Test
	public void testUpdateUser() {

		UserService userService = new UserService();
		
		UserEntity updateUser = new UserEntity();
		
		updateUser.setName("UthraKannan");
		updateUser.setEmail("uthra@gmail.com");
		updateUser.setPhoneNumber(7908946112l);
		updateUser.setPassword("Ramya@12");
		
		assertDoesNotThrow(() -> {
			userService.update(1, updateUser);
		});
		
	}
	
	//// TEST FOR DELETE USER
	
	@Test
	public void deleteUser() throws Exception {
		
		UserService userService = new UserService();
		
		userService.delete(1);
		
	}
	
}
