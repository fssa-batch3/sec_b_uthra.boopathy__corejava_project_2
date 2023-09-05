package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.PriceDAO;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.PriceValidator;
import in.fssa.tharasworld.validator.ProductValidator;

public class PriceService {

	/**
	 * 
	 * @return
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
	 * 
	 * @param id
	 * @param price
	 * @throws Exception
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
	
	/// delete size id or price id

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

}
