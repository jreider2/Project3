/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import dao.DBConnect;
import partner.Partner;
import product.Product;

/**
 * @author julianareider Based off example code by Nathnael Alemu
 *
 */
public class PartnerDAO {

	/**
	 * 
	 */
	public PartnerDAO() {
		// TODO Auto-generated constructor stub
	}

	public Set<Partner> getAllPartners() {
		// TODO Auto-generated method stub
		return null;
	}

	/** Note to partner: I have NOT completed this method
	 * The Partner returned is missing the Product objects in their inventory!!
	 * otherwise the functionality is all there
	 *  */
	public Partner getPartner(String id) {
		String companyName = "";
		//ArrayList<Product> products = new ArrayList<>();  
		String userName = "";
		String password = "";
		
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Partner where PartnerID='" + id +"'";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			resultSet.next();
			
			companyName = resultSet.getString("companyName");
			userName = resultSet.getString("userName");
			password = resultSet.getString("password");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}

		
		Partner partner = new Partner();
		partner.setId(id);
		partner.setCompanyName(companyName);
		partner.setUserName(userName);
		partner.setPassword(password);
		//partner.setProducts(products); //TODO make it so it's not empty 
		
		return partner;
	}

	/**
	 * @return partner the generated Partner object, which included the generated Primary Key ID from the Database 
	 */
	/** Note to partner: I have NOT completed this method 
	 * missing same aspect as method above (otherwise solid)
	 * */
	public Partner addPartner(String companyName, String userName, String password, int addressID) {
		Integer resultKey = -1;
		
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			
			String insertQuery = "INSERT INTO Partner (PartnerID,CompanyName,AddressID,password,username)"
					+ "VALUES(0,'" + companyName + "'," + addressID + ",'" + password + "','" + userName + "')";
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
		
		Partner partner = new Partner();
		partner.setId(resultKey.toString());
		partner.setCompanyName(companyName);
		ArrayList<Product> products = new ArrayList<>(); //TODO
		partner.setProducts(products);	
		partner.setUserName(userName);
		partner.setPassword(password);
		
		return partner;
	}

	
	/** Note to partner: I have NOT completed this method */
	public void updatePartner(String id, Product product) {
		
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement updateStatement = connection.createStatement();
			
			//TODO edit
			//String updateQuery = "UPDATE partner SET products='"+product+"' WHERE PartnerID='"+id+"')";
			//updateStatement.executeUpdate(updateQuery);		
			
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

	
	/** Note to partner: I have NOT checked this method for completeness but i think it's almost there*/
	public void deletePartner(String id) {
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement deleteStatement = connection.createStatement();
			
			String deleteQuery = "DELETE FROM Partner WHERE PartnerID='"+id+"')"; 
			deleteStatement.executeUpdate(deleteQuery);	
			
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

}
