import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

public class JDBC_Test {
	
	// run as is
	// fix any errors by changing db and changing java code
		// add db field height that is an int
		// add an autoincrementing primary key
	
	// delete record 7 on command line
	// run again
	// look at table data on command line
	
	// add a new statement of type default and a new resultset
	// use these to make a SELECT statement that has a subquery and print out results
	
	// insert a new row using INSERT with executeUpdate(query)
	// delete a row using DELETE with executeUpdate(query)
		
	public static void main(String[] args) {
		
		// db parameters
		String username = "root";
		String password = "buddycat";
		String db = "army";
		String url = "jdbc:mysql://localhost:3306/"+db;
		Connection conn = null;
		
		// initialization
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		} catch(ClassNotFoundException e) {
			System.out.println("MySQL Driver Not Found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// other parameters
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Statement Creation Failed");
			e.printStackTrace();
		}
		
		// formulating query
		String tablename = "army_soldiers";
		String fieldname = "given_name";
		String query = "SELECT " + fieldname + " FROM " + tablename + ";";
		
		// getting data
		try {
			rs = stmt.executeQuery(query);
			
			// processing data
			while (rs.next()) {
				System.out.print(rs.getString(1) + ", ");
			}
			
		} catch (SQLException e) {
			System.out.println("There was a problem with executing query:"+query);
			e.printStackTrace();
		}
		
		// formulating query
		fieldname = "family_name";
		query = "UPDATE " + tablename + " SET " + fieldname + " = 'Luskin' WHERE " + fieldname + " = 'Byrne';"; 

		// modifying data
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e1) {
			System.out.println("There was a problem with executing query:"+query);
			e1.printStackTrace();
		}
		
		// formulating prepared statement
		fieldname = "given_name";
		try {
			ps = conn.prepareStatement("UPDATE " + tablename + " SET " + fieldname + " = ? WHERE "
					+ fieldname + " = ?");
			ps.setString(1, "Gary");
			ps.setString(2, "Bob");
			ps.executeUpdate();
		} catch (SQLException e2) {
			System.out.println("There was a problem with executing prepared statement="+ps);
			e2.printStackTrace();
		} 
		
		// updating data in result set
		query = "SELECT * FROM " + tablename + ";";
		try {
			stmt2 = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt2.executeQuery(query);
			
			rs.absolute(5); // moves the cursor to the fifth row of rs
		    rs.updateString("family_name", "Cuckovich"); 
		    rs.updateRow(); // updates the row in the data source
			
			// processing data
			while (rs.next()) {
				System.out.print(
						rs.getString(1) + " " + 
						rs.getString(2) + ", ");
			}
			
		} catch (SQLException e) {
			System.out.println("There was a problem with executing query:"+query);
			e.printStackTrace();
		}
		
		// inserting data in result set
		try {
			rs.beforeFirst();
			rs.moveToInsertRow(); // moves cursor to the insert row
			rs.updateDate(1, new Date(1978,04,11));
			rs.updateString(2, "Beth");
			rs.updateString(3, "Goodwin");
			rs.updateString(4, "Female");
			rs.updateString(5, "Translator");
			rs.updateInt(6,166); 
			rs.insertRow();
			rs.moveToCurrentRow();
			
			// processing data
			while (rs.next()) {
				System.out.print(
						rs.getString(1) + " " + 
						rs.getString(2) + " " + 
						rs.getString(3) + " " + 
						rs.getString(4) + " " + 
						rs.getString(5) +  ", ");
			}
			
		} catch (SQLException e) {
			System.out.println("There was a problem inserting into the resultset");
			e.printStackTrace();
		}
		

		
		// closing resources
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem Closing DB Resources");
			e.printStackTrace();
		}
	}
}
