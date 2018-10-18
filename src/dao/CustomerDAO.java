/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import customer.Customer;

/**
 * @author Based off example code by Nathnael Alemu
 */
public class CustomerDAO {

	private OrderDAO orderDAO;
	private AddressDAO addressDAO;
	private CreditCardDAO creditCardDAO;
	private CustomerDAO custDAO;
	
	/**
	 * 
	 */
	public CustomerDAO() {
		orderDAO = new OrderDAO();
		addressDAO = new AddressDAO();
		creditCardDAO = new CreditCardDAO();
		
	}

	public Set<Customer> getAllCustomers() {
		Connection connection = DBConnect.getDatabaseConnection();
		Set<Customer> customers = new HashSet<>();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Customer";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				String employeeID = resultSet.getString("CustomerID");
				Customer emp = getCustomer(employeeID);
				if(emp != null) {
					customers.add(emp);
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
		
		return customers;
		
	}

	/** Note to partner: I have NOT completed this method */
	public Customer getCustomer(String id) {
		String firstName = "";
//		String lastName = "";
//		String address = "";
//		String creditCard = "";
//		String phone = "";
//		String userName = "";
//		String password = "";
//		ArrayList<Order> orders = new ArrayList<>();
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Customer where CustomerID='" + id +"'";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			resultSet.next();
			
			firstName = resultSet.getString("FName");
//			lastName = resultSet.getString("lastName");
//			address = resultSet.getString("lastName"); //TODO update this code: Address has it's own table . access AddressDAO object (use attribute line 21) 
//			creditCard = resultSet.getString("creditCard"); //TODO same as Address. (attribute line 22) 
//			phone = resultSet.getString("phone");
//			userName = resultSet.getString("userName");
//			password = resultSet.getString("password");

//			orders = orderDAO.getAllOrders(); //TODO fix me (make sure function returns something).
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
//		customer.setLastName(lastName);
//		//TODO customer.setAddress(address); update
//		//TODO customer.setCard(creditCard); update
//		customer.setPhone(phone);
//		customer.setUserName(userName);
//		customer.setPassword(password);
//		customer.setOrders(orders);
		
		return customer;
//		return null;
	}

	public boolean addCustomer(String firstName, String lastName, String phone, String username, String password, String email, String addressID) {
		Connection conn = DBConnect.getDatabaseConnection();
		
		try {
			Statement insert = conn.createStatement();
			
			String query = "INSERT INTO Customer VALUES (0, '" + firstName + "', '" + lastName + 
					"', '" + phone + "', '" + username + "', '" + password + "', '" + email + "', " + addressID + ")";
			
			insert.executeUpdate(query);
			insert.close();
			return true;	
			
		} catch (SQLException e) { e.printStackTrace(); return false; }
		
	}

	public void updateCustomer(String id) {
		// TODO Auto-generated method stub
		
	}

	/** Note to partner: I have NOT completed this method */
	public void deleteCustomer(String id) {
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement deleteStatement = connection.createStatement();
			
			String deleteQuery = "DELETE FROM Customer WHERE CustomerID='"+id+"')";
			deleteStatement.executeUpdate(deleteQuery);	
			
			//TODO delete all DB table entries related to this entry:
			//address
			//credit card
			//All the orders?
			
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
