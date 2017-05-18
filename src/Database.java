import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.xml.bind.DatatypeConverter;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tryToLogin(connectToDatabase(), "Schunk", "test");
	}
	
	public static Connection connectToDatabase(){
		Connection connect = null;
		try {
			//try to connect database
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/NCBS-Chat-DB", "root", "");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/NCBSChatDB", "Niklas", "1234");
			System.out.println("connect to database was successful");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
	}
	
	public static String generateHash(String text){
		//String to sha256 hash
		
		MessageDigest digest = null;
		
		try {
			//return the specific digest algorithm
			digest = MessageDigest.getInstance("SHA-256");
						
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//converts string to byte array
		byte[] bytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		
		//converts the byte array to string hash
		String hash = DatatypeConverter.printHexBinary(bytes);
		
		return hash.toLowerCase();
	}
	 
	public static boolean tryToLogin(Connection connect, String username, String passwd){
		PreparedStatement myStmt;
		
		try {
			//create MySQL query
			String sql = "select * from user where username = ?;";
			myStmt = connect.prepareStatement(sql);
			myStmt.setString(1, username);
			
			//get passwd from database
			ResultSet result = myStmt.executeQuery();
		
			if (result.next()){
				//check the password
				if (generateHash(passwd).equals(result.getString("passwd"))) {
					System.out.println(String.format("login as user %s successful", username));
					return true;
				} else {
					System.out.println("login failed");
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
