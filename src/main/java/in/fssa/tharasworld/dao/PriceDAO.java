package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;

public class PriceDAO {

	/**
	 * Creates a new price entry for a product in the database.
	 *
	 * This method inserts a new price entry into the 'prices' table with the
	 * provided product ID, actual price, current price, and discount values.
	 *
	 * @param newPrice An instance of the PriceEntity class representing the new
	 *                 price entry.
	 * @param pdtId    The unique identifier of the product for which the price
	 *                 entry is created.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void create(PriceEntity newPrice, int pdtId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO prices (pdt_id, actual_price, current_price, discount) VALUES (?, ?, ?, ?)";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, pdtId);
			ps.setDouble(2, newPrice.getActualPrice());
			ps.setDouble(3, newPrice.getCurrentPrice());
			ps.setDouble(4, newPrice.getDiscount());

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Updates the price of a product in the database.
	 *
	 * This method inserts a new price entry into the 'prices' table for the
	 * provided product ID, actual price, current price, and discount values,
	 * effectively updating the product's price.
	 *
	 * @param updatePrice An instance of the PriceEntity class representing the
	 *                    updated price information.
	 * @param pdtId       The unique identifier of the product for which the price
	 *                    is updated.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void update(PriceEntity updatePrice, int pdtId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO prices (pdt_id, actual_price, current_price, discount) VALUES (?, ?, ?, ?)";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, pdtId);
			ps.setDouble(2, updatePrice.getActualPrice());
			ps.setDouble(3, updatePrice.getCurrentPrice());
			ps.setDouble(4, updatePrice.getDiscount());

			ps.executeUpdate();

			System.out.println("Product price has been updated successfully!");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Marks a price entry as inactive in the database.
	 *
	 * This method updates the 'is_active' column to '0' (false) for a price entry
	 * with the specified price ID, effectively marking it as inactive and not
	 * available for further use.
	 *
	 * @param priceId The unique identifier of the price entry to be marked as
	 *                inactive.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
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

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a list of price entries for a product by its product ID from the
	 * database.
	 *
	 * This method queries the 'prices' table to retrieve a list of price entries
	 * that belong to a product with the specified product ID and are marked as
	 * active ('is_active' = 1) in the database.
	 *
	 * @param pdtId The unique identifier of the product for which price entries are
	 *              retrieved.
	 * @return A list of PriceEntity objects representing the price entries for the
	 *         specified product.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public List<PriceEntity> findByProductId(int pdtId) throws PersistenceException {

		List<PriceEntity> prices = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		PriceEntity price = null;

		try {

			String query = "SELECT price_id, pdt_id, actual_price, current_price, discount, is_active FROM prices WHERE is_active=1 AND pdt_id = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, pdtId);

			rs = ps.executeQuery();

			while (rs.next()) {

				price = new PriceEntity();

				price.setPriceId(rs.getInt("price_id"));
				price.setActualPrice(rs.getDouble("actual_price"));
				price.setCurrentPrice(rs.getDouble("current_price"));
				price.setDiscount(rs.getDouble("discount"));
				price.setPdtId(rs.getInt("pdt_id"));

				prices.add(price);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return prices;

	}

	/**
	 * Retrieves a set of all active price entries from the database.
	 *
	 * This method queries the 'prices' table to retrieve all price entries that are
	 * marked as active ('is_active' = 1) in the database and returns them as a set
	 * of PriceEntity objects.
	 *
	 * @return A set of PriceEntity objects representing all active price entries in
	 *         the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public Set<PriceEntity> findAllPrices() throws PersistenceException {

		Set<PriceEntity> prices = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT price_id, pdt_id, actual_price, current_price, discount, is_active FROM prices WHERE is_active = 1";

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

				prices.add(price);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return prices;

	}

	/**
	 * Retrieves the price ID associated with a product from the database.
	 *
	 * This method queries the 'prices' table to retrieve the price ID of a product
	 * with the specified product ID, provided that the price entry is marked as
	 * active ('is_active' = 1) in the database.
	 *
	 * @param pdtId The unique identifier of the product for which the price ID is
	 *              retrieved.
	 * @return The price ID associated with the specified product, or 0 if no active
	 *         price entry is found.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public int findPriceIdByProduct(int pdtId) throws PersistenceException {

		int priceId = 0;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		PriceEntity price = null;

		try {

			String query = "SELECT price_id, pdt_id, actual_price, current_price, discount, is_active FROM prices WHERE is_active = 1 AND pdt_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, pdtId);

			rs = ps.executeQuery();

			while (rs.next()) {

				price = new PriceEntity();

				price.setPriceId(rs.getInt("price_id"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return priceId = price.getPriceId();

	}
	
	
	
	
	public int findProductByPriceId(int priceId) throws PersistenceException, ValidationException {

		int pdtId = 0;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		PriceEntity price = null;

		try {

			String query = "SELECT price_id, pdt_id, actual_price, current_price, discount, is_active FROM prices WHERE price_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, priceId);

			rs = ps.executeQuery();

			if (rs.next()) {

				price = new PriceEntity();

				price.setPdtId(rs.getInt("pdt_id"));

			} else {
				
				throw new ValidationException("Price id does not exists");
				
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return pdtId = price.getPdtId();

	}
	
	
	public static PriceEntity findPriceByPriceId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PriceEntity price = null;

		try {

			String query = "SELECT price_id, pdt_id, actual_price, current_price, discount FROM prices WHERE price_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {

				price = new PriceEntity();

				price.setPriceId(rs.getInt("price_id"));
				price.setActualPrice(rs.getDouble("actual_price"));
				price.setCurrentPrice(rs.getDouble("current_price"));
				price.setDiscount(rs.getDouble("discount"));
				price.setPdtId(rs.getInt("pdt_id"));

				
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return price;

	}

	/**
	 * Checks if a price entry with the specified price ID exists and is active in
	 * the database.
	 *
	 * This method queries the 'prices' table to check if a price entry with the
	 * provided price ID exists and is marked as active ('is_active' = 1) in the
	 * database.
	 *
	 * @param id The price ID to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If the specified price ID does not exist in the
	 *                              database.
	 */

	public static void checkPriceExists(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT price_id FROM prices WHERE is_active = 1 AND price_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Price id does not exists");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

}
