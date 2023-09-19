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

	private static final String NAME_PATTERN = "^[a-zA-Z ]+$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

	/**
	 * Validates a UserEntity object to ensure its properties meet specific
	 * criteria.
	 *
	 * This method performs a series of validation checks on a UserEntity object to
	 * ensure that it is valid and meets the required criteria. It checks for null
	 * input and validates various properties of the user, including name, email,
	 * password, phone number, and age. Additionally, it may perform additional
	 * validation checks on each property by calling specific validation methods.
	 *
	 * @param user The UserEntity object to be validated.
	 *
	 * @throws ValidationException If the input user is null or if any of its
	 *                             properties do not meet the validation criteria.
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
	 * Validates a phone number to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a phone number to ensure that it is
	 * a 10-digit long positive number within a specified range. It is typically
	 * used to validate phone numbers before they are associated with user profiles
	 * to ensure data integrity.
	 *
	 * @param phoneNumber The phone number to be validated.
	 *
	 * @throws ValidationException If the phone number is not a 10-digit long
	 *                             positive number within the specified range.
	 */

	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {

		String phno = String.valueOf(phoneNumber);

		if (phno.length() != 10) {
			throw new ValidationException("Invalid phone number");
		}

		if (phoneNumber <= 6000000000l || phoneNumber >= 9999999999l) {
			throw new ValidationException("Invalid phone number");
		}

	}

	/**
	 * Validates a name to ensure it is a valid string and matches a specified
	 * pattern.
	 *
	 * This method performs validation checks on a name to ensure that it is a valid
	 * string and that it matches a specified pattern. It is typically used to
	 * validate names before they are associated with user profiles, products, or
	 * other entities to ensure data integrity.
	 *
	 * @param name The name to be validated.
	 *
	 * @throws ValidationException If the name is invalid or does not match the
	 *                             specified pattern.
	 */

	public static void validateName(String name) throws ValidationException {

		StringUtil.rejectIfInvalidString(name, "Name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}

	}

	/**
	 * Validates an email address to ensure it is a valid string and matches a
	 * specified pattern.
	 *
	 * This method performs validation checks on an email address to ensure that it
	 * is a valid string and that it matches a specified pattern typically used for
	 * email addresses. It is typically used to validate email addresses before they
	 * are associated with user profiles or for other purposes to ensure data
	 * integrity.
	 *
	 * @param email The email address to be validated.
	 *
	 * @throws ValidationException If the email address is invalid or does not match
	 *                             the specified pattern.
	 */

	public static void validateEmail(String email) throws ValidationException {

		StringUtil.rejectIfInvalidString(email, "Email");

		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email doesn't match the pattern");
		}

	}

	/**
	 * Checks if a user with the specified email address exists in a data source.
	 *
	 * This method queries a data source, typically a database, to determine if a
	 * user with the given email address already exists. It is used to check for the
	 * existence of users before performing actions such as registration to prevent
	 * duplicate entries.
	 *
	 * @param email The email address to check for existence.
	 *
	 * @throws ValidationException  If the email address is invalid or if there are
	 *                              issues with the validation.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during the check, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void CheckUserExists(String email) throws ValidationException, PersistenceException {

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExists(email);

	}

	/**
	 * Checks if a user with the specified phone number exists in a data source.
	 *
	 * This method queries a data source, typically a database, to determine if a
	 * user with the given phone number already exists. It is used to check for the
	 * existence of users before performing actions such as registration to prevent
	 * duplicate entries.
	 *
	 * @param phoneNumber The phone number to check for existence.
	 *
	 * @throws ValidationException  If the phone number is invalid or if there are
	 *                              issues with the validation.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during the check, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void CheckUserExistsWithPhoneNumber(long phoneNumber)
			throws ValidationException, PersistenceException {

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExistsWithPhoneNumber(phoneNumber);

	}

	/**
	 * Checks if a user with the specified user ID exists in a data source.
	 *
	 * This method queries a data source, typically a database, to determine if a
	 * user with the given user ID already exists. It is used to check for the
	 * existence of users before performing actions that require a valid user ID,
	 * such as retrieving user information.
	 *
	 * @param id The user ID to check for existence.
	 *
	 * @throws ValidationException  If the user ID is zero or negative.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during the check, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void CheckUserExistsWithId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Invalid user id");
		}

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExistsWithId(id);

	}

	/**
	 * Checks if a user with the specified email address exists in a data source for
	 * update purposes.
	 *
	 * This method queries a data source, typically a database, to determine if a
	 * user with the given email address already exists. It is used for update
	 * operations to ensure that the email address being updated to does not already
	 * exist in the database.
	 *
	 * @param email The email address to check for existence during an update.
	 *
	 * @throws ValidationException  If the email address is invalid or if there are
	 *                              issues with the validation.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during the check, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void CheckUserExistsForUpdate(String email) throws ValidationException, PersistenceException {

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExistsForUpdate(email);

	}

	/**
	 * Checks if a user with the specified phone number exists in a data source for
	 * update purposes.
	 *
	 * This method queries a data source, typically a database, to determine if a
	 * user with the given phone number already exists. It is used for update
	 * operations to ensure that the phone number being updated to does not already
	 * exist in the database.
	 *
	 * @param phoneNumber The phone number to check for existence during an update.
	 *
	 * @throws ValidationException  If the phone number is invalid or if there are
	 *                              issues with the validation.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during the check, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void CheckUserExistsWithPhoneNumberForUpdate(long phoneNumber)
			throws ValidationException, PersistenceException {

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExistsWithPhoneNumberForUpdate(phoneNumber);

	}

	/**
	 * Validates a password to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a password to ensure that it is a
	 * valid string, contains at least 8 characters, and matches a specified pattern
	 * typically used for passwords. It is typically used to validate passwords
	 * before they are associated with user profiles to ensure security and data
	 * integrity.
	 *
	 * @param password The password to be validated.
	 *
	 * @throws ValidationException If the password is invalid, contains less than 8
	 *                             characters, or does not match the specified
	 *                             password pattern.
	 */

	public static void validatePassword(String password) throws ValidationException {

		StringUtil.rejectIfInvalidString(password, "Password");

		if (password.length() < 8) {
			throw new ValidationException("Password must contain atleast 8 characters");
		}

		if (!Pattern.matches(PASSWORD_PATTERN, password)) {
			throw new ValidationException("Password doesn't match the pattern");
		}

	}

	/**
	 * Validates an age to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on an age to ensure that it is within
	 * a specified range. Typically, it checks that the age is greater than 10 and
	 * less than 100, ensuring that the age is a reasonable value. It is used to
	 * validate age information associated with user profiles or other entities to
	 * ensure data integrity.
	 *
	 * @param age The age to be validated.
	 *
	 * @throws ValidationException If the age is outside the specified valid range
	 *                             (less than or equal to 10 or greater than or
	 *                             equal to 100).
	 */

	public static void validateAge(int age) throws ValidationException {

		if (age <= 10 || age >= 100) {
			throw new ValidationException("Invalid age");
		}

	}

}
