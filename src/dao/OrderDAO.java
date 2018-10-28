/**
 * 
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customer.CreditCard;
import order.Order;
import order.OrderedItem;
import partner.Partner;
import product.Product;
import product.ProductManager;
import dao.PartnerDAO;
import net.shibboleth.utilities.java.support.collection.Pair;

/**
 * @author julianareider
 *
 */
public class OrderDAO {
	
	private ProductManager pm = new ProductManager();

	/**
	 * 
	 */
	public OrderDAO() {	}

	public ArrayList<Order> getAllOrders(String customerNo) {
		Connection conn = DBConnect.getDatabaseConnection();
		
		ArrayList<Order> arOrd = new ArrayList<>();
		
		try {
			Statement select = conn.createStatement();
			
			String query = "SELECT * FROM Orders JOIN OrderList on Orders.OrderID = OrderList.OrderID Where Orders.customerID=" + "'" + customerNo + "'";  
			ResultSet rs = select.executeQuery(query);
			
			ArrayList<Product> productsOnOrder = new ArrayList<>();
			String previousRowOrderID = "";
			Order o = new Order();
			while (rs.next()) {
				
				//only start a new order object if we have added all products on that order to the order object.
				if (previousRowOrderID != rs.getString("OrderID")) {
					o = new Order();
					o.setId(rs.getString("OrderID"));
					o.setCreditCardNo(rs.getString("CreditCardNo"));
					o.setCustomerID(rs.getString("CustomerID"));
					o.setOrderTotal(new BigDecimal(rs.getShort("OrderTotal")));
					productsOnOrder.add(pm.getProduct(rs.getString("ProductID")));
					
					o.setProducts(productsOnOrder);
				} else {
					//there are still items on the order to be added to the order object. (one row is selected at a time)
					productsOnOrder.add(pm.getProduct(rs.getString("ProductID")));
					o.setProducts(productsOnOrder);
				}
				
				previousRowOrderID = o.getId();
				
				//only add the order to the orderlist returned when we know that we have added all products on the order to the object.
				if (previousRowOrderID != rs.getString("OrderID")) {
					arOrd.add(o);
				}
			}
			
			return arOrd;
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return null;
	}

	
	/** Note to partner: this method is considered complete */
	public String getOrderStatus(String id) {
		String orderStatus = "";
		
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Orders where OrderID='" + id +"'";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			resultSet.next();
			
			orderStatus = resultSet.getString("OrderStatus");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		return orderStatus;
	}
	
	/** Note to partner: I have NOT completed this method */ // - Completed
	public Order getOrder(String id) {		
		Connection conn = DBConnect.getDatabaseConnection();

		Order ord = new Order();
		try {
			Statement select = conn.createStatement();
			
			String query = "SELECT * FROM Orders JOIN OrderList on Orders.OrderID = OrderList.OrderID Where Orders.Orderid=" + "'" + id + "'";  
			ResultSet rs = select.executeQuery(query);
			rs.next();

			ord.setId(rs.getString("OrderID"));
			ord.setOrderStatus(rs.getString("orderStatus"));
			ord.setCustomerID(rs.getString("CustomerID"));
			ord.setOrderTotal(new BigDecimal(rs.getString("OrderTotal")));
			ord.setCreditCardNo(rs.getString("CreditCardNo"));
			ArrayList<Product> prodsOnOrder = new ArrayList<>();
			prodsOnOrder.add(pm.getProduct(rs.getString("ProductID")));
			
			//get all products if multiple rows are returned.
			while(rs.next()) {
				prodsOnOrder.add(pm.getProduct(rs.getString("ProductID")));
			}
			ord.setProducts(prodsOnOrder);
			
			return ord;
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return null;
		
		
	}
	
	
	/** Note to partner: this method is considered complete */
	public Order placeOrder(String customerID, ArrayList<OrderedItem> productsOnOrder, String ccNo, BigDecimal orderTotal) {
		Integer resultKey = -1;
		Connection connection = DBConnect.getDatabaseConnection();
		String status = "ordered";
		Statement queryStatement = connection.createStatement();
		//Create new Order with appropriate attributes
		Order order = new Order();
		//add order to orders table
		try {
			
			
			String insertQuery = "INSERT INTO Orders VALUES (0,'"+ status +"','" + customerID + "', '" + ccNo + "', '" + orderTotal.toString() + "')";
			queryStatement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
				
			//Grab the generated key//get orderID
			ResultSet rs = queryStatement.getGeneratedKeys();
	        if (rs.next()){
	            resultKey = rs.getInt(1);
	        }
	        rs.close();
	        
	        //add order and Products to product table -- for each product ID
	        for (OrderedItem oI : productsOnOrder) {
				
				insertQuery = "INSERT INTO OrderList (OrderID,ProductID,Qty)"
						+ "VALUES('" + resultKey + "', '" + oI.getProductID() + "', '" + oI.getQtyOnOrder() + "')";
				queryStatement.executeUpdate(insertQuery);
	        }
	        
	        //add record to payment table to record that the order was paid for.
	        insertQuery = "INSERT INTO Payment VALUES (0, '" + ccNo + "', '" + resultKey.toString() + "', '" + customerID + "')";
	        queryStatement.executeUpdate(insertQuery);
	        
	        order.setId(Integer.toString(resultKey));
			order.setOrderStatus(status);
			order.setCreditCardNo(ccNo);
			
			String query = "SELECT OrderList.OrderID as OrderID, Product.ProductID as ProductID, OrderList.Qty as qty, Product.Price as Price FROM eCommerceDB.OrderList JOIN Product on Product.ProductID = OrderList.ProductID where OrderList.OrderID=" + "'" + Integer.toString(resultKey) + "'";  

			ArrayList<OrderedItem> itemsOnOrder = new ArrayList<>();
			rs = queryStatement.executeQuery(query);
			while(rs.next()) {
				OrderedItem oI = new OrderedItem();
				oI.setProductID(rs.getString("ProductID"));
				oI.setProductPrice("Price");
				oI.setQtyOnOrder("qty");
				itemsOnOrder.add(oI);
			}
			
			order.setProducts(itemsOnOrder);
			
	        queryStatement.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		//return that new order object
		return order;
	}

	
	/** Note to partner: this method is considered complete */
	public boolean updateOrderStatus(String orderID, String newStatus) {
		boolean isShipped = false;

		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement updateStatement = connection.createStatement();
			
			String updateQuery = "UPDATE Orders SET OrderStatus='" + newStatus + "' WHERE OrderID='" + orderID + "'";
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
		
		//if successful than update isShipped
		isShipped = true;
		
		return isShipped;
	}


}
