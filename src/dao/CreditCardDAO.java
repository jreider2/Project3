package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customer.CreditCard;


public class CreditCardDAO {
	
	public CreditCardDAO() {
		
	}
	
	public boolean enterCreditCard(CreditCard ccToEnter) {
		Connection conn = DBConnect.getDatabaseConnection();
		
		try {
			Statement insert = conn.createStatement();
			String query = "INSERT INTO CreditCard VALUES (" + ccToEnter.getCreditCardNumber() + ", '" + ccToEnter.getExpDate() + 
					"', '" + ccToEnter.getCardHolderName() + "', " + ccToEnter.getSecurityCode() + ", " + ccToEnter.getCustomerID() + ")";
			
			insert.executeUpdate(query);
			insert.close();
			return true;	
			
		} catch (SQLException e) { e.printStackTrace(); return false; }
		
	}
	
	public boolean removeCreditCard(String ccNoToDelete) {
		Connection conn = DBConnect.getDatabaseConnection();
		
		try {
			Statement delete = conn.createStatement();
			String query = "DELETE FROM CreditCard WHERE ccNo = " + ccNoToDelete;
			
			delete.executeUpdate(query);
			return true;	
			
		} catch (SQLException e) { e.printStackTrace(); return false; }
	}
	
	public CreditCard getCreditCardInfo(String creditCardNo) {
		Connection conn = DBConnect.getDatabaseConnection();

		CreditCard cc = new CreditCard();
		try {
			Statement select = conn.createStatement();
			
			String query = "SELECT * FROM CreditCard WHERE ccNo=" + "'" + creditCardNo + "'";  
			ResultSet rs = select.executeQuery(query);
			rs.next();

			cc.setCreditCardNumber(rs.getString("ccNo"));
			cc.setExpDate(rs.getString("ExpirationDate"));
			cc.setcardHolderName(rs.getString("ccHolderFName"));
			cc.setSecurityCode(rs.getString("ccSecurityCode"));
			cc.setCustomerID(rs.getString("CustomerID"));
			
			return cc;
			
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
	
	public ArrayList<CreditCard> getAllCreditCards(String customerNo){
		Connection conn = DBConnect.getDatabaseConnection();
		
		ArrayList<CreditCard> arCC = new ArrayList<>();
		
		try {
			Statement select = conn.createStatement();
			
			String query = "SELECT * FROM CreditCard WHERE CustomerID=" + "'" + customerNo + "'";  
			ResultSet rs = select.executeQuery(query);

			while (rs.next()) {
				String ccId = rs.getString("ccNo");
				arCC.add(getCreditCardInfo(ccId));
			}
			
			return arCC;
			
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
	
}
