package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.Logger;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.TypeValidator;
import in.fssa.tharasworld.validator.UserValidator;

public class TypeService {

	/**
	 * Retrieves a set of all available product types.
	 *
	 * This method retrieves a set of all available product types by querying the
	 * database using a {@link TypeDAO}. It returns the result as a set of
	 * {@link TypeEntity} objects, representing product types. If any
	 * service-related issue occurs during the operation, it throws a
	 * {@link ServiceException}.
	 *
	 * @return A set of all available product types.
	 * @throws ServiceException If a service-related issue occurs during retrieval.
	 */

	public Set<TypeEntity> findAll() throws ServiceException {

		Set<TypeEntity> typeList = null;

		try {
			TypeDAO typeDAO = new TypeDAO();

			typeList = typeDAO.findAll();

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return typeList;

	}

	/**
	 * Creates a new product type.
	 *
	 * This method creates a new product type by validating the input
	 * {@link TypeEntity} and then using a {@link TypeDAO} to insert it into the
	 * database. It takes a {@link TypeEntity} representing the new product type as
	 * input. If the input validation fails, it throws a
	 * {@link ValidationException}. If any service-related issue occurs during the
	 * creation process, it throws a {@link ServiceException}.
	 *
	 * @param newType The {@link TypeEntity} representing the new product type.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             creation.
	 * @throws ValidationException If the input validation fails.
	 */

	public void create(TypeEntity newType) throws ServiceException, ValidationException {

		try {
			TypeValidator.validate(newType);

			TypeDAO typeDAO = new TypeDAO();
			typeDAO.create(newType);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Updates an existing product type.
	 *
	 * This method updates an existing product type in the database with the
	 * provided changes. It first validates the product type ID and the updated
	 * product type using {@link TypeValidator}. Then, it uses a {@link TypeDAO} to
	 * perform the database update operation. If the input validation fails, it
	 * throws a {@link ValidationException}. If any service-related issue occurs
	 * during the update process, it throws a {@link ServiceException}.
	 *
	 * @param id          The ID of the product type to update.
	 * @param updatedType The {@link TypeEntity} containing the updated information.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             update.
	 * @throws ValidationException If the input validation fails.
	 */

	public void update(int id, TypeEntity updatedType) throws ServiceException, ValidationException {

		try {

			TypeValidator.validateTypeId(id);
			TypeValidator.validate(updatedType);

			TypeDAO typeDAO = new TypeDAO();
			typeDAO.update(id, updatedType);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes a product type by its ID.
	 *
	 * This method deletes a product type from the database by its ID. It first
	 * validates the product type ID using {@link TypeValidator}. Then, it uses a
	 * {@link TypeDAO} to perform the database deletion operation. If the input
	 * validation fails, it throws a {@link ValidationException}. If any
	 * service-related issue occurs during the deletion process, it throws a
	 * {@link ServiceException}.
	 *
	 * @param id The ID of the product type to delete.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             deletion.
	 * @throws ValidationException If the input validation fails.
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {

			TypeValidator.validateTypeId(id);

			TypeDAO typeDAO = new TypeDAO();
			typeDAO.delete(id);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves all product types associated with a category by its ID.
	 *
	 * This method retrieves all product types that are associated with a specific
	 * category by its ID from the database. It first validates the category ID
	 * using {@link TypeValidator}. Then, it uses a {@link TypeDAO} to perform the
	 * database query. If the input validation fails, it throws a
	 * {@link ValidationException}. If any service-related issue occurs during the
	 * retrieval, it throws a {@link ServiceException}.
	 *
	 * @param id The ID of the category for which to retrieve associated product
	 *           types.
	 * @return A set of {@link TypeEntity} objects representing the product types
	 *         associated with the category.
	 * @throws ServiceException     If a service-related issue occurs during the
	 *                              retrieval.
	 * @throws ValidationException  If the input validation fails.
	 * @throws PersistenceException If a database-related issue occurs during the
	 *                              retrieval.
	 */

	public static Set<TypeEntity> findAllTypesByCategoryId(int id)
			throws ServiceException, ValidationException, PersistenceException {

		TypeValidator.validateCategoryId(id);

		Set<TypeEntity> typeList = null;

		try {
			TypeDAO typeDAO = new TypeDAO();

			typeList = typeDAO.findAllTypeByCategoryId(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return typeList;

	}

	/**
	 * Retrieves a product type by its ID.
	 *
	 * This method retrieves a specific product type from the database by its ID. It
	 * first validates the product type ID using {@link TypeValidator}. Then, it
	 * uses a {@link TypeDAO} to perform the database query. If the input validation
	 * fails, it throws a {@link ValidationException}. If any service-related issue
	 * occurs during the retrieval, it throws a {@link ServiceException}.
	 *
	 * @param id The ID of the product type to retrieve.
	 * @return A {@link TypeEntity} object representing the retrieved product type.
	 * @throws ServiceException     If a service-related issue occurs during the
	 *                              retrieval.
	 * @throws ValidationException  If the input validation fails.
	 * @throws PersistenceException If a database-related issue occurs during the
	 *                              retrieval.
	 */

	public static TypeEntity findByTypeId(int id) throws ValidationException, PersistenceException, ServiceException {

		TypeValidator.validateTypeId(id);

		TypeEntity type = null;

		try {
			TypeDAO typeDAO = new TypeDAO();

			type = typeDAO.checkTypeExistWithId(id);

			
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return type;

	}

}
