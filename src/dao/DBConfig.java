/**
 * 
 */
package dao;

/**
 * @author Nathneal A
 *
 */
public class DBConfig {
	private String username;
	private String password;
	private String databaseURL;
	private String databaseName;

	/**
	/** Note to partner: this class is considered complete 
	 */
	public DBConfig() {
		username="Comp433JrAr";
		password="comp433!";
		databaseURL="jdbc:mysql://ecommercedb.c8swbrmnlwkg.us-east-2.rds.amazonaws.com";
		databaseName="eCommerceDB";
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getDatabaseURL() {
		return databaseURL+"/"+getDatabaseName();
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

}
