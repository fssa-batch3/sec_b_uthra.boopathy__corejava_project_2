package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.regex.Pattern;

import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.util.StringUtil;
import in.fssa.tharasworld.util.ConnectionUtil;

public class SizeValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	
	/**
	 * 
	 * @param size
	 * @throws ValidationException
	 */

	public static void validate(SizeEntity size) throws ValidationException {
		

		if (size == null) {
			
			throw new ValidationException("Invalid size input");
		
		}
			
		StringUtil.rejectIfInvalidString(size.getSizeName(), "Size name");
		
		if (!Pattern.matches(NAME_PATTERN, size.getSizeName())) {
			throw new ValidationException("Size name doesn't match the pattern");
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SizeEntity catefory = null;
		
		try {
			
			String query = "SELECT * FROM sizes WHERE is_active=1 AND size_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, size.getSizeName());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				throw new ValidationException("This size is already exists");
				
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
