package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.Logger;
import in.fssa.tharasworld.validator.CategoryValidator;

public class CategoryService {

	/**
	 * Retrieves a set of all available categories from the database.
	 *
	 * This method delegates the task of retrieving categories to the CategoryDAO
	 * using `categoryDAO.findAll()`. It returns a set containing all available
	 * categories. If there are no categories, the returned set will be empty. If a
	 * database error occurs during the operation, a ServiceException is thrown.
	 *
	 * @return A set of all available categories, or an empty set if there are no
	 *         categories.
	 * @throws ServiceException If a database error occurs or any other
	 *                          service-related issue.
	 */

	public static Set<CategoryEntity> findAll() throws ServiceException {

		Set<CategoryEntity> CategoryList;
		try {
			CategoryDAO categoryDAO = new CategoryDAO();

			CategoryList = categoryDAO.findAll();

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return CategoryList;

	}

	/**
	 * Creates a new category in the database.
	 *
	 * This method delegates the task of creating a new category to the CategoryDAO
	 * using `categoryDAO.create(newCategory)`. It takes an instance of the
	 * CategoryEntity class representing the new category to be created as a
	 * parameter. The method first validates the new category using
	 * CategoryValidator, ensuring it meets any necessary criteria. If validation
	 * passes, the category is created in the database. If validation fails or a
	 * database error occurs, a ServiceException is thrown.
	 *
	 * @param newCategory An instance of the CategoryEntity class representing the
	 *                    new category to be created.
	 * @throws ServiceException    If validation fails, a database error occurs, or
	 *                             any other service-related issue arises.
	 * @throws ValidationException If the new category does not meet the required
	 *                             validation criteria.
	 */

	public void create(CategoryEntity newCategory) throws ServiceException, ValidationException {

		try {
			CategoryValidator.validate(newCategory);

			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.create(newCategory);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates an existing category in the database.
	 *
	 * This method delegates the task of updating a category to the CategoryDAO
	 * using `categoryDAO.update(id, updatedCategory)`. It takes two parameters: the
	 * ID of the category to update and an instance of the CategoryEntity class
	 * representing the updated category data. The method first validates the
	 * category ID and the updated category data using CategoryValidator. If
	 * validation passes, the category is updated in the database. If validation
	 * fails or a database error occurs, a ServiceException is thrown.
	 *
	 * @param id              The ID of the category to update.
	 * @param updatedCategory An instance of the CategoryEntity class representing
	 *                        the updated category data.
	 * @throws ServiceException    If validation fails, a database error occurs, or
	 *                             any other service-related issue arises.
	 * @throws ValidationException If the category ID is invalid or the updated
	 *                             category data does not meet the required
	 *                             validation criteria.
	 */

	public void update(int id, CategoryEntity updatedCategory) throws ServiceException, ValidationException {

		try {
			CategoryValidator.validateId(id);

			CategoryValidator.validate(updatedCategory);

			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.update(id, updatedCategory);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes an existing category from the database.
	 *
	 * This method delegates the task of deleting a category to the CategoryDAO
	 * using `categoryDAO.delete(id)`. It takes one parameter, which is the ID of
	 * the category to delete. The method first validates the category ID using
	 * CategoryValidator. If validation passes, the category is deleted from the
	 * database. If validation fails or a database error occurs, a ServiceException
	 * is thrown.
	 *
	 * @param id The ID of the category to delete.
	 * @throws ServiceException    If validation fails, a database error occurs, or
	 *                             any other service-related issue arises.
	 * @throws ValidationException If the category ID is invalid.
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {

			CategoryValidator.validateId(id);

			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.delete(id);

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

}
