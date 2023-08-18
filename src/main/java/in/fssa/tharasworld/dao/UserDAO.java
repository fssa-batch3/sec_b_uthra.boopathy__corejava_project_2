package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.interfaces.UserInterface;


public class UserDAO implements UserInterface<UserEntity>{
	
	/**
	 * @return
	 */
	  
	@Override 
	public Set<UserEntity> findAll() {
		
		Set<UserEntity> userList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			
			String query = "SELECT * FROM users WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				UserEntity user = new UserEntity();
				user.setName(rs.getString("user_name"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAge(rs.getInt("age"));
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				
				userList.add(user);
				
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

		return userList;
		
	}
	
	/**
	 * @param newUser
	 */

	@Override
	public void create(UserEntity newuser) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO users (email, user_name, age, phone_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1,  newuser.getEmail());
			ps.setString(2,  newuser.getName());
			ps.setInt(3, newuser.getAge());
			ps.setLong(4, newuser.getPhoneNumber());
			ps.setString(5, newuser.getPassword());
			ps.setString(6, newuser.getRole());
			
			ps.executeUpdate();
			
			System.out.println("User has been created successfully");
			
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
	
	/**
	 * @param id, updateduser
	 */

	@Override
	public void update(int id, UserEntity updatedUser) {
		
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		    
		    	StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
		        List<Object> values = new ArrayList<>();
		        
		        if (updatedUser.getName() != null) {
		            queryBuilder.append("user_name = ?, ");
		            values.add(updatedUser.getName());
		        }
		        
		        if (updatedUser.getPassword() != null) {
		            queryBuilder.append("password = ?, ");
		            values.add(updatedUser.getPassword());
		        }
		        
		        if (updatedUser.getEmail() != null) {
		            queryBuilder.append("email = ?, ");
		            values.add(updatedUser.getEmail());
		        }
		       
		        queryBuilder.setLength(queryBuilder.length() - 2);
		        queryBuilder.append(" WHERE is_active = 1 AND user_id = ?");
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(queryBuilder.toString());
		       
		        for (int i = 0; i < values.size(); i++) {
		            ps.setObject(i + 1, values.get(i));
		        }
		        ps.setInt(values.size() + 1, id);
		        ps.executeUpdate();
		        System.out.println("User has been updated successfully");
		   
		    } catch (SQLException e) {
		       
		    	e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new RuntimeException(e);
		   
		    } catch (RuntimeException er) {
				
				er.printStackTrace();
				System.out.println(er.getMessage());
				throw new RuntimeException(er);
				
			} finally {
		   
		    	ConnectionUtil.close(conn, ps);
		    
		    }
		    
		}
	
	/**
	 * @param id
	 */
		

	@Override
	public void delete(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE users SET is_active = 0 WHERE is_active = 1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("User has been deleted successfully");
			
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
	
	/**
	 * @param id
	 */

	@Override
	public UserEntity findById(int id) {

		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		
		try {
			
			String query = "SELECT * FROM users WHERE is_active = 1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				
				user = new UserEntity();
				user.setAge(rs.getInt("age"));
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("user_name"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setRole(rs.getString("role"));
				
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
		
		return user;
		
	}

	
}
