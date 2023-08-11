package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.util.*;

public class TypeValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	public static void validate(TypeEntity type) throws ValidationException {

		if (type == null) {
			throw new ValidationException("Invalid type input");
		}
		
		StringUtil.rejectIfInvalidString(type.getTypeName(), "Type name");
		
		if (!Pattern.matches(NAME_PATTERN, type.getTypeName())) {
			throw new ValidationException("Type name doesn't match the pattern");
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM types WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, type.getTypeName());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				throw new ValidationException("This type name is already exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
		if(type.getCateId()<=0) {
			
			throw new ValidationException("Invalid category id");
			
		}
		
		validateCategoryId(type.getCateId());
		
			
	}
	
	public static void validateCategoryId(int id) throws ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//CategoryEntity cate = null;
		
		try {
			
			String query = "SELECT * FROM categories WHERE is_active=1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				
				throw new ValidationException("Category does not exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}

	
}
