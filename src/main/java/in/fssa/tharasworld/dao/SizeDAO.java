package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.util.ConnectionUtil;

public class SizeDAO { 
	
	/**
	 * 
	 * @return
	 */

	 
	public Set<SizeEntity> findAll() {
		
		Set<SizeEntity> sizeList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM sizes WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				SizeEntity size = new SizeEntity();
				size.setSizeName(rs.getString("size_name"));
				size.setSizeId(rs.getInt("size_id"));		
				
				sizeList.add(size);
				
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

		return sizeList;
		
	}

	
	/**
	 * 
	 * @param newSize
	 */
	
	public void create(SizeEntity newSize) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO sizes (size_name) VALUES (?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1,  newSize.getSizeName());
			
			ps.executeUpdate();
			
			System.out.println("Size has been created successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
			
		} catch (RuntimeException er) {
			
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);
			
		} finally {
			ConnectionUtil.close(connection, ps);
		}

		
	}
	
}
