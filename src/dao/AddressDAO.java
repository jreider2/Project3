/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import customer.Address;

/**
 * @author julianaReider with code based off of Nathneal's example code
 *
 */
public class AddressDAO {

	/**
	 * 
	 */
	public AddressDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * New address ID will be returned.
	 * 
	 * Note to partner: this method is considered complete 
	 */
	/** Note to partner: this method is considered complete */
	public String addAddress(String aptNumber, String streetAndStreetNum, String city, String state, String zipcode ) {
		Integer resultKey = -1;
		
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			
			String insertQuery = "INSERT INTO Address (AddressID,Street,AptNo,City,ZipCode,State)"
					+ "VALUES(0,'"+streetAndStreetNum+"','"+aptNumber+"','"+city+"','"+zipcode+"','"+state+"')";
			insertStatement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
		
			//Grab the generated key
			ResultSet rs = insertStatement.getGeneratedKeys();
	        if (rs.next()){
	            resultKey = rs.getInt(1);
	        }
	        rs.close();
	        insertStatement.close();

	       return resultKey.toString();
	        
			
		}catch(SQLException se) {
			se.printStackTrace();
			//TODO resultKey = -1  ??
			
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		return "-1";
	}

}
