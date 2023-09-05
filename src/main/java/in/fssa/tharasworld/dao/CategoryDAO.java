 package in.fssa.tharasworld.dao;
 
import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.interfaces.CategoryInterface;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;

public class CategoryDAO implements CategoryInterface<CategoryEntity>{
	
	/**
	 * @return
	 * @throws PersistenceException 
	 */ 

	@Override
	public Set<CategoryEntity> findAll() throws PersistenceException {
	
		Set<CategoryEntity> categoryList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT cate_id, cate_name, img_url, is_active FROM categories WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CategoryEntity category = new CategoryEntity();
				category.setCateId(rs.getInt("cate_id"));
				category.setCateName(rs.getString("cate_name"));
				category.setImg(rs.getString("img_url"));
				
				categoryList.add(category);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return categoryList;
		
		
	}
	
	/**
	 * @param newCategory
	 * @throws PersistenceException 
	 */

	@Override
	public void create(CategoryEntity newCategory) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO categories (cate_name, img_url) VALUES (?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1, newCategory.getCateName());
			ps.setString(2, newCategory.getImg());
			
			ps.executeUpdate();
			
			System.out.println("Category has been created successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(connection, ps);
		}
		
		
	}
	
	/**
	 * @param id updatedCategory
	 */

	@Override
	public void update(int id, CategoryEntity updatedCategory) throws PersistenceException {


	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	    
	    	String query = "UPDATE categories SET cate_name=?, img_url=? WHERE is_active=1 AND cate_id=?";
	    	
	    	con = ConnectionUtil.getConnection();
	    	ps = con.prepareStatement(query);
	    	 
	        ps.setString(1, updatedCategory.getCateName());
	        
	        ps.setString(2, updatedCategory.getImg());
	        
	        ps.setInt(3, id);
	        
	        ps.executeUpdate();
	       
	        System.out.println("Category has been updated successfully");
	   
	    } catch (SQLException e) {
	       
	    	e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e.getMessage());
	   
	    } finally {
	   
	    	ConnectionUtil.close(con, ps);
	    
	    }
		
	}
	
	/**
	 * @param id
	 */

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE categories SET is_active = 0 WHERE is_active = 1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			System.out.println("Category has been deleted successfully");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}
	
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	
	public static void checkCategoryExist (String name) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//CategoryEntity cate = null;
		
		try {
			
			String query = "SELECT cate_name FROM categories WHERE is_active=1 AND cate_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				throw new ValidationException("This category name is already exists");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public static void checkCategoryExistWithId (int id) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//CategoryEntity cate = null;
		
		try {
			
			String query = "SELECT cate_id FROM categories WHERE is_active=1 AND cate_id = ?";
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
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
	}
	
}
