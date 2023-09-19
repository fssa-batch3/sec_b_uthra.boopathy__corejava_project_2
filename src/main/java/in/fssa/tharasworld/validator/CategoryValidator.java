package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.util.*;

public class CategoryValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	/**
	 * Validates a CategoryEntity object to ensure its properties meet specific
	 * criteria.
	 *
	 * This method performs a series of validation checks on a CategoryEntity object
	 * to ensure that it is valid and meets the required criteria. It checks for
	 * null input, validates strings for the category name and image URL, and
	 * ensures that the category name matches a specified pattern. Additionally, it
	 * checks if a category with the same name already exists in a data source.
	 *
	 * @param category The CategoryEntity object to be validated.
	 *
	 * @throws ValidationException  If the input category is null, if the category
	 *                              name or image URL is invalid, or if the category
	 *                              name doesn't match the specified pattern.
	 * @throws PersistenceException If a category with the same name already exists
	 *                              in a data source, typically when checking for
	 *                              uniqueness in a category name.
	 */

	public static void validate(CategoryEntity category) throws ValidationException, PersistenceException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		StringUtil.rejectIfInvalidString(category.getCateName(), "Category name");

		StringUtil.rejectIfInvalidString(category.getImg(), "Image url");

		if (!Pattern.matches(NAME_PATTERN, category.getCateName())) {
			throw new ValidationException("Category name doesn't match the pattern");
		}

		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.checkCategoryExist(category.getCateName());

	}

	/**
	 * Validates a category name to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a category name to ensure that it
	 * is a valid string and that it matches a specified pattern. It is typically
	 * used to validate category names before they are stored in a data source to
	 * ensure data integrity.
	 *
	 * @param name The category name to be validated.
	 *
	 * @throws ValidationException  If the category name is invalid or if it doesn't
	 *                              match the specified pattern.
	 * @throws PersistenceException If a category with the same name already exists
	 *                              in a data source, typically when checking for
	 *                              uniqueness in category names.
	 */

	public static void validateName(String name) throws ValidationException, PersistenceException {

		StringUtil.rejectIfInvalidString(name, "Category name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Category name doesn't match the pattern");
		}
	}

	/**
	 * Validates a category ID to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a category ID to ensure that it is
	 * a positive integer. It is typically used to validate category IDs before
	 * performing operations that require a valid category ID, such as database
	 * queries.
	 *
	 * @param id The category ID to be validated.
	 *
	 * @throws ValidationException  If the category ID is zero or negative.
	 * @throws PersistenceException If no category with the specified ID exists in a
	 *                              data source, typically when checking the
	 *                              existence of a category by ID.
	 */

	public static void validateId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Category id cannot be zero or in negative");
		}

		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.checkCategoryExistWithId(id);
	}

}
