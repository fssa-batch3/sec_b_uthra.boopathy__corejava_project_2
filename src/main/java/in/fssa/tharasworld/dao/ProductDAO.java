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
import in.fssa.tharasworld.util.Logger;

public class ProductDAO {

	/**
	 * Retrieves a set of all active product details from the database.
	 *
	 * This method queries the 'products' table to retrieve all product details that
	 * are marked as active ('is_active' = 1) in the database and returns them as a
	 * set of ProductDetailDTO objects.
	 *
	 * @return A set of ProductDetailDTO objects representing all active product
	 *         details in the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
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

			while (rs.next()) {

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

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productList;

	}

	/**
	 * Creates a new product with the provided details and returns its generated
	 * product ID.
	 *
	 * This method inserts a new product into the 'products' table in the database
	 * with the provided details, including name, image URL, description, seller ID,
	 * and type ID. It also retrieves and returns the generated product ID after the
	 * insert operation is successful.
	 *
	 * @param newProduct The ProductDetailDTO object containing the details of the
	 *                   new product.
	 * @return The generated product ID of the newly created product.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
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

			if (rs.next()) {
				productId = rs.getInt(1);
			}

			System.out.println("Product  has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}

		return productId;
	}

	/**
	 * Updates a product's information in the database with the provided details.
	 *
	 * This method updates the information of a product in the 'products' table in
	 * the database with the provided details, including name, image URL,
	 * description, and type ID. The update is based on the product's ID (pdt_id).
	 * Only the non-null and non-negative values in the updatedProduct object are
	 * applied to the database. The 'is_active' column is used to filter active
	 * products.
	 *
	 * @param id             The ID of the product to be updated.
	 * @param updatedProduct The ProductEntity object containing the updated product
	 *                       details.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
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

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(conn, ps);

		}

	}

	/**
	 * Marks a product as inactive in the database.
	 *
	 * This method updates the 'is_active' column of a product in the 'products'
	 * table in the database to mark it as inactive. The product is identified by
	 * its ID (pdt_id).
	 *
	 * @param id The ID of the product to be marked as inactive.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
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

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Finds the category ID by its name in the database.
	 *
	 * This method retrieves the ID of a category from the 'categories' table in the
	 * database based on its name.
	 *
	 * @param name The name of the category to search for.
	 * @return The ID of the category with the specified name, or 0 if not found.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

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

			while (rs.next()) {

				category = new CategoryEntity();
				category.setCateId(rs.getInt("cate_id"));

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return categoryId = category.getCateId();

	}

	/**
	 * Retrieves a set of ProductDetailDTO objects by category ID using INNER JOIN.
	 *
	 * This method retrieves products and their details by performing an INNER JOIN
	 * between the "types" and "products" tables based on the given category ID.
	 *
	 * @param id The ID of the category to search for products.
	 * @return A set of ProductDetailDTO objects representing the products in the
	 *         specified category.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public Set<ProductDetailDTO> findByCategoryId(int id) throws PersistenceException {
		Set<ProductDetailDTO> productList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT p.pdt_id, p.name, p.image_url, p.description, p.seller_id, p.type_id "
					+ "FROM products AS p " + "INNER JOIN types AS t ON p.type_id = t.type_id "
					+ "WHERE p.is_active = 1 AND t.cate_id = ?";
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

				productList.add(product);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productList;
	}

	/**
	 * Retrieves a set of ProductDetailDTO objects by type ID using INNER JOIN.
	 *
	 * This method retrieves products and their details by performing an INNER JOIN
	 * with the "types" table based on the given type ID.
	 *
	 * @param id The ID of the type to search for products.
	 * @return A set of ProductDetailDTO objects representing the products of the
	 *         specified type.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

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
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productList;
	}

	/**
	 * Retrieves the type ID by type name.
	 *
	 * This method retrieves the type ID associated with a given type name.
	 *
	 * @param name The name of the type to search for.
	 * @return The type ID if the type name exists; otherwise, 0.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

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
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return typeId = type.getTypeId();
	}

	/**
	 * Retrieves detailed product information, including associated price details,
	 * for a given product ID.
	 *
	 * @param id The unique identifier of the product to retrieve.
	 * @return A ProductDetailDTO object containing detailed product information.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
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
				pdt.setSellerId(rs.getInt("seller_id"));

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

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return pdt;

	}

	/**
	 * Retrieves a set of detailed product information for all products associated
	 * with a specific seller.
	 *
	 * @param id The unique identifier of the seller whose products are to be
	 *           retrieved.
	 * @return A Set of ProductDetailDTO objects containing detailed product
	 *         information for the seller's products.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 */

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

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return pdt;

	}

	/**
	 * Checks the existence of a product with the specified product ID in the
	 * database.
	 *
	 * @param id The product ID to be checked for existence.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 * @throws ValidationException  If the product with the given ID does not exist
	 *                              in the database.
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

			if (!rs.next()) {

				throw new ValidationException("Product does not exixts");

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

}
