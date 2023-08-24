package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.dao.SizeDAO;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.util.StringUtil;
import in.fssa.tharasworld.util.ConnectionUtil;

public class SizeValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z0-9_-]*$";
	
	/** 
	 * 
	 * @param size
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */

	public static void validate(SizeEntity size) throws ValidationException, PersistenceException {
		

		if (size == null) {
			
			throw new ValidationException("Invalid size input");
		
		}
			
		StringUtil.rejectIfInvalidString(size.getSizeName(), "Size name");
		
		if (!Pattern.matches(NAME_PATTERN, size.getSizeName())) {
			throw new ValidationException("Size name doesn't match the pattern");
		}
		
		SizeDAO sizedao = new SizeDAO();
		sizedao.checkSizeNameExists(size.getSizeName());
		
			
		}
	
}
