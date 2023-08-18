package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.util.ConnectionUtil;

public class PriceDAO {
	
	public void create(PriceEntity newPrice, int pdtId) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "INSERT INTO prices (pdt_id, actual_price, current_price, discount, size_id) VALUES (?, ?, ?, ?, ?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pdtId);
			ps.setDouble(2, newPrice.getActualPrice());
			ps.setDouble(3, newPrice.getCurrentPrice());
			ps.setDouble(3, newPrice.getDiscount());
			ps.setDouble(5, newPrice.getSizeId());
			
			ps.executeUpdate();
			
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
	
	public void update (int id, PriceEntity updatePrice) {
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	    
	    	StringBuilder queryBuilder = new StringBuilder("UPDATE prices SET ");
	        List<Object> values = new ArrayList<>();
	        
	        if (updatePrice.getActualPrice() > 0) {
	            queryBuilder.append("actual_price = ?, ");
	            values.add(updatePrice.getActualPrice());
	        }
	        
	        if (updatePrice.getCurrentPrice() > 0 ) {
	            queryBuilder.append("current_price = ?, ");
	            values.add(updatePrice.getCurrentPrice());
	        }
	        
	        if (updatePrice.getDiscount() > 0) {
	            queryBuilder.append("discount = ?, ");
	            values.add(updatePrice.getDiscount());
	        }
	       
	        queryBuilder.setLength(queryBuilder.length() - 2);
	        queryBuilder.append(" WHERE is_active = 1 AND price_id = ?");
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(queryBuilder.toString());
	       
	        for (int i = 0; i < values.size(); i++) {
	            ps.setObject(i + 1, values.get(i));
	        }
	        ps.setInt(values.size() + 1, id);
	        ps.executeUpdate();
	        System.out.println("Product price has been updated successfully");
	   
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
	
	public void delete(int priceId) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "SELECT * FROM prices WHERE is_active = 1 AND price_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, priceId);
			
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
	
	public List<PriceEntity> findByProductId(int pdtId) {
		
		List<PriceEntity> prices = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM prices WHERE is_active = 1 AND pdt_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pdtId);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				PriceEntity price = new PriceEntity();
				
				price.setPriceId(rs.getInt("price_id"));
				price.setActualPrice(rs.getDouble("actual_price"));
				price.setCurrentPrice(rs.getDouble("current_price"));
				price.setDiscount(rs.getDouble("discount"));
				price.setPdtId(rs.getInt("pdt_id"));
				price.setSizeId(rs.getInt("size_id"));
				
				prices.add(price);
				
			}
			
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

		
		return prices;
		
	}
	
public Set<PriceEntity> findAllPrices() {
		
		Set<PriceEntity> prices = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM prices WHERE is_active = 1";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
						
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				PriceEntity price = new PriceEntity();
				
				price.setPriceId(rs.getInt("price_id"));
				price.setActualPrice(rs.getDouble("actual_price"));
				price.setCurrentPrice(rs.getDouble("current_price"));
				price.setDiscount(rs.getDouble("discount"));
				price.setPdtId(rs.getInt("pdt_id"));
				price.setSizeId(rs.getInt("size_id"));
				
				prices.add(price);
				
			}
			
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

		
		return prices;
		
	}

}
