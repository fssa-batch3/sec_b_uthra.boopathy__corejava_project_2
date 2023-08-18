package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.CategoryValidator;


public class CategoryService {
	
	
	/**
	 *  
	 * @return
	 */
	public Set<CategoryEntity> findAll() { 
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		Set<CategoryEntity> CategoryList = categoryDao.findAll();
		
		for(CategoryEntity cate:CategoryList) {
			System.out.println(cate);
		}
				
		return CategoryList; 
		
	}

	/**
	 * 
	 * @param newCategory
	 * @throws Exception
	 */
	
	public void create(CategoryEntity newCategory) throws Exception {
		
		CategoryValidator.validate(newCategory);
		
		CategoryDAO categorydao = new CategoryDAO();
		categorydao.create(newCategory);
		
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws Exception
	 */
	
	public void update(int id, CategoryEntity updatedCategory) throws Exception {
		
		if(id==0) {
			throw new ValidationException("Invalid id");
		}
		
		CategoryValidator.validate(updatedCategory);
		
		CategoryDAO categorydao = new CategoryDAO();
		categorydao.update(id, updatedCategory);
		
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
		
		CategoryDAO categorydao = new CategoryDAO();
		categorydao.delete(id);
		
	}

}
