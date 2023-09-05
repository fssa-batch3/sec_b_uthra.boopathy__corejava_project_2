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
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			productList = productDAO.findAll();

			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
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
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();

			ProductValidator.validate(newProduct);
			ProductValidator.validatePriceList(newProduct.getListOfPrices());

			int id = productDAO.create(newProduct);

			for (PriceEntity newPrice : newProduct.getListOfPrices()) {
				priceDAO.create(newPrice, id);
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

			ProductDAO productDAO = new ProductDAO();

			ProductValidator.validateName(updatedProduct.getName());
			ProductValidator.validateImageUrl(updatedProduct.getImg());
			ProductValidator.validateDescription(updatedProduct.getDescription());
			ProductValidator.validateTypeId(updatedProduct.getTypeId());

			productDAO.update(id, updatedProduct);
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

			ProductDAO productDAO = new ProductDAO();

			productDAO.delete(id);
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
				System.out.println(pdt);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return productList;
		
	}
	
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
			
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();
					
			productList = productDAO.findByTypeId(id);
			
			for (ProductDetailDTO pdt : productList) {
				List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
				pdt.setListOfPrices(prices);
				System.out.println(pdt);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return productList;
		
	}
	
	
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
				
				ProductDAO productDAO = new ProductDAO();
				PriceDAO priceDAO = new PriceDAO();
					
				productList = productDAO.findByProductId(id);

				List<PriceEntity> prices = priceDAO.findByProductId(id);
				productList.setListOfPrices(prices);
				System.out.println(productList);
			
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		return productList;
		
	}
	
	
	public static Set<ProductDetailDTO> findAllProductsBySellerId(int id) throws ServiceException, ValidationException  {

		

		Set<ProductDetailDTO> productList;
				
			try {
				
				ProductValidator.validateProductId(id);
				
				ProductDAO productDAO = new ProductDAO();
				PriceDAO priceDAO = new PriceDAO();
					
				productList = productDAO.findAllProductsBySellerId(id);
				
				
				for (ProductDetailDTO pdt : productList) {
					List<PriceEntity> prices = priceDAO.findByProductId(pdt.getPdtId());
					pdt.setListOfPrices(prices);
					System.out.println(pdt);
				}

				for(ProductDetailDTO pdt: productList) {
				
				System.out.println(pdt);
				
				}
			
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		return productList;
		
	}

}
