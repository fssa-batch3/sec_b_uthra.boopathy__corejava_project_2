package in.fssa.tharasworld.validator;

import java.util.regex.Pattern;

import in.fssa.tharasworld.dao.AddressDAO;
import in.fssa.tharasworld.dao.UserDAO;
import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.StringUtil;

public class AddressValidator {

	private static final String NAME_PATTERN = "^[a-zA-Z ]+$";

	/**
	 * Validates an AddressEntity to ensure its properties are valid and conform to
	 * specific criteria.
	 *
	 * @param address The AddressEntity to be validated.
	 * @throws ValidationException  If any validation criteria are not met.
	 * @throws PersistenceException If an error related to data persistence occurs.
	 */

	public static void validate(AddressEntity address) throws ValidationException, PersistenceException {

		if (address == null) {
			throw new ValidationException("Invalid address input");
		}

		validateName(address.getName());

		validateUserId(address.getUserId());

		validateAddress(address);

		StringUtil.rejectIfInvalidString(address.getState(), "State");

		if (address.getPincode() <= 0) {
			throw new ValidationException("Pincode cannot be zero or in negative");
		}

		if (address.getPincode() < 600000 || address.getPincode() > 700000) {
			throw new ValidationException("Invalid pincode");
		}

	}

	/**
	 * Validates a name string to ensure it is not null, not empty, and matches a
	 * specified pattern.
	 *
	 * @param name The name string to be validated.
	 * @throws ValidationException If the name is null, empty, or doesn't match the
	 *                             specified pattern.
	 */

	public static void validateName(String name) throws ValidationException {

		StringUtil.rejectIfInvalidString(name, "Name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}

	}

	/**
	 * Validates an AddressEntity object to ensure its properties meet certain
	 * criteria.
	 *
	 * @param address The AddressEntity object to be validated.
	 * @throws ValidationException  If any of the validation criteria are not met.
	 * @throws PersistenceException If there is an issue with checking the address
	 *                              existence in the database.
	 */

	public static void validateAddress(AddressEntity address) throws ValidationException, PersistenceException {

		StringUtil.rejectIfInvalidString(address.getAddress(), "Address");

		AddressDAO addressDAO = new AddressDAO();
		addressDAO.checkAddressExist(address.getAddress(), address.getUserId());

		StringUtil.rejectIfInvalidString(address.getState(), "State");

		if (address.getPincode() <= 0) {
			throw new ValidationException("Pincode cannot be zero or in negative");
		}

		if (address.getPincode() < 600000 || address.getPincode() > 700000) {
			throw new ValidationException("Invalid pincode");
		}

	}

	/**
	 * Validates a user's ID to ensure it meets certain criteria.
	 *
	 * @param id The user's ID to be validated.
	 * @throws ValidationException  If the validation criteria are not met.
	 * @throws PersistenceException If there is an issue with checking the user's
	 *                              existence in the database.
	 */

	public static void validateUserId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("User Id cannot be zero or in negative");
		}

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserExistsWithId(id);

	}

	/**
	 * Validates an address ID to ensure it meets certain criteria.
	 *
	 * @param id The address ID to be validated.
	 * @throws ValidationException  If the validation criteria are not met.
	 * @throws PersistenceException If there is an issue with checking the address
	 *                              existence in the database.
	 */

	public static void validateId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Address id cannot be zero or in negative");
		}

		AddressDAO addressDAO = new AddressDAO();
		addressDAO.checkAddressExistWithAddId(id);

	}
	
	public static void validateAddressId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Address id cannot be zero or in negative");
			
		}
		AddressDAO addressDAO = new AddressDAO();
		addressDAO.checkAddressExistWithAddressId(id);
	}

}
