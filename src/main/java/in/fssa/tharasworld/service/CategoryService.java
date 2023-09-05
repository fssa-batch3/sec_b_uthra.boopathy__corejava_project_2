package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.CategoryValidator;


public class CategoryService {
	
	
	/**
	 *  
	 * @return
	 * @throws ServiceException 
	 */
	public static Set<CategoryEntity> findAll() throws ServiceException { 
		
		Set<CategoryEntity> CategoryList;
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			
			CategoryList = categoryDAO.findAll();
			
			for(CategoryEntity cate:CategoryList) {
				System.out.println(cate);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				
		return CategoryList; 
		
	}

	/**
	 * 
	 * @param newCategory
	 * @throws ValidationException 
	 * @throws Exception
	 */
	
	public void create(CategoryEntity newCategory) throws ServiceException, ValidationException {
		
		try {
			CategoryValidator.validate(newCategory);
			
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.create(newCategory);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws Exception
	 */
	
	public void update(int id, CategoryEntity updatedCategory) throws ServiceException, ValidationException {
		
		try {
			CategoryValidator.validateId(id);
			
			CategoryValidator.validate(updatedCategory);
			
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.update(id, updatedCategory);
		
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
			
			CategoryValidator.validateId(id);
			
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.delete(id);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}
