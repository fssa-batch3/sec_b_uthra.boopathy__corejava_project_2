 package in.fssa.tharasworld.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.tharasworld.interfaces.CategoryInterface;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.util.ConnectionUtil;

public class CategoryDAO implements CategoryInterface<CategoryEntity>{

	@Override
	public Set<CategoryEntity> findAll() {
	
		Set<CategoryEntity> categoryList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM categories WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CategoryEntity category = new CategoryEntity();
				category.setCateId(rs.getInt("cate_id"));
				category.setCateName(rs.getString("cate_name"));
				
				categoryList.add(category);
				
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

		return categoryList;
		
		
	}

	@Override
	public void create(CategoryEntity newCategory) {

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO categories (cate_name) VALUES (?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1, newCategory.getCateName());
			
			ps.executeUpdate();
			
			System.out.println("Category has been created successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			
		} catch (RuntimeException er) {
			
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);
			
		} finally {
			ConnectionUtil.close(connection, ps);
		}
		
		
	}

	@Override
	public void update(int id, CategoryEntity updatedCategory) {


	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	    
	    	String query = "UPDATE categories SET cate_name=? WHERE is_active=1 AND cate_id=?";
	    	
	    	con = ConnectionUtil.getConnection();
	    	ps = con.prepareStatement(query);
	    	
	        ps.setString(1, updatedCategory.getCateName());
	        
	        ps.setInt(2, id);
	        
	        ps.executeUpdate();
	       
	        System.out.println("Category has been updated successfully");
	   
	    } catch (SQLException e) {
	       
	    	e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	   
	    } catch (RuntimeException er) {
			
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);
			
		} finally {
	   
	    	ConnectionUtil.close(con, ps);
	    
	    }
		
	}

	@Override
	public void delete(int id) {

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
			throw new RuntimeException(e);
		
		} catch (RuntimeException er) {
			
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);
			
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}
	
}
