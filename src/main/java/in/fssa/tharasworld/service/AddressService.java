package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.AddressDAO;
import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.Logger;
import in.fssa.tharasworld.validator.AddressValidator;
import in.fssa.tharasworld.validator.CategoryValidator;

public class AddressService {

	/**
	 * Creates a new address in the system.
	 *
	 * This method validates the provided AddressEntity using the AddressValidator
	 * class. If the address is valid, it is passed to the AddressDAO for creation
	 * in the database. If the validation fails or a database error occurs, a
	 * ServiceException is thrown.
	 *
	 * @param newAddress An instance of the AddressEntity class representing the new
	 *                   address to be created.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during creation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided address does not pass validation
	 *                             checks.
	 */

	public void create(AddressEntity newAddress) throws ServiceException, ValidationException {

		try {
			AddressValidator.validate(newAddress);

			AddressDAO addressDAO = new AddressDAO();
			addressDAO.create(newAddress);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates an existing address in the system.
	 *
	 * This method validates the provided `id` using
	 * `AddressValidator.validateId(id)` to ensure it is valid. It also validates
	 * the updated address using `AddressValidator.validateAddress(updatedAddress)`.
	 * If both validations pass, the method delegates the update operation to the
	 * AddressDAO for the specified address. If validation fails or a database error
	 * occurs, a ServiceException is thrown.
	 *
	 * @param id             The ID of the address to be updated.
	 * @param updatedAddress An instance of the AddressEntity class representing the
	 *                       updated address.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during update, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided ID is not valid or the updated
	 *                             address does not pass validation checks.
	 */

	public static void update(int id, AddressEntity updatedAddress) throws ServiceException, ValidationException {

		try {
			AddressValidator.validateId(id);

			AddressValidator.validateAddress(updatedAddress);

			AddressDAO addressDAO = new AddressDAO();
			addressDAO.update(id, updatedAddress);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Sets an address as the default address for a user.
	 *
	 * This method first validates the provided `userId` and `addressId` using
	 * `AddressValidator.validateUserId(userId)` and
	 * `AddressValidator.validateId(addressId)`, respectively, to ensure they are
	 * valid. It then delegates the task of setting all other addresses of the user
	 * as non-default to the AddressDAO using
	 * `addressDAO.setAsDefaultAddressIntoFalse(userId)` and sets the specified
	 * address as the default address using
	 * `addressDAO.setAsDefaultAddress(addressId)`. If validation fails or a
	 * database error occurs, a ServiceException is thrown.
	 *
	 * @param userId    The ID of the user for whom the address will be set as
	 *                  default.
	 * @param addressId The ID of the address to be set as the default address.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during the operation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided user ID or address ID is not
	 *                             valid.
	 */

	public static void setAsDefaultAddress(int userId, int addressId) throws ServiceException, ValidationException {

		try {
			AddressValidator.validateUserId(userId);

			AddressValidator.validateId(addressId);

			AddressDAO addressDAO = new AddressDAO();
			addressDAO.setAsDefaultAddressIntoFalse(userId);

			addressDAO.setAsDefaultAddress(addressId);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes an address by its ID.
	 *
	 * This method first validates the provided `id` using
	 * `AddressValidator.validateId(id)` to ensure it is a valid ID. It then
	 * delegates the task of deleting the address to the AddressDAO using
	 * `addressDAO.delete(id)`. If validation fails or a database error occurs, a
	 * ServiceException is thrown.
	 *
	 * @param id The ID of the address to be deleted.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during the operation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided address ID is not valid.
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {

			AddressValidator.validateId(id);

			AddressDAO addressDAO = new AddressDAO();
			addressDAO.delete(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Finds the default address for a user by their ID.
	 *
	 * This method first validates the provided `id` using
	 * `AddressValidator.validateUserId(id)` to ensure it is a valid user ID. It
	 * then delegates the task of finding the default address to the AddressDAO
	 * using `addressDAO.findByDefault(id)`. If validation fails or a database error
	 * occurs, a ServiceException is thrown.
	 *
	 * @param id The ID of the user for whom the default address is to be retrieved.
	 * @return The default address associated with the user, or null if no default
	 *         address is found.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during the operation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided user ID is not valid.
	 */

	public static AddressEntity findByDefault(int id) throws ServiceException, ValidationException {

		AddressEntity address = null;

		try {

			AddressValidator.validateUserId(id);

			AddressDAO addressDAO = new AddressDAO();
			address = addressDAO.findByDefault(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return address;

	}

	/**
	 * Finds an address by its unique identifier (ID).
	 *
	 * This method first validates the provided `id` using
	 * `AddressValidator.validateId(id)` to ensure it is a valid address ID. It then
	 * delegates the task of finding the address to the AddressDAO using
	 * `addressDAO.findById(id)`. If validation fails or a database error occurs, a
	 * ServiceException is thrown.
	 *
	 * @param id The unique identifier (ID) of the address to be retrieved.
	 * @return The address with the specified ID, or null if no address is found
	 *         with the given ID.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during the operation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided address ID is not valid.
	 */

	public static AddressEntity findByAddressId(int id) throws ServiceException, ValidationException {

		AddressEntity address = null;

		try {

			AddressValidator.validateAddressId(id);

			AddressDAO addressDAO = new AddressDAO();
			address = addressDAO.findById(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return address;

	}

	/**
	 * Finds a list of addresses associated with a user by their user identifier
	 * (ID).
	 *
	 * This method first validates the provided `id` using
	 * `AddressValidator.validateUserId(id)` to ensure it is a valid user ID. It
	 * then delegates the task of finding the addresses to the AddressDAO using
	 * `addressDAO.findByUserId(id)`. If validation fails or a database error
	 * occurs, a ServiceException is thrown.
	 *
	 * @param id The unique identifier (ID) of the user for whom addresses are to be
	 *           retrieved.
	 * @return A list of addresses associated with the specified user, or an empty
	 *         list if no addresses are found.
	 * @throws ServiceException    If there is a validation error, a database error
	 *                             occurs during the operation, or any other
	 *                             service-related issue.
	 * @throws ValidationException If the provided user ID is not valid.
	 */

	public static List<AddressEntity> findAddressesByUserId(int id) throws ServiceException, ValidationException {

		List<AddressEntity> addressList = null;

		try {

			AddressValidator.validateUserId(id);

			AddressDAO addressDAO = new AddressDAO();
			addressList = addressDAO.findByUserId(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return addressList;

	}

}
