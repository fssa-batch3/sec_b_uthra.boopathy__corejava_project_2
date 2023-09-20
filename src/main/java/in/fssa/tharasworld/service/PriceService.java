package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.PriceDAO;
import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.PriceValidator;
import in.fssa.tharasworld.validator.ProductValidator;

public class PriceService {

	/**
	 * Retrieves a set of all price entities from the database.
	 *
	 * This method delegates the task of fetching all price entities to the PriceDAO
	 * using `priceDAO.findAllPrices()`. It returns a set of `PriceEntity` objects
	 * representing the prices retrieved from the database. If a database error
	 * occurs during the operation, a ServiceException is thrown.
	 *
	 * @return A set of PriceEntity objects representing all price entities in the
	 *         database.
	 * @throws ServiceException If a database error occurs or any other
	 *                          service-related issue arises.
	 */

	public Set<PriceEntity> findAll() throws ServiceException {

		Set<PriceEntity> priceList;
		try {
			PriceDAO priceDAO = new PriceDAO();

			priceList = priceDAO.findAllPrices();

			for (PriceEntity price : priceList) {
				System.out.println(price);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return priceList;
	}

	/**
	 * Updates the price of a product with the specified product ID.
	 *
	 * This method first validates the product ID and the price entity, ensuring
	 * they meet the required criteria. It then delegates the task of updating the
	 * price to the PriceDAO by first finding the existing price ID for the product
	 * and then updating the price. If the price ID cannot be found, it will delete
	 * the existing price entry, create a new one, and associate it with the
	 * product.
	 *
	 * @param pdtId The ID of the product for which the price needs to be updated.
	 * @param price The updated PriceEntity containing the new price details.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             price update.
	 * @throws ValidationException If the product ID or price entity fails
	 *                             validation.
	 */

	public static void update(int pdtId, PriceEntity price) throws ServiceException, ValidationException {

		try {

			PriceValidator.validateProductIdForUpdatePrice(pdtId);

			PriceValidator.validate(price);

			PriceDAO priceDAO = new PriceDAO();

			int priceId = priceDAO.findPriceIdByProduct(pdtId);

			System.out.println(priceId);

			priceDAO.delete(priceId);

			priceDAO.update(price, pdtId);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Deletes the price entry with the specified price ID.
	 *
	 * This method first validates the price ID to ensure it meets the required
	 * criteria. It then delegates the task of deleting the price entry to the
	 * PriceDAO using the provided price ID.
	 *
	 * @param id The ID of the price entry to be deleted.
	 * @throws ServiceException    If a service-related issue occurs during the
	 *                             deletion.
	 * @throws ValidationException If the price ID fails validation.
	 */

	public static void delete(int id) throws ServiceException, ValidationException {

		try {
			PriceValidator.validateId(id);

			PriceDAO priceDAO = new PriceDAO();

			priceDAO.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public static int findProductByPriceId(int id) throws ValidationException, ServiceException {
		
		int pdtId = 0;
			
		try {
		
			PriceDAO priceDAO = new PriceDAO();
			pdtId = priceDAO.findProductByPriceId(id);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
			return pdtId;
			
		
	}
	
	public static PriceEntity findPriceByPriceId(int id) throws ValidationException, ServiceException {
		
		PriceEntity price = null;
			
		try {
		
			PriceDAO priceDAO = new PriceDAO();
			price = priceDAO.findPriceByPriceId(id);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
			return price;
			
		
	}

}
