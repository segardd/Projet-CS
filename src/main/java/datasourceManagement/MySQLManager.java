package datasourceManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLManager {
	private static MySQLManager instance;
	private Connection conn = null;
	
	private MySQLManager()
	{
		connexion();
	}
	
	public static synchronized MySQLManager getInstance() {
		
		if (instance == null) {
			instance = new MySQLManager();
		}
		return instance;		
	}
	
	public void setValue(Connection value) {
		conn = value;
	}
	
	public Connection getValue() {
		return conn;
	}
	
	private void connexion()
	{
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

          Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heptathlon", "root", "");
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
	}
	

	public ResultSet getData(String req)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(req);
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	
	public int setData(String req)
	{
		int res=0;
		Statement stmt = null;
		ResultSet rs;

		try {
		    stmt = conn.createStatement();
		    res = stmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
		    rs = stmt.getGeneratedKeys();
		    if( rs.next() ) {
		    	res = rs.getInt(1);
		    }
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { }
		        stmt = null;
		    }
		}
		return res;
	}
}

