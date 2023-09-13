package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.UserDAO;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.util.StringUtil;
import in.fssa.tharasworld.util.ConnectionUtil;

public class UserValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
	
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	
	public static void validate(UserEntity user) throws ValidationException {

		if (user == null) { 
			throw new ValidationException("Invalid user input");
		}  
			
			validateName(user.getName());
			validateEmail(user.getEmail());
			validatePassword(user.getPassword()); 
			validatePhoneNumber(user.getPhoneNumber());
			validateAge(user.getAge());
			
		} 
		
	/**
	 * 
	 * @param phoneNumber
	 * @throws ValidationException
	 */
	
		private static void validatePhoneNumber(long phoneNumber) throws ValidationException{
			
			String phno = String.valueOf(phoneNumber);
			
			if(phno.length()!=10) { 
				throw new ValidationException("Invalid phone number");
			}
			
			if(phoneNumber <= 6000000000l || phoneNumber >= 9999999999l) {
				throw new ValidationException("Invalid phone number");
			} 
	
		}
		
		/**
		 * 
		 * @param name
		 * @throws ValidationException
		 */
		
		public static void validateName(String name) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(name, "Name");
			
			if (!Pattern.matches(NAME_PATTERN, name)) {
				throw new ValidationException("Name doesn't match the pattern");
			}
		
		}
		
		/**
		 * 
		 * @param email
		 * @throws ValidationException
		 */
		
		public static void validateEmail(String email) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(email, "Email");
			
			if (!Pattern.matches(EMAIL_PATTERN, email)) {
				throw new ValidationException("Email doesn't match the pattern");
			}			
		
		}
		
		public static void CheckUserExists(String email) throws ValidationException, PersistenceException {
			
			UserDAO userDAO = new UserDAO();
			userDAO.checkUserExists(email);
			
		}
		
		public static void CheckUserExistsWithPhoneNumber(long phoneNumber) throws ValidationException, PersistenceException {
			
			UserDAO userDAO = new UserDAO();
			userDAO.checkUserExistsWithPhoneNumber(phoneNumber);
			
		}
		
		public static void CheckUserExistsWithId(int id) throws ValidationException, PersistenceException {
			
			if(id<=0) {
				throw new ValidationException("Invalid user id");
			}
			
			UserDAO userDAO = new UserDAO();
			userDAO.checkUserExistsWithId(id);
			
		}
		
		public static void CheckUserExistsForUpdate(String email) throws ValidationException, PersistenceException {
			
			UserDAO userDAO = new UserDAO();
			userDAO.checkUserExistsForUpdate(email);
			
		}
		
		public static void CheckUserExistsWithPhoneNumberForUpdate(long phoneNumber) throws ValidationException, PersistenceException {
			
			UserDAO userDAO = new UserDAO();
			userDAO.checkUserExistsWithPhoneNumberForUpdate(phoneNumber);
			
		}
		
		/**
		 * 
		 * @param password
		 * @throws ValidationException
		 */
		
		public static void validatePassword(String password) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(password, "Password");
			
			if(password.length()<8) {
				throw new ValidationException("Password must contain atleast 8 characters");
			}
			
			if (!Pattern.matches(PASSWORD_PATTERN, password)) {
				throw new ValidationException("Password doesn't match the pattern");
			}
		
		
		}
		
		/**
		 * 
		 * @param age
		 * @throws ValidationException
		 */
		
		public static void validateAge(int age) throws ValidationException{
			
			String ages = String.valueOf(age);
			
			if(age <= 10 || age >= 100) {
				throw new ValidationException("Invalid age");
			}
	
		}
	
}
