/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import dao.DBConnect;
import partner.Partner;
import product.Product;

/**
 * @author Based off example code by Nathnael Alemu
 *
 */
public class ProductDAO {
	
	private PartnerDAO partnerDAO;

	/**
	 * 
	 */
	public ProductDAO() {
		partnerDAO = new PartnerDAO();
	}
	
	/**
	 * Returns null if no products match the search 
	 * Else returns the list of products. 
	 */
	public ArrayList<Product> performProductSearch(String searchTerm) {
		
		Connection connection = DBConnect.getDatabaseConnection();
		ArrayList<Product> products = new ArrayList<>();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String searchQuery = "SELECT * FROM Product WHERE Description LIKE '%" + searchTerm + "%'";
			ResultSet resultSet = selectStatement.executeQuery(searchQuery);
			resultSet.next();
			
			while(resultSet.next()) {
				String productID = resultSet.getString("ProductID"); //TODO check this value
				Product prod = getProduct(productID);
				if(prod != null) {
					products.add(prod);
				}
			}
			return products;
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		return null;
	}
	
	public ArrayList<Product> getProductList(ArrayList<OrderedItem> productIDs) {
		ArrayList<Product> queriedProducts = new ArrayList<Product>();
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Product where ProductID in (";
			
			for (int i = 0; i < productIDs.size(); i++) {
				if(i < productIDs.size() - 1) {
					selectQuery += "'" + productIDs.get(i) + "',";
				} else {
					selectQuery += "'" + productIDs.get(i) + "')";
				}
			}
			
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			
			Product TempProd;
			while(resultSet.next()) {
				TempProd.setId(resultSet.getString("ProductID"));
				TempProd.setName(resultSet.getString("Name"));
				TempProd.setDescription(resultSet.getString("Description"));
				TempProd.setPrice(Double.valueOf(resultSet.getString("Price")));
				TempProd.setQuantityOnOrder(quantityOnOrder);
				name = resultSet.getString("name");
				description  = resultSet.getString("description");
				price = resultSet.getString("price");
				partnerID = resultSet.getString("PartnerID");
			}
			
			return queriedProducts;
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}

	/** Note to partner: this method is considered complete */
	public Product getProduct(String id) {
		String name = "";
		String description ="";
		String price = "";
		String partnerID = "";
		
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Product where ProductID='" + id +"'";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			resultSet.next();
			
			name = resultSet.getString("name");
			description  = resultSet.getString("description");
			price = resultSet.getString("price");
			partnerID = resultSet.getString("PartnerID");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(Double.parseDouble(price));
		product.setProductOwner(partnerDAO.getPartner(partnerID));
		return product;
	}
	
	public Set<Product> getAllProducts() {
		
		Connection connection = DBConnect.getDatabaseConnection();
		Set<Product> products = new HashSet<>();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Product";
			
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				String productID = resultSet.getString("ProductID"); 
				Product prod = getProduct(productID);
				if(prod != null) {
					products.add(prod);
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		return products;
	}


	/** Note to partner: this method is considered complete */
	public Product addProduct(String name, String description, double price, String partnerID) {
		Integer resultKey = -1;
		Connection connection = DBConnect.getDatabaseConnection();
		int partID = Integer.parseInt(partnerID);
		
		try {
			Statement insertStatement = connection.createStatement();
		 
			String insertQuery = "INSERT INTO Product (ProductID,Name,Description,Price,PartnerID)"
					+ "VALUES(0,'"+name+"','"+description+"','"+price+"','"  + partnerID + "')";
			insertStatement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
		
			//Grab the generated key
			ResultSet rs = insertStatement.getGeneratedKeys();
	        if (rs.next()){
	            resultKey = rs.getInt(1);
	        }
	        rs.close();
	        insertStatement.close();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		Product product = new Product();
		product.setId(resultKey.toString());
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setProductOwner(partnerDAO.getPartner(partnerID));
		
		return product;
	}

	/** Note to partner: I have NOT completed this method */
	public void updateProduct(int id, int price) {
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement updateStatement = connection.createStatement();
			
			String updateQuery = "UPDATE Product SET Price='"+Integer.toString(price)+"' WHERE ProductID='"+id+"')";
			updateStatement.executeUpdate(updateQuery);		
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	public void deleteProduct(String id) {
		// TODO Auto-generated method stub
		
	}

}
