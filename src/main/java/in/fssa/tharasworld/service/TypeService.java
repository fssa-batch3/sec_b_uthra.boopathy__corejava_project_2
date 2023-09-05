package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.TypeValidator;
import in.fssa.tharasworld.validator.UserValidator;
 
public class TypeService {
	
	/**
	 * 
	 * @return
	 */

	public Set<TypeEntity> findAll() throws ServiceException {
		
		
		
		Set<TypeEntity> typeList = null;
		
		try {
			TypeDAO typeDAO = new TypeDAO();
			
			typeList = typeDAO.findAll();
			
			for(TypeEntity type:typeList) {
				System.out.println(type);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				
		return typeList; 
		
	}
	
	/**
	 * 
	 * @param newType
	 * @throws Exception
	 */

	public void create(TypeEntity newType) throws ServiceException, ValidationException {
		
		try {
			TypeValidator.validate(newType);
			
			TypeDAO typeDAO = new TypeDAO();
			typeDAO.create(newType);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}
	/**
	 * 
	 * @param id
	 * @param updatedType
	 * @throws Exception
	 */
	
	public void update(int id, TypeEntity updatedType) throws ServiceException, ValidationException {
		
		try {
			
			TypeValidator.validateTypeId(id);
			TypeValidator.validate(updatedType);
			
			TypeDAO typeDAO = new TypeDAO();
			typeDAO.update(id, updatedType);
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
			
			TypeValidator.validateTypeId(id);
			
			TypeDAO typeDAO = new TypeDAO();
			typeDAO.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public static Set<TypeEntity> findAllTypesByCategoryId(int id) throws ServiceException, ValidationException, PersistenceException {
		
		TypeValidator.validateCategoryId(id);
		
		Set<TypeEntity> typeList = null;
		
		try {
			TypeDAO typeDAO = new TypeDAO();
			
			typeList = typeDAO.findAllTypeByCategoryId(id);
			
			for(TypeEntity type:typeList) {
				System.out.println(type);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				
		return typeList; 
		
	}
	
	public static TypeEntity findByTypeId(int id) throws ValidationException, PersistenceException, ServiceException {
		
		TypeValidator.validateTypeId(id);
		
		TypeEntity type = null;
		
		try {
			TypeDAO typeDAO = new TypeDAO();
			
			type = typeDAO.checkTypeExistWithId(id);
			
				System.out.println(type);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				
		return type; 
		
	}
	
}
