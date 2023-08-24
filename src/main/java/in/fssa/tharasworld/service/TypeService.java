package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.TypeValidator;
 
public class TypeService {
	
	/**
	 * 
	 * @return
	 */

	public Set<TypeEntity> findAll() throws ServiceException {
		
		
		
		Set<TypeEntity> typeList = null;
		
		try {
			TypeDAO typeDao = new TypeDAO();
			
			typeList = typeDao.findAll();
			
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
			
			TypeDAO typedao = new TypeDAO();
			typedao.create(newType);
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
			
			CategoryValidator.validateId(id);
			TypeValidator.validate(updatedType);
			
			TypeDAO typedao = new TypeDAO();
			typedao.update(id, updatedType);
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
			
			TypeDAO typedao = new TypeDAO();
			typedao.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
}
