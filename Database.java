import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
	Connection connection = null;
	
	public Database() {
		connectDB();
	}
	
	private void connectDB() {
		String URL = "jdbc:mysql://localhost:3306/speedify";
		String username = "hanan";
		String password ="123456";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,username,password);
			System.out.println("Connection established.");
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Please try again.");
		}
	}
	public boolean verifyLogin(String username, String password) {
	    
	    try {
	        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            String storedPassword = rs.getString("Password");
	            if (password.equals(storedPassword)) {
	                return true;
	            }
	        }

	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Connection Failed. Check if Apache and MySql features are opened in Xampp Control Panel."
	        		+ "\nRestart the application after starting the services.");
	    }
	    return false;
	}

	public boolean signup(String fname, String lname, String CNIC, String Phone, String Address, String Email, String username, String password) {
	    try 
	    {
	        String query = "INSERT INTO signup (fname, lname, CNIC, Phone, Address, Email, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, fname);
	        preparedStatement.setString(2, lname);
	        preparedStatement.setString(3, CNIC);
	        preparedStatement.setString(4, Phone);
	        preparedStatement.setString(5, Address);
	        preparedStatement.setString(6, Email);
	        preparedStatement.setString(7, username);
	        preparedStatement.setString(8, password);

	        int rowsAffected = preparedStatement.executeUpdate();
	        System.out.println("Rows affected: " + rowsAffected);
	        return rowsAffected > 0;
	        
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.out.println("Error during signup: " + e.getMessage());
	    }
	    return false;
	}

}
