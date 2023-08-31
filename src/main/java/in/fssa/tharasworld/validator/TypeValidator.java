package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.TypeDAO;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.util.*;

public class TypeValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	
	/**
	 * 
	 * @param type
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */

	public static void validate(TypeEntity type) throws ValidationException, PersistenceException {

		if (type == null) {
			throw new ValidationException("Invalid type input");
		}
		
		if(type.getCateId()<=0) {
			
			throw new ValidationException("Category id cannot be zero or in negative");
			
		}
		
		validateCategoryId(type.getCateId());
				
		StringUtil.rejectIfInvalidString(type.getTypeName(), "Type name");
		
		if (!Pattern.matches(NAME_PATTERN, type.getTypeName())) {
			throw new ValidationException("Type name doesn't match the pattern");
		}
				
		TypeDAO typedao = new TypeDAO();

		typedao.checkTypeExistWithName(type.getTypeName());
		
			
	}
	
	public static void validateCategoryId(int id) throws ValidationException, PersistenceException {
		
		TypeDAO typedao = new TypeDAO();

		typedao.checkCategoryIdExists(id);
		
	}
	
	
	public static void validateTypeId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Type id cannot be zero or in negative");
		}
		
		TypeDAO typedao = new TypeDAO();

		typedao.checkTypeExistWithId(id);
		
		
	}


	
}
