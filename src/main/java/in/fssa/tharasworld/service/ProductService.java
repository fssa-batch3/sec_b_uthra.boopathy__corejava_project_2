package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.ProductDAO;
import in.fssa.tharasworld.dao.PriceDAO;
import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.Logger;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.entity.ProductEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.ProductValidator;
import in.fssa.tharasworld.validator.TypeValidator;

public class ProductService {

	/**
	 * Retrieves a set of detailed product information for all active products.
	 *
	 * This method fetches detailed product information for all active products
	 * including their prices. It combines data from both the ProductDAO and
	 * PriceDAO. The resulting set of ProductDetailDTO objects provides a
	 * comprehensive view of each product.
	 *
	 * @return A set of ProductDetailDTO objects containing detailed product
	 *         information.
	 * @throws ServiceException If a service-related issue occurs during the
	 *                          retrieval.
	 */

	public static Set<ProductDetailDTO> findAll() throws ServiceException {

		Set<ProductDetailDTO> productList;
		try {
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			productList = productDAO.findAll();
			
			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
			}

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Creates a new product with associated prices in the database.
	 *
	 * This method creates a new product and its associated prices in the database.
	 * It ensures that the provided product and price data are valid using the
	 * ProductValidator.
	 *
	 * @param newProduct An instance of the ProductDetailDTO class representing the
	 *                   new product to be created, including associated prices.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             creation.
	 * @throws ValidationException If the provided product or price data is invalid.
	 */

	public void create(ProductDetailDTO newProduct) throws ServiceException, ValidationException {

		try {
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			ProductValidator.validate(newProduct);
			ProductValidator.validatePriceList(newProduct.getListOfPrices());

			int id = productDAO.create(newProduct);

			for (PriceEntity newPrice : newProduct.getListOfPrices()) {
				priceDAO.create(newPrice, id);
			}
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates an existing product in the database.
	 *
	 * This method updates an existing product in the database with the provided
	 * product data. It validates the product data using the ProductValidator.
	 *
	 * @param id             The ID of the product to be updated.
	 * @param updatedProduct An instance of the ProductEntity class representing the
	 *                       updated product data.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             update.
	 * @throws ValidationException If the provided product data is invalid.
	 */

	public void update(int id, ProductEntity updatedProduct) throws ServiceException, ValidationException {

		try {

			ProductValidator.validateProductId(id);

			ProductDAO productDAO = new ProductDAO();

			ProductValidator.validateName(updatedProduct.getName());
			ProductValidator.validateImageUrl(updatedProduct.getImg());
			ProductValidator.validateDescription(updatedProduct.getDescription());
			ProductValidator.validateTypeId(updatedProduct.getTypeId());

			productDAO.update(id, updatedProduct);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes an existing product from the database.
	 *
	 * This method deletes an existing product from the database using the provided
	 * product ID. It validates the product ID and handles any exceptions that may
	 * occur during the deletion process.
	 *
	 * @param id The ID of the product to be deleted.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             deletion.
	 * @throws ValidationException If the provided product ID is invalid.
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {

			ProductValidator.validateProductId(id);

			ProductDAO productDAO = new ProductDAO();

			productDAO.delete(id);
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Finds all products belonging to a specific category.
	 *
	 * This method retrieves a set of product details (DTOs) for all products that
	 * belong to the specified category ID. It validates the category ID, retrieves
	 * the products from the database, and fetches their corresponding price
	 * details. If any service-related issue occurs during the operation, it throws
	 * a {@link ServiceException}. If the provided category ID is invalid, it throws
	 * a {@link ValidationException}.
	 *
	 * @param id The ID of the category for which products are to be retrieved.
	 * @return A set of product details for the products in the specified category.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided category ID is invalid.
	 */

	public static Set<ProductDetailDTO> findByCategoryId(int id) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList = null;

		try {

			CategoryValidator.validateId(id);

			ProductDAO productDAO = new ProductDAO();

			productList = productDAO.findByCategoryId(id);

			PriceDAO priceDAO = new PriceDAO();

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
			}
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Finds all products belonging to a specific category by category name.
	 *
	 * This method retrieves a set of product details (DTOs) for all products that
	 * belong to the category with the specified name. It validates the category
	 * name, retrieves the corresponding category ID from the database, and then
	 * fetches the products for that category along with their price details. If any
	 * service-related issue occurs during the operation, it throws a
	 * {@link ServiceException}. If the provided category name is invalid or no
	 * products are found for the category, it throws a {@link ValidationException}.
	 *
	 * @param name The name of the category for which products are to be retrieved.
	 * @return A set of product details for the products in the specified category.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided category name is invalid or no
	 *                             products are found for the category.
	 */

	public static Set<ProductDetailDTO> findByCategoryName(String name) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList = null;

		try {

			CategoryValidator.validateName(name);

			ProductDAO productDAO = new ProductDAO();

			int categoryId = productDAO.findCategoryIdByCategoryName(name);

			productList = productDAO.findByCategoryId(categoryId);

			PriceDAO priceDAO = new PriceDAO();

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
			}
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Finds all products belonging to a specific type by type ID.
	 *
	 * This method retrieves a set of product details (DTOs) for all products that
	 * belong to the type with the specified type ID. It validates the type ID,
	 * retrieves the products for that type along with their price details, and
	 * returns the result. If any service-related issue occurs during the operation,
	 * it throws a {@link ServiceException}. If the provided type ID is invalid or
	 * no products are found for the type, it throws a {@link ValidationException}.
	 *
	 * @param id The ID of the type for which products are to be retrieved.
	 * @return A set of product details for the products of the specified type.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided type ID is invalid or no products
	 *                             are found for the type.
	 */

	public static Set<ProductDetailDTO> findByTypeId(int id) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList = null;

		try {

			TypeValidator.validateTypeId(id);

			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			productList = productDAO.findByTypeId(id);

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
			}
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Finds all products belonging to a specific type by type name.
	 *
	 * This method retrieves a set of product details (DTOs) for all products that
	 * belong to the type with the specified type name. It validates the type name,
	 * retrieves the type ID for that name, retrieves the products for that type
	 * along with their price details, and returns the result. If any
	 * service-related issue occurs during the operation, it throws a
	 * {@link ServiceException}. If the provided type name is invalid, no type with
	 * that name exists, or no products are found for the type, it throws a
	 * {@link ValidationException}.
	 *
	 * @param name The name of the type for which products are to be retrieved.
	 * @return A set of product details for the products of the specified type.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided type name is invalid, no type
	 *                             with that name exists, or no products are found
	 *                             for the type.
	 */

	public static Set<ProductDetailDTO> findByTypeName(String name) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList = null;

		try {

			TypeValidator.validateTypeName(name);

			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			int typeId = productDAO.findByTypeName(name);

			productList = productDAO.findByTypeId(typeId);

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				
			}
		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Finds a product by its unique product ID.
	 *
	 * This method retrieves detailed information about a product specified by its
	 * unique product ID. It validates the product ID, retrieves the product's
	 * details, including its price details, and returns the result as a single
	 * {@link ProductDetailDTO}. If any service-related issue occurs during the
	 * operation, it throws a {@link ServiceException}. If the provided product ID
	 * is invalid or no product is found with that ID, it throws a
	 * {@link ValidationException}.
	 *
	 * @param id The unique identifier of the product to retrieve.
	 * @return The detailed information about the product specified by its ID.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided product ID is invalid or no
	 *                             product is found with that ID.
	 */

	public static ProductDetailDTO findByProductId(int id) throws ServiceException, ValidationException {

		ProductDetailDTO productList = null;

		try {

			ProductValidator.validateProductId(id);

			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			productList = productDAO.findByProductId(id);

			List<PriceEntity> prices = priceDAO.findByProductId(id);
			productList.setListOfPrices(prices);
			

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

	/**
	 * Finds all products associated with a seller by their seller's unique ID.
	 *
	 * This method retrieves a set of detailed information about products associated
	 * with a seller specified by their unique seller's ID. It validates the
	 * seller's ID, retrieves the products' details, including their price details,
	 * and returns the result as a set of {@link ProductDetailDTO} objects. If any
	 * service-related issue occurs during the operation, it throws a
	 * {@link ServiceException}. If the provided seller's ID is invalid or no
	 * products are associated with that seller, it throws a
	 * {@link ValidationException}.
	 *
	 * @param id The unique identifier of the seller to retrieve products for.
	 * @return A set of detailed information about products associated with the
	 *         seller.
	 * @throws ServiceException    If a service-related issue occurs during
	 *                             retrieval.
	 * @throws ValidationException If the provided seller's ID is invalid or no
	 *                             products are associated with that seller.
	 */

	public static Set<ProductDetailDTO> findAllProductsBySellerId(int id) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList;

		try {

			ProductValidator.validateSellerId(id);

			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			productList = productDAO.findAllProductsBySellerId(id);

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				
			}

		} catch (PersistenceException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}

}
