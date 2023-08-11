package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.entity.ProductEntity;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.*;

public class ProductValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	public static void validate(ProductEntity product) throws ValidationException {

		if (product == null) {
			throw new ValidationException("Invalid product input");
		}
		
		validateName(product.getName());
		validateDescription(product.getDescription());
		validateTypeId(product.getTypeId()); 
			
	}
	
	public static void validateName(String name) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(name, "Product name");
		
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	
	}
	
	public static void validateTypeId(int id) throws ValidationException {
		
		if(id <= 0) {
			
			throw new ValidationException("Invalid id");
			
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ProductEntity pdt = null;
		
		try {
			
			String query = "SELECT * FROM types WHERE is_active=1 AND type_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				throw new ValidationException("Type id does not exists");
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}

		
	
	}
	
	public static void validateDescription(String description) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(description, "Description");
		
	
	}
	

}