package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.util.ConnectionUtil;

public class ProductDAO {

	public Set<ProductDetailDTO> findAll() {
		
		Set<ProductDetailDTO> productList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM products WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ProductDetailDTO product = new ProductDetailDTO();
				product.setPdtId(rs.getInt("pdt_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setSellerId(rs.getInt("seller_id"));
				product.setTypeId(rs.getInt("type_id"));
				
				productList.add(product);
				
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

		return productList;
		
			
	}

	public int create(ProductDetailDTO newProduct) {

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO products (name, description, seller_id, type_id) VALUES (?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getSellerId());
			ps.setInt(4, newProduct.getTypeId());
			
			ps.executeUpdate();
			
			System.out.println("Product  has been created successfully");
			
			int productId;
			
			return productId = newProduct.getPdtId();
			
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
	public void update(int id, ProductEntity updatedProduct) {

		Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	    
	    	StringBuilder queryBuilder = new StringBuilder("UPDATE products SET ");
	        List<Object> values = new ArrayList<>();
	        
	        if (updatedProduct.getName() != null) {
	            queryBuilder.append("name = ?, ");
	            values.add(updatedProduct.getName());
	        }
	        
	        if (updatedProduct.getDescription() != null) {
	            queryBuilder.append("description = ?, ");
	            values.add(updatedProduct.getDescription());
	        }
	        
	        if (updatedProduct.getTypeId() > 0) {
	            queryBuilder.append("type_id = ?, ");
	            values.add(updatedProduct.getTypeId());
	        }
	       
	        queryBuilder.setLength(queryBuilder.length() - 2);
	        queryBuilder.append(" WHERE is_active = 1 AND pdt_id = ?");
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(queryBuilder.toString());
	       
	        for (int i = 0; i < values.size(); i++) {
	            ps.setObject(i + 1, values.get(i));
	        }
	        ps.setInt(values.size() + 1, id);
	        ps.executeUpdate();
	        System.out.println("Product has been updated successfully");
	   
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

	@Override
	public void delete(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE products SET is_active = 0 WHERE is_active = 1 AND pdt_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Product has been deleted successfully");
			
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
