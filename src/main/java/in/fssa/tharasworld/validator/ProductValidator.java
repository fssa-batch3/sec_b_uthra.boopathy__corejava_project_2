package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.List;
import java.util.regex.Pattern;

import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.dao.*;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.*;

public class ProductValidator {

	private static final String NAME_PATTERN = "^[a-zA-Z ]+$";

	private static final String HTTP_PATTERN = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

	/**
	 * Validates a ProductDetailDTO object to ensure its properties meet specific
	 * criteria.
	 *
	 * This method performs a series of validation checks on a ProductDetailDTO
	 * object to ensure that it is valid and meets the required criteria. It checks
	 * for null input, validates various properties of the product, such as name,
	 * image URL, description, seller ID, type ID, and the list of prices.
	 * Additionally, it may perform additional validation checks on each property by
	 * calling specific validation methods.
	 *
	 * @param product The ProductDetailDTO object to be validated.
	 *
	 * @throws ValidationException  If the input product is null or if any of its
	 *                              properties do not meet the validation criteria.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during validation, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void validate(ProductDetailDTO product) throws ValidationException, PersistenceException {

		if (product == null) {
			throw new ValidationException("Invalid product input");
		}

		validateName(product.getName());

		validateImageUrl(product.getImg());

		validateDescription(product.getDescription());

		validateSellerId(product.getSellerId());

		validateTypeId(product.getTypeId());

		validatePriceList(product.getListOfPrices());

	}

	/**
	 * Validates a product name to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a product name to ensure that it is
	 * a valid string and that it matches a specified pattern. It is typically used
	 * to validate product names before they are stored in a data source to ensure
	 * data integrity.
	 *
	 * @param name The product name to be validated.
	 *
	 * @throws ValidationException If the product name is invalid or if it doesn't
	 *                             match the specified pattern.
	 */

	public static void validateName(String name) throws ValidationException {

		StringUtil.rejectIfInvalidString(name, "Product name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}

	}

	/**
	 * Validates an image URL to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on an image URL to ensure that it is a
	 * valid string and that it matches a specified pattern typically used for
	 * HTTP/HTTPS URLs. It is typically used to validate image URLs before they are
	 * associated with a product to ensure data integrity.
	 *
	 * @param imgUrl The image URL to be validated.
	 *
	 * @throws ValidationException If the image URL is invalid or if it doesn't
	 *                             match the specified pattern.
	 */

	public static void validateImageUrl(String imgUrl) throws ValidationException {

		StringUtil.rejectIfInvalidString(imgUrl, "Image url");

		if (!Pattern.matches(HTTP_PATTERN, imgUrl)) {
			throw new ValidationException("Image url doesn't match the pattern");
		}

	}

	/**
	 * Validates a seller ID to ensure it meets specific criteria and represents an
	 * existing seller in a data source.
	 *
	 * This method performs validation checks on a seller ID to ensure that it is a
	 * positive integer and that a user with the specified ID exists and is
	 * associated with a seller role in a data source. It is typically used to
	 * validate seller IDs before performing operations that require a valid seller
	 * ID, such as associating a seller with a product or verifying seller-related
	 * permissions.
	 *
	 * @param id The seller ID to be validated.
	 *
	 * @throws ValidationException  If the seller ID is zero or negative.
	 * @throws PersistenceException If no user with the specified ID exists or if
	 *                              the user is not associated with a seller role in
	 *                              the data source.
	 */

	public static void validateSellerId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Seller id connot be zero or in negative");
		}

		UserDAO userDAO = new UserDAO();
		userDAO.checkUserIsSeller(id);

	}

	/**
	 * Validates a product type ID to ensure it meets specific criteria and
	 * represents an existing product type in a data source.
	 *
	 * This method performs validation checks on a product type ID to ensure that it
	 * is a positive integer and that a product type with the specified ID exists in
	 * a data source. It is typically used to validate product type IDs before
	 * associating products with specific types or verifying type-related
	 * information.
	 *
	 * @param id The product type ID to be validated.
	 *
	 * @throws ValidationException  If the product type ID is zero or negative.
	 * @throws PersistenceException If no product type with the specified ID exists
	 *                              in the data source.
	 */

	public static void validateTypeId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {

			throw new ValidationException("Type id cannot be zero or in negative");

		}

		TypeDAO type = new TypeDAO();
		type.checkTypeExistWithId(id);

	}

	/**
	 * Validates a product description to ensure it is a valid string.
	 *
	 * This method performs validation checks on a product description to ensure
	 * that it is a valid string. It is typically used to validate product
	 * descriptions before they are associated with products to ensure data
	 * integrity.
	 *
	 * @param description The product description to be validated.
	 *
	 * @throws ValidationException If the product description is invalid.
	 */

	public static void validateDescription(String description) throws ValidationException {

		StringUtil.rejectIfInvalidString(description, "Description");

	}

	/**
	 * Validates a product ID to ensure it meets specific criteria and represents an
	 * existing product in a data source.
	 *
	 * This method performs validation checks on a product ID to ensure that it is a
	 * positive integer and that a product with the specified ID exists in a data
	 * source. It is typically used to validate product IDs before performing
	 * operations that require a valid product ID, such as retrieving or updating
	 * product information.
	 *
	 * @param id The product ID to be validated.
	 *
	 * @throws ValidationException  If the product ID is zero or negative.
	 * @throws PersistenceException If no product with the specified ID exists in
	 *                              the data source.
	 */

	public static void validateProductId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {

			throw new ValidationException("Product id cannot be zero or in negative");

		}

		ProductDAO pdt = new ProductDAO();
		pdt.checkProductExist(id);

	}

	////// PRICE VALIDATION //////////

	/**
	 * Validates a list of PriceEntity objects to ensure they meet specific
	 * criteria.
	 *
	 * This method performs validation checks on a list of PriceEntity objects to
	 * ensure that the list is not null or empty, and that each PriceEntity object
	 * within the list meets its specific criteria. It is typically used to validate
	 * lists of prices associated with products to ensure data integrity.
	 *
	 * @param price The list of PriceEntity objects to be validated.
	 *
	 * @throws ValidationException  If the price list is null or empty or if any of
	 *                              the PriceEntity objects within the list do not
	 *                              meet the validation criteria.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during validation, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void validatePriceList(List<PriceEntity> price) throws ValidationException, PersistenceException {

		if (price.isEmpty() || price == null) {

			throw new ValidationException("Price List cannot be null or empty");
		}

		for (PriceEntity priceList : price) {
			PriceValidator.validate(priceList);
		}

	}

	/**
	 * Validates a price ID to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a price ID to ensure that it is a
	 * positive integer. It is typically used to validate price IDs before
	 * performing operations that require a valid price ID, such as retrieving or
	 * updating price information.
	 *
	 * @param id The price ID to be validated.
	 *
	 * @throws ValidationException If the price ID is zero or negative.
	 */

	public static void validatePriceId(int id) throws ValidationException {

		if (id <= 0) {
			throw new ValidationException("Invalid price id");
		}

	}

}
