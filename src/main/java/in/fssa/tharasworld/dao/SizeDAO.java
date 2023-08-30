package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;

public class SizeDAO { 
	
	/**
	 * 
	 * @return
	 */

	 
	public Set<SizeEntity> findAll() throws PersistenceException {
		
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
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return sizeList;
		
	}

	
	/**
	 * 
	 * @param newSize
	 */
	
	public void create(SizeEntity newSize) throws PersistenceException {
		
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
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(connection, ps);
		}

		
	}
	
	public void checkSizeNameExists (String name) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SizeEntity catefory = null;
		
		try {
			
			String query = "SELECT * FROM sizes WHERE is_active=1 AND size_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				throw new ValidationException("This size is already exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public void checkSizeExistWithId (int id) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SizeEntity catefory = null;
		
		try {
			
			String query = "SELECT * FROM sizes WHERE is_active=1 AND size_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				
				throw new ValidationException("Size does not exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
}
