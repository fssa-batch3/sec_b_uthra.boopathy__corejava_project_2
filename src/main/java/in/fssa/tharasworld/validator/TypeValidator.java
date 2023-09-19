package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.util.*;

public class TypeValidator {

	private static final String NAME_PATTERN = "^[a-zA-Z ]+$";

	/**
	 * Validates a TypeEntity object to ensure its properties meet specific
	 * criteria.
	 *
	 * This method performs a series of validation checks on a TypeEntity object to
	 * ensure that it is valid and meets the required criteria. It checks for null
	 * input, validates various properties of the type, such as category ID, type
	 * name, and image URL. Additionally, it may perform additional validation
	 * checks on each property by calling specific validation methods. It also
	 * checks if a type with the same name already exists in a data source.
	 *
	 * @param type The TypeEntity object to be validated.
	 *
	 * @throws ValidationException  If the input type is null, if the category ID is
	 *                              zero or negative, if any of its properties do
	 *                              not meet the validation criteria, or if a type
	 *                              with the same name already exists in the data
	 *                              source.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during validation, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void validate(TypeEntity type) throws ValidationException, PersistenceException {

		if (type == null) {
			throw new ValidationException("Invalid type input");
		}

		if (type.getCateId() <= 0) {

			throw new ValidationException("Category id cannot be zero or in negative");

		}

		StringUtil.rejectIfInvalidString(type.getTypeName(), "Type name");

		StringUtil.rejectIfInvalidString(type.getImg(), "Image url");

		if (!Pattern.matches(NAME_PATTERN, type.getTypeName())) {
			throw new ValidationException("Type name doesn't match the pattern");
		}

		validateCategoryId(type.getCateId());

		TypeDAO typeDAO = new TypeDAO();

		typeDAO.checkTypeExistWithName(type.getTypeName());

	}

	/**
	 * Validates a type name to ensure it is a valid string.
	 *
	 * This method performs validation checks on a type name to ensure that it is a
	 * valid string. It is typically used to validate type names before they are
	 * associated with product types to ensure data integrity.
	 *
	 * @param name The type name to be validated.
	 *
	 * @throws ValidationException  If the type name is invalid.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during validation, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void validateTypeName(String name) throws ValidationException, PersistenceException {

		StringUtil.rejectIfInvalidString(name, "Type name");

	}

	/**
	 * Validates a category ID to ensure it meets specific criteria and represents
	 * an existing category in a data source.
	 *
	 * This method performs validation checks on a category ID to ensure that it is
	 * a positive integer and that a category with the specified ID exists in a data
	 * source. It is typically used to validate category IDs before associating them
	 * with product types or verifying category-related information.
	 *
	 * @param id The category ID to be validated.
	 *
	 * @throws ValidationException  If the category ID is zero or negative.
	 * @throws PersistenceException If no category with the specified ID exists in
	 *                              the data source.
	 */

	public static void validateCategoryId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Category id cannot be zero or in negative");
		}

		TypeDAO typeDAO = new TypeDAO();

		typeDAO.checkCategoryIdExists(id);

	}

	/**
	 * Validates a type ID to ensure it meets specific criteria and represents an
	 * existing type in a data source.
	 *
	 * This method performs validation checks on a type ID to ensure that it is a
	 * positive integer and that a type with the specified ID exists in a data
	 * source. It is typically used to validate type IDs before performing
	 * operations that require a valid type ID or verifying type-related
	 * information.
	 *
	 * @param id The type ID to be validated.
	 *
	 * @throws ValidationException  If the type ID is zero or negative.
	 * @throws PersistenceException If no type with the specified ID exists in the
	 *                              data source.
	 */

	public static void validateTypeId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Type id cannot be zero or in negative");
		}

		TypeDAO typeDAO = new TypeDAO();

		typeDAO.checkTypeExistWithId(id);

	}

}
