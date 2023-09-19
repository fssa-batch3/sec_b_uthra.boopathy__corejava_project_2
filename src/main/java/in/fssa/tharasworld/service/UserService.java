package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.UserDAO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.UserValidator;

public class UserService {

	/**
	 * Retrieves a set of all user entities from the database.
	 *
	 * This method retrieves a set of all user entities from the database using a
	 * {@link UserDAO} instance. It does not require any input parameters. If any
	 * service-related issue occurs during the retrieval, it throws a
	 * {@link ServiceException}.
	 *
	 * @return A set of {@link UserEntity} objects representing all users in the
	 *         database.
	 * @throws ServiceException If a service-related issue occurs during the
	 *                          retrieval.
	 */

	public static Set<UserEntity> findAll() throws ServiceException {

		Set<UserEntity> userList = null;

		try {
			UserDAO userDAO = new UserDAO();

			userList = userDAO.findAll();

			for (UserEntity user : userList) {
				System.out.println(user);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return userList;

	}

	/**
	 * Creates a new user entity in the database.
	 *
	 * This method creates a new user entity in the database using a {@link UserDAO}
	 * instance. It takes a {@link UserEntity} object as input and performs the
	 * following operations:
	 *
	 * 1. Validates the input user entity using {@link UserValidator}. 2. Checks if
	 * a user with the same email or phone number already exists in the database. 3.
	 * If validation and uniqueness checks pass, it creates the new user entity in
	 * the database.
	 *
	 * @param newUser The {@link UserEntity} object representing the new user to be
	 *                created.
	 * @throws ValidationException If the input user entity is not valid or
	 *                             uniqueness checks fail.
	 * @throws ServiceException    If a service-related issue occurs during the user
	 *                             creation process.
	 */

	public void create(UserEntity newUser) throws ValidationException, ServiceException {

		try {

			UserValidator.validate(newUser);
			UserValidator.CheckUserExists(newUser.getEmail());
			UserValidator.CheckUserExistsWithPhoneNumber(newUser.getPhoneNumber());

			UserDAO userDAO = new UserDAO();
			userDAO.create(newUser);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates an existing user entity in the database.
	 *
	 * This method updates an existing user entity in the database using a
	 * {@link UserDAO} instance. It takes an integer ID and an updated
	 * {@link UserEntity} object as input and performs the following operations:
	 *
	 * 1. Checks if a user with the specified ID exists in the database. 2.
	 * Validates the input user entity fields, including name, password, and age. 3.
	 * Updates the user entity in the database with the new data.
	 *
	 * @param id          The ID of the user to be updated.
	 * @param updatedUser The updated {@link UserEntity} object containing the new
	 *                    user data.
	 * @throws ValidationException If the input user data is not valid.
	 * @throws ServiceException    If a service-related issue occurs during the user
	 *                             update process.
	 */

	public void update(int id, UserEntity updatedUser) throws ServiceException, ValidationException {

		try {
			UserValidator.CheckUserExistsWithId(id);
			UserValidator.validateName(updatedUser.getName());
			UserValidator.validatePassword(updatedUser.getPassword());
			UserValidator.validateAge(updatedUser.getAge());

			UserDAO userDAO = new UserDAO();
			userDAO.update(id, updatedUser);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes a user entity from the database.
	 *
	 * This method deletes a user entity from the database using a {@link UserDAO}
	 * instance. It takes an integer ID as input and performs the following
	 * operations:
	 *
	 * 1. Checks if a user with the specified ID exists in the database. 2. Deletes
	 * the user entity from the database.
	 *
	 * @param id The ID of the user to be deleted.
	 * @throws ValidationException If the user ID is not valid or if the user does
	 *                             not exist.
	 * @throws ServiceException    If a service-related issue occurs during the user
	 *                             deletion process.
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {
			UserValidator.CheckUserExistsWithId(id);
			UserValidator.CheckUserExistsWithId(id);

			UserDAO userDAO = new UserDAO();
			userDAO.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a user entity by its ID from the database.
	 *
	 * This method retrieves a user entity by its integer ID from the database using
	 * a {@link UserDAO} instance. It takes an integer ID as input and performs the
	 * following operations:
	 *
	 * 1. Checks if a user with the specified ID exists in the database. 2.
	 * Retrieves the user entity from the database.
	 *
	 * @param id The ID of the user to be retrieved.
	 * @return The user entity with the specified ID.
	 * @throws ValidationException If the user ID is not valid or if the user does
	 *                             not exist.
	 * @throws ServiceException    If a service-related issue occurs during the user
	 *                             retrieval process.
	 */

	public static UserEntity findById(int id) throws ServiceException, ValidationException {

		UserEntity user = null;

		try {

			UserValidator.CheckUserExistsWithId(id);
			UserDAO userDAO = new UserDAO();

			user = userDAO.findById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return user;

	}

	/**
	 * Checks if a user with the specified phone number exists for login purposes.
	 *
	 * This method checks if a user with the specified phone number exists in the
	 * database and is active. It takes a long phone number as input and performs
	 * the following operations:
	 *
	 * 1. Checks if a user with the specified phone number exists and is active in
	 * the database. 2. Retrieves the user entity for login purposes.
	 *
	 * @param phoneNumber The phone number of the user to be checked.
	 * @return The user entity for login if the user exists and is active.
	 * @throws ValidationException If the phone number is not valid, or if the user
	 *                             does not exist or is not active.
	 * @throws ServiceException    If a service-related issue occurs during the user
	 *                             check process.
	 */

	public UserEntity checkUserExistsWithPhoneNumber(long phoneNumber) throws ServiceException, ValidationException {

		UserEntity user = null;

		try {

			UserValidator.CheckUserExistsWithPhoneNumberForUpdate(phoneNumber);
			;
			UserDAO userDAO = new UserDAO();

			user = userDAO.checkUserExistsWithPhoneNumberForLogin(phoneNumber);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return user;

	}

}
