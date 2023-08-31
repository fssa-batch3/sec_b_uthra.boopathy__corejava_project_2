package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.ProductDAO;
import in.fssa.tharasworld.dao.PriceDAO;
import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.entity.ProductEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.ProductValidator;
import in.fssa.tharasworld.validator.TypeValidator;

public class ProductService {

	/**
	 * 
	 * @return
	 */
	
	public static Set<ProductDetailDTO> findAll() throws ServiceException {

		Set<ProductDetailDTO> productList;
		try {
			ProductDAO productdao = new ProductDAO();
			PriceDAO pricedao = new PriceDAO();

			productList = productdao.findAll();

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = pricedao.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				System.out.println(pdt);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return productList;

	}
	
	/**
	 * 
	 * @param newProduct
	 * @throws Exception
	 */

	public void create(ProductDetailDTO newProduct) throws ServiceException, ValidationException {

		try {
			ProductDAO productdao = new ProductDAO();
			PriceDAO pricedao = new PriceDAO();

			ProductValidator.validate(newProduct);
			ProductValidator.validatePriceList(newProduct.getListOfPrices());

			int id = productdao.create(newProduct);

			for (PriceEntity newPrice : newProduct.getListOfPrices()) {
				pricedao.create(newPrice, id);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws ValidationException 
	 * @throws Exception
	 */

	public void update(int id, ProductEntity updatedProduct) throws ServiceException, ValidationException {

		try {

			ProductValidator.validateProductId(id);

			ProductDAO productdao = new ProductDAO();

			ProductValidator.validateName(updatedProduct.getName());
			ProductValidator.validateDescription(updatedProduct.getDescription());
			ProductValidator.validateTypeId(updatedProduct.getTypeId());

			productdao.update(id, updatedProduct);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */

	public void delete(int id) throws ServiceException, ValidationException {

		try {

			ProductValidator.validateProductId(id);

			ProductDAO productdao = new ProductDAO();

			productdao.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	public Set<ProductDetailDTO> findByCategoryId(int id) throws ServiceException, ValidationException {

		Set<ProductDetailDTO> productList = null;
		
		try {
			
			CategoryValidator.validateId(id);
			
			ProductDAO productdao = new ProductDAO();

			productList = productdao.findByCategoryId(id);
			
			PriceDAO pricedao = new PriceDAO();
			
			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = pricedao.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				System.out.println(pdt);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return productList;
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	public static Set<ProductDetailDTO> findByTypeId(int id) throws ServiceException, ValidationException {
		
		

		Set<ProductDetailDTO> productList = null;
				
		try {
			
			TypeValidator.validateTypeId(id);
			
			ProductDAO productdao = new ProductDAO();
			PriceDAO pricedao = new PriceDAO();
					
			productList = productdao.findByTypeId(id);
			
			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = pricedao.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				System.out.println(pdt);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return productList;
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	public static ProductDetailDTO findByProductId(int id) throws ServiceException, ValidationException  {

		

		ProductDetailDTO productList = null;
				
			try {
				
				ProductValidator.validateProductId(id);
				
				ProductDAO productdao = new ProductDAO();
				PriceDAO pricedao = new PriceDAO();
					
				productList = productdao.findByProductId(id);

				List<PriceEntity> prices = pricedao.findByProductId(id);
				productList.setListOfPrices(prices);
				System.out.println(productList);
			
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		return productList;
		
	}

}
