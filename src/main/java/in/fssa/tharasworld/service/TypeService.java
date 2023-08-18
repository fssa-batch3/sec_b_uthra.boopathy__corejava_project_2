package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.validator.TypeValidator;
 
public class TypeService {
	
	/**
	 * 
	 * @return
	 */

	public Set<TypeEntity> findAll() {
		
		TypeDAO typeDao = new TypeDAO();
		
		Set<TypeEntity> typeList = typeDao.findAll();
		
		for(TypeEntity type:typeList) {
			System.out.println(type);
		}
				
		return typeList; 
		
	}
	
	/**
	 * 
	 * @param newType
	 * @throws Exception
	 */

	public void create(TypeEntity newType) throws Exception {
		
		TypeValidator.validate(newType);
		
		TypeDAO typedao = new TypeDAO();
		typedao.create(newType);
		
	}
	/**
	 * 
	 * @param id
	 * @param updatedType
	 * @throws Exception
	 */
	
	public void update(int id, TypeEntity updatedType) throws Exception {
		
		TypeValidator.validate(updatedType);
		
		TypeDAO typedao = new TypeDAO();
		typedao.update(id, updatedType);
		
	}
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	
	public void delete(int id) throws Exception {
		
		if(id==0) {
			throw new ValidationException("Invalid id");
		}
		
		TypeDAO typedao = new TypeDAO();
		typedao.delete(id);
		
	}
	
}
