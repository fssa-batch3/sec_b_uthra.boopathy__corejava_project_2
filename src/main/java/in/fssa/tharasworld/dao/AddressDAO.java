package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;

public class AddressDAO {

	/**
	 * Creates a new address entry in the database.
	 *
	 * This method inserts a new address record into the 'address' table with the
	 * provided information. The 'set_as_default' column is set to 'false' by
	 * default.
	 *
	 * @param newAddress An instance of the AddressEntity class representing the new
	 *                   address.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void create(AddressEntity newAddress) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO address (name, address, state, pincode, set_as_default, user_id) VALUES (?, ?, ?, ?, false, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newAddress.getName());
			ps.setString(2, newAddress.getAddress());
			ps.setString(3, newAddress.getState());
			ps.setInt(4, newAddress.getPincode());
//			ps.setBoolean(5, newAddress.isSetAsDefaultStatus());
			ps.setInt(5, newAddress.getUserId());

			ps.executeUpdate();

			Logger.info("Address has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * Updates an existing address in the database.
	 *
	 * This method updates an existing address record in the 'address' table with
	 * the provided information, based on the given address ID. The 'is_active'
	 * column is set to '1' (active) for the address being updated.
	 *
	 * @param id             The unique identifier of the address to be updated.
	 * @param updatedAddress An instance of the AddressEntity class representing the
	 *                       updated address details.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void update(int id, AddressEntity updatedAddress) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE address SET name=?, address=?, state=?, pincode=? WHERE is_active=1 AND address_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedAddress.getName());

			ps.setString(2, updatedAddress.getAddress());

			ps.setString(3, updatedAddress.getState());

			ps.setInt(4, updatedAddress.getPincode());

			ps.setInt(5, id);

			ps.executeUpdate();

			Logger.info("Address has been updated successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * Sets an address as the default address in the database.
	 *
	 * This method updates an address record in the 'address' table to set it as the
	 * default address. The 'set_as_default' column is set to '1' (true) for the
	 * specified address ID while ensuring that the address is marked as active
	 * ('is_active' = 1).
	 *
	 * @param id The unique identifier of the address to be set as the default.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void setAsDefaultAddress(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE address SET set_as_default = 1 WHERE is_active=1 AND address_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			Logger.info("Address has been set as default successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * Sets the 'set_as_default' flag to false for addresses associated with a
	 * specific user.
	 *
	 * This method updates the 'set_as_default' column to '0' (false) for all
	 * addresses belonging to a specified user, ensuring that they are no longer
	 * marked as default addresses while ensuring that the addresses are marked as
	 * active ('is_active' = 1).
	 *
	 * @param id The unique identifier of the user whose addresses should have
	 *           'set_as_default' set to false.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void setAsDefaultAddressIntoFalse(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE address SET set_as_default = 0 WHERE is_active=1 AND user_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * Marks an address as inactive in the database.
	 *
	 * This method updates the 'is_active' column to '0' (false) for an address with
	 * the specified address ID, effectively marking it as inactive and not
	 * available for further use.
	 *
	 * @param id The unique identifier of the address to be marked as inactive.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE address SET is_active = 0 WHERE is_active = 1 AND address_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			Logger.info("Address has been deleted successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves the default address associated with a specific user from the
	 * database.
	 *
	 * This method queries the 'address' table to find the default address (where
	 * 'set_as_default' is true) for the specified user, and returns the details as
	 * an AddressEntity object.
	 *
	 * @param id The unique identifier of the user for whom the default address
	 *           should be retrieved.
	 * @return An AddressEntity object representing the default address, or null if
	 *         none is found.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public AddressEntity findByDefault(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AddressEntity address = null;

		try {

			String query = "SELECT address_id, name, address, state, pincode, set_as_default FROM address WHERE is_active = 1 AND user_id = ? AND set_as_default=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				address = new AddressEntity();
				address.setAddressId(rs.getInt("address_id"));
				address.setName(rs.getString("name"));
				address.setAddress(rs.getString("address"));
				address.setState(rs.getString("state"));
				address.setPincode(rs.getInt("pincode"));
				address.setSetAsDefaultStatus(rs.getBoolean("set_as_default"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return address;

	}

	/**
	 * Retrieves an address by its unique identifier from the database.
	 *
	 * This method queries the 'address' table to find an active address with the
	 * specified address ID, and returns the details as an AddressEntity object.
	 *
	 * @param id The unique identifier of the address to be retrieved.
	 * @return An AddressEntity object representing the retrieved address, or null
	 *         if not found.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public AddressEntity findById(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AddressEntity address = null;

		try {

			String query = "SELECT address_id, name, address, state, pincode FROM address WHERE address_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				address = new AddressEntity();
				address.setAddressId(rs.getInt("address_id"));
				address.setName(rs.getString("name"));
				address.setAddress(rs.getString("address"));
				address.setState(rs.getString("state"));
				address.setPincode(rs.getInt("pincode"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return address;

	}

	/**
	 * Retrieves a list of addresses associated with a specific user from the
	 * database.
	 *
	 * This method queries the 'address' table to find all active addresses
	 * belonging to the specified user, and returns the details as a list of
	 * AddressEntity objects.
	 *
	 * @param id The unique identifier of the user for whom the addresses should be
	 *           retrieved.
	 * @return A List of AddressEntity objects representing the addresses associated
	 *         with the user.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public List<AddressEntity> findByUserId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<AddressEntity> addressList = new ArrayList<>();

		try {

			String query = "SELECT address_id, name, address, state, pincode FROM address WHERE is_active = 1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				AddressEntity address = new AddressEntity();
				address.setAddressId(rs.getInt("address_id"));
				address.setName(rs.getString("name"));
				address.setAddress(rs.getString("address"));
				address.setState(rs.getString("state"));
				address.setPincode(rs.getInt("pincode"));

				addressList.add(address);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return addressList;

	}

	/**
	 * Checks if an address already exists for a specific user in the database.
	 *
	 * This method queries the 'address' table to determine if an address with the
	 * given details (address and user ID) already exists and is marked as active
	 * ('is_active' = 1) in the database. If an existing address is found, it throws
	 * a ValidationException with the message "Address already exists."
	 *
	 * @param address The address string to be checked for existence.
	 * @param userId  The unique identifier of the user for whom the address
	 *                existence should be checked.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If the address already exists for the specified
	 *                              user.
	 */

	public void checkAddressExist(String address, int userId) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		AddressEntity user = null;

		try {

			String query = "SELECT name, address, state, pincode, set_as_default, user_id FROM address WHERE is_active=1 AND user_id=? AND address=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setString(2, address);
			rs = ps.executeQuery();

			if (rs.next()) {
				throw new ValidationException("Address already exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if an address with the specified address ID exists in the database.
	 *
	 * This method queries the 'address' table to determine if an address with the
	 * given address ID exists and is marked as active ('is_active' = 1) in the
	 * database. If the address with the provided ID is not found, it throws a
	 * ValidationException with the message "Address id does not exist."
	 *
	 * @param id The unique identifier of the address to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If the address with the specified address ID
	 *                              does not exist.
	 */

	public void checkAddressExistWithAddId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		AddressEntity user = null;

		try {

			String query = "SELECT name, address, state, pincode, set_as_default, user_id FROM address WHERE is_active=1 AND address_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Address id does not exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}
	
	public void checkAddressExistWithAddressId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		AddressEntity user = null;

		try {

			String query = "SELECT name, address, state, pincode, set_as_default, user_id FROM address WHERE address_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Address id does not exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}


}
