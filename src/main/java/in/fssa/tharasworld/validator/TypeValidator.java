package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

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
	 */

	public static void validate(TypeEntity type) throws ValidationException {

		if (type == null) {
			throw new ValidationException("Invalid type input");
		}
		
		StringUtil.rejectIfInvalidString(type.getTypeName(), "Type name");
		
		if (!Pattern.matches(NAME_PATTERN, type.getTypeName())) {
			throw new ValidationException("Type name doesn't match the pattern");
		}

		TypeDAO typedao = new TypeDAO();
		typedao.checkTypeExist(type.getTypeName());
		
		if(type.getCateId()<=0) {
			
			throw new ValidationException("Invalid category id");
			
		}
				
		typedao.checkCategoryIdExists(type.getCateId());
		
			
	}


	
}
