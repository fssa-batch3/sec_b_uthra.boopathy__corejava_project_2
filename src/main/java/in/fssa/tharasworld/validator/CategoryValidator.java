package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.CategoryDAO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.util.*;

public class CategoryValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */

	public static void validate(CategoryEntity category) throws ValidationException, PersistenceException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}
		
		StringUtil.rejectIfInvalidString(category.getCateName(), "Category name");
		
		StringUtil.rejectIfInvalidString(category.getImg(), "Image url");
		
		if (!Pattern.matches(NAME_PATTERN, category.getCateName())) {
			throw new ValidationException("Category name doesn't match the pattern");
		}
		
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.checkCategoryExist(category.getCateName());
			
		}
	
	public static void validateName(String name) throws ValidationException, PersistenceException {
		
		StringUtil.rejectIfInvalidString(name, "Category name");
		
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Category name doesn't match the pattern");
		}
	}
	
	public static void validateId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Category id cannot be zero or in negative");
		}
		
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.checkCategoryExistWithId(id);
	}
		
	}
	
