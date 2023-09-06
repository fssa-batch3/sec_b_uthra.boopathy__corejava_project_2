package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.entity.ProductEntity;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;

public class ProductDAO {
	
	/**
	 * 
	 * @return
	 */

	public Set<ProductDetailDTO> findAll() throws PersistenceException {
		
		Set<ProductDetailDTO> productList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT pdt_id, name, image_url, description, seller_id, type_id, is_active FROM products WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ProductDetailDTO product = new ProductDetailDTO();
				
				product.setPdtId(rs.getInt("pdt_id"));
				product.setName(rs.getString("name"));
				product.setImg(rs.getString("image_url"));
				product.setDescription(rs.getString("description"));
				product.setSellerId(rs.getInt("seller_id"));
				product.setTypeId(rs.getInt("type_id"));
				
				productList.add(product);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productList;
		
			
	}
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */

	public int create(ProductDetailDTO newProduct) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int productId = -1;
		
		try {
			String query = "INSERT INTO products (name, image_url, description, seller_id, type_id) VALUES (?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getImg());
			ps.setString(3, newProduct.getDescription());
			ps.setInt(4, newProduct.getSellerId());
			ps.setInt(5, newProduct.getTypeId());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				productId = rs.getInt(1);
			}
			
			System.out.println("Product  has been created successfully");
			
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}
		
		return productId;
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 */

	public void update(int id, ProductEntity updatedProduct) throws PersistenceException {

		Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	    
	    	StringBuilder queryBuilder = new StringBuilder("UPDATE products SET ");
	        List<Object> values = new ArrayList<>();
	        
	        if (updatedProduct.getName() != null) {
	            queryBuilder.append("name = ?, ");
	            values.add(updatedProduct.getName());
	        }
	        
	        if (updatedProduct.getImg() != null) {
	            queryBuilder.append("image_url = ?, ");
	            values.add(updatedProduct.getImg());
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
	        throw new PersistenceException(e.getMessage());
	   
	    } finally {
	   
	    	ConnectionUtil.close(conn, ps);
	    
	    }
	    
	}
	
	/**
	 * 
	 * @param id
	 */

	public void delete(int id) throws PersistenceException {

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
			throw new PersistenceException(e.getMessage());
		
		} finally {
			ConnectionUtil.close(con, ps);
		}		
		
	}
	
	
	public int findCategoryIdByCategoryName(String name) throws PersistenceException {
		
		int categoryId = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		CategoryEntity category = null;
		
		try {
			
			String query = "SELECT * FROM categories WHERE is_active=1 AND cate_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				category = new CategoryEntity();
				category.setCateId(rs.getInt("cate_id"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return categoryId = category.getCateId();
		
	}
	
	
	public Set<ProductDetailDTO> findByCategoryId(int id) throws PersistenceException {
		
		Set<ProductDetailDTO> productList = new HashSet<>();
		
		List<TypeEntity> types = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT type_id, name, img_url, cate_id, is_active FROM types WHERE is_active=1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				TypeEntity type = new TypeEntity();
				type.setTypeId(rs.getInt("type_id"));
				
				types.add(type);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		for(TypeEntity pdt : types) {
		
		 con = null;
		 ps = null;
		 rs = null;
		
		try {
			
			String query = "SELECT pdt_id, name, image_url, description, seller_id, type_id, is_active FROM products WHERE is_active=1 AND type_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pdt.getTypeId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ProductDetailDTO product = new ProductDetailDTO();
				
				product.setPdtId(rs.getInt("pdt_id"));
				product.setName(rs.getString("name"));
				product.setImg(rs.getString("image_url"));
				product.setDescription(rs.getString("description"));
				product.setSellerId(rs.getInt("seller_id"));
				product.setTypeId(rs.getInt("type_id"));
				
				productList.add(product);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		}
		
		return productList;
		
	}
	
	public Set<ProductDetailDTO> findByTypeId(int id) throws PersistenceException {

	    Set<ProductDetailDTO> productList = new HashSet<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        String query = "SELECT pdt_id, name, image_url, description, seller_id, type_id, is_active FROM products WHERE is_active=1 AND type_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        while (rs.next()) {

	            ProductDetailDTO pdt = new ProductDetailDTO();
	            pdt.setPdtId(rs.getInt("pdt_id"));
	            pdt.setName(rs.getString("name"));
	            pdt.setImg(rs.getString("image_url"));
	            pdt.setDescription(rs.getString("description"));
	            pdt.setSellerId(rs.getInt("seller_id"));
	            pdt.setTypeId(rs.getInt("type_id"));

	            productList.add(pdt);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return productList;
	}
	
	public int findByTypeName(String name) throws PersistenceException {

	    int typeId = 0;

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    TypeEntity type = null;

	    try {

	        String query = "SELECT type_id FROM types WHERE is_active=1 AND name = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setString(1, name);
	        rs = ps.executeQuery();

	        while (rs.next()) {

	           type = new TypeEntity();
	           type.setTypeId(rs.getInt("type_id"));
	           
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return typeId = type.getTypeId();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 */
	
	public ProductDetailDTO findByProductId(int id) throws PersistenceException {
				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ProductDetailDTO pdt = null;
		
		try {
			
			String query = "SELECT pdt_id, name, image_url, description, seller_id, type_id, is_active FROM products WHERE is_active = 1 AND pdt_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				pdt = new ProductDetailDTO();
				pdt.setPdtId(rs.getInt("pdt_id"));
				pdt.setName(rs.getString("name"));
				pdt.setImg(rs.getString("image_url"));
				pdt.setDescription(rs.getString("description"));
				pdt.setTypeId(rs.getInt("type_id"));
				
				List<PriceEntity> priceList = new ArrayList<>();

	            String priceQuery = "SELECT * FROM prices WHERE is_active=1 AND pdt_id = ?";
	            PreparedStatement pricePs = con.prepareStatement(priceQuery);
	            pricePs.setInt(1, id);
	            ResultSet priceRs = pricePs.executeQuery();

	            while (priceRs.next()) {
	                PriceEntity price = new PriceEntity();
	                price.setPriceId(priceRs.getInt("price_id"));
	                price.setActualPrice(priceRs.getDouble("actual_price"));
	                price.setCurrentPrice(priceRs.getDouble("current_price"));
	                price.setDiscount(priceRs.getDouble("discount"));

	                priceList.add(price);
	            }

	            pdt.setListOfPrices(priceList);
			}
			
		}  catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return pdt;
		
	}
	
	
	public Set<ProductDetailDTO> findAllProductsBySellerId(int id) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<ProductDetailDTO> pdt = new HashSet<>();
		
		try {
			
			String query = "SELECT pdt_id, name, image_url, description, seller_id, type_id, is_active FROM products WHERE is_active = 1 AND seller_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				ProductDetailDTO product = new ProductDetailDTO();
				
				product.setPdtId(rs.getInt("pdt_id"));
				product.setName(rs.getString("name"));
				product.setImg(rs.getString("image_url"));
				product.setDescription(rs.getString("description"));
				product.setSellerId(rs.getInt("seller_id"));
				product.setTypeId(rs.getInt("type_id"));
				
				pdt.add(product);
				
			}
			
		}  catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return pdt;
		
	}

	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	
	public static void checkProductExist(int id) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 
			 String query = "SELECT pdt_id FROM products WHERE is_active = 1 AND pdt_id = ?";
			 
			 con = ConnectionUtil.getConnection();
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, id);
			 
			 rs = ps.executeQuery();
			 
			 if(!rs.next()) {
				 
				 throw new ValidationException("Product does not exixts");
				 
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
