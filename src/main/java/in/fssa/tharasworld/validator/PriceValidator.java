package in.fssa.tharasworld.validator;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.dao.*;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;

public class PriceValidator {

	/**
	 * Validates a PriceEntity object to ensure its properties meet specific
	 * criteria.
	 *
	 * This method performs a series of validation checks on a PriceEntity object to
	 * ensure that it is not null and that its properties (actualPrice,
	 * currentPrice, and discount) meet the required criteria.
	 *
	 * @param price The PriceEntity object to be validated.
	 *
	 * @throws ValidationException  If the input price is null or empty, or if any
	 *                              of its properties do not meet the validation
	 *                              criteria.
	 * @throws PersistenceException If there are issues related to data persistence
	 *                              during validation, though this exception might
	 *                              not be directly related to the validation
	 *                              itself.
	 */

	public static void validate(PriceEntity price) throws ValidationException, PersistenceException {

		if (price == null || "".equals(price)) {

			throw new ValidationException("Price cannot be null or empty");

		}

		validateActualPrice(price.getActualPrice());

		validateCurrentPrice(price.getCurrentPrice());

		validateDiscount(price.getDiscount());

	}

	/**
	 * Validates the actual price of a product to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on the actual price of a product to
	 * ensure that it is a positive value and less than 1 lakh (100,000). It is
	 * typically used to validate the actual price of products before they are
	 * stored in a data source to ensure data integrity.
	 *
	 * @param price The actual price of the product to be validated.
	 *
	 * @throws ValidationException If the actual price is zero or negative, or if it
	 *                             exceeds 1 lakh (100,000).
	 */

	public static void validateActualPrice(double price) throws ValidationException {

		if (price <= 0) {

			throw new ValidationException("Actual price connot be zero or in negative");

		}

		if (price >= 100000) {

			throw new ValidationException("Actual price must be less than 1 lakh");

		}

	}

	/**
	 * Validates the current price of a product to ensure it meets specific
	 * criteria.
	 *
	 * This method performs validation checks on the current price of a product to
	 * ensure that it is a positive value and less than 1 lakh (100,000). It is
	 * typically used to validate the current price of products before they are
	 * stored in a data source to ensure data integrity.
	 *
	 * @param price The current price of the product to be validated.
	 *
	 * @throws ValidationException If the current price is zero or negative, or if
	 *                             it exceeds 1 lakh (100,000).
	 */

	public static void validateCurrentPrice(double price) throws ValidationException {

		if (price <= 0) {

			throw new ValidationException("Current price connot be zero or in negative");

		}

		if (price >= 100000) {

			throw new ValidationException("Current price must be less than 1 lakh");

		}

	}

	/**
	 * Validates a discount value to ensure it meets specific criteria.
	 *
	 * This method performs validation checks on a discount value to ensure that it
	 * is a positive value and less than 100%. It is typically used to validate
	 * discount values before they are applied to products or transactions to ensure
	 * that the discounts are within acceptable limits.
	 *
	 * @param discount The discount value to be validated.
	 *
	 * @throws ValidationException If the discount value is zero or negative, or if
	 *                             it exceeds 100%.
	 */

	public static void validateDiscount(double discount) throws ValidationException {

		if (discount <= 0) {

			throw new ValidationException("Invalid Discount");

		}

		if (discount >= 100) {

			throw new ValidationException("Discount must be less than 100");

		}

	}

	/**
	 * Validates the existence of a price record in a data source by its ID.
	 *
	 * This method performs validation checks to ensure that a price record with the
	 * specified ID exists in a data source. It typically checks the existence of a
	 * price record before performing operations that require a valid price ID, such
	 * as retrieving or updating price information.
	 *
	 * @param id The ID of the price record to be validated.
	 *
	 * @throws ValidationException  If the ID is invalid (zero or negative).
	 * @throws PersistenceException If no price record with the specified ID exists
	 *                              in the data source.
	 */

	public static void validatePriceExists(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Invalid id");
		}

		PriceDAO priceDAO = new PriceDAO();
		priceDAO.findProductByPriceId(id);

	}

	/**
	 * Validates a price ID to ensure it meets specific criteria and exists in a
	 * data source.
	 *
	 * This method performs validation checks on a price ID to ensure that it is a
	 * positive integer and that a price record with the specified ID exists in a
	 * data source. It is typically used to validate price IDs before performing
	 * operations that require a valid price ID, such as retrieving or updating
	 * price information.
	 *
	 * @param id The price ID to be validated.
	 *
	 * @throws ValidationException  If the price ID is zero or negative.
	 * @throws PersistenceException If no price record with the specified ID exists
	 *                              in the data source.
	 */

	public static void validateId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Price id cannot be zero or in negative");
		}

		PriceDAO priceDAO = new PriceDAO();
		PriceDAO.checkPriceExists(id);

	}

	/**
	 * Validates a product ID for updating its price, ensuring it meets specific
	 * criteria and exists in a data source.
	 *
	 * This method performs validation checks on a product ID to ensure that it is a
	 * positive integer and that a product with the specified ID exists in a data
	 * source. It is typically used to validate product IDs before updating their
	 * prices to ensure data integrity.
	 *
	 * @param pdtId The product ID to be validated for updating its price.
	 *
	 * @throws ValidationException  If the product ID is zero or negative.
	 * @throws PersistenceException If no product with the specified ID exists in
	 *                              the data source.
	 */

	public static void validateProductIdForUpdatePrice(int pdtId) throws ValidationException, PersistenceException {

		if (pdtId <= 0) {
			throw new ValidationException("Product id cannot be zero or in negative");
		}

		ProductDAO productDAO = new ProductDAO();
		productDAO.checkProductExist(pdtId);

	}

}
