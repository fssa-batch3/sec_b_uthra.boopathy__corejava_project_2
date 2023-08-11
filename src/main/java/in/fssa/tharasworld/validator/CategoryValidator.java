package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.util.*;

public class CategoryValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	public static void validate(CategoryEntity category) throws ValidationException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}
		
		StringUtil.rejectIfInvalidString(category.getCateName(), "Category name");
		
		if (!Pattern.matches(NAME_PATTERN, category.getCateName())) {
			throw new ValidationException("Category name doesn't match the pattern");
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//CategoryEntity cate = null;
		
		try {
			
			String query = "SELECT * FROM categories WHERE is_active=1 AND cate_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, category.getCateName());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				throw new ValidationException("This category name is already exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			
		} catch (RuntimeException er) {
			
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
			
		}
		
	}
	
