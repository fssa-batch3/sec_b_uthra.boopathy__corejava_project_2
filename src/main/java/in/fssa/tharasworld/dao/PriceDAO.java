package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;

public class PriceDAO {
	
	/**
	 * 
	 * @param newPrice
	 * @param pdtId
	 */
	
	public void create(PriceEntity newPrice, int pdtId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "INSERT INTO prices (pdt_id, actual_price, current_price, discount, size_id) VALUES (?, ?, ?, ?, ?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pdtId);
			ps.setDouble(2, newPrice.getActualPrice());
			ps.setDouble(3, newPrice.getCurrentPrice());
			ps.setDouble(4, newPrice.getDiscount());
			ps.setDouble(5, newPrice.getSizeId());
			
			ps.executeUpdate(); 
			
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
	 * @param id
	 * @param updatePrice
	 */
	
	public void update (PriceEntity updatePrice, int pdtId, int sizeId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "INSERT INTO prices (pdt_id, actual_price, current_price, discount, size_id) VALUES (?, ?, ?, ?, ?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pdtId);
			ps.setDouble(2, updatePrice.getActualPrice());
			ps.setDouble(3, updatePrice.getCurrentPrice());
			ps.setDouble(4, updatePrice.getDiscount());
			ps.setDouble(5, sizeId);
			
			ps.executeUpdate();
			
			System.out.println("Product price has been updated successfully!");
			
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
	 * @param priceId
	 */
	
	public void delete(int priceId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "UPDATE prices SET is_active = 0 WHERE is_active = 1 AND price_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, priceId);
			
			ps.executeUpdate();
			
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
	 * @param pdtId
	 * @return
	 */
	
	public List<PriceEntity> findByProductId(int pdtId) throws PersistenceException {
		
		List<PriceEntity> prices = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM prices WHERE pdt_id = ?";
			
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
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps);
		}

		
		return prices;
		
	}
	
	/**
	 * 
	 * @return
	 */
	
	public Set<PriceEntity> findAllPrices() throws PersistenceException {
		
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
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps);
		}

		
		return prices;
		
	}
	
	
	public int findByPriceIdByProductAndSizeId(int pdtId, int sizeId) throws PersistenceException {
		
		int priceId = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PriceEntity price = null;
		
		try {
			
			String query = "SELECT * FROM prices WHERE pdt_id=? AND size_id=?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pdtId);
			ps.setInt(2, sizeId);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				price = new PriceEntity();
				
				price.setPriceId(rs.getInt("price_id"));
					
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps);
		}

		
		return priceId = price.getPriceId();
		
	}
	
	
	public static void checkPriceExists (int id) throws PersistenceException, ValidationException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT price_id FROM prices WHERE is_active = 1 AND price_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			 
			if(!rs.next()) {
				throw new ValidationException("Price id does not exists");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
	}

}
