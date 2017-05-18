import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

public class Database implements ServerDatabase{
	/*
	 * TODO
	 * get all avaible users
	 * set chatid 
	 * valid chatid
	 * 
	 */
	static Connection connect = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Database();
		connectToDatabase();
		db.setOnline("Schunk");
		
	}
	
	public static Connection connectToDatabase(){
		//Connection connect = null;
		
		try {
			//try to connect database
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/NCBS-Chat-DB", "root", "");
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/NCBSChatDB", "Niklas", "1234");
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
	
	
//####################################
	
	@Override
	public boolean tryToLogin(String username, String passwd){
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
	
	@Override
	public void setReceivers(String[] user, int chatid){
		//create MySQL query
		PreparedStatement myStmt;
		
		try {
			String sql = String.format("UPDATE user SET chatid=%s WHERE", chatid);
			
			for (int i=0; i < user.length-1; i++){
				sql += " username LIKE ? OR";
			}
			sql += " username LIKE ?;"; 
			
			myStmt = connect.prepareStatement(sql);
			
			for (int i=1; i < user.length+1; i++){
				myStmt.setString(i,user[i-1]);
			}
			
			//get all chatids from database
			myStmt.execute();
		
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public HashMap<Integer,String> getReceivers(int chatid){
		HashMap<Integer,String> receivers = new HashMap<Integer,String>();
		
		//create MySQL query
		PreparedStatement myStmt;
		String sql = "select id,username from user where chatid = ? and online = 1;";
		try {
			myStmt = connect.prepareStatement(sql);
			myStmt.setInt(1, chatid);
			
			//get all chatids from database
			ResultSet result = myStmt.executeQuery();
		
			
			while (result.next()){
				receivers.put(Integer.parseInt(result.getString("id")), result.getString("username"));
			}
			
			System.out.println(receivers.get(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receivers;
	}

	@Override
	public ArrayList<String> getAviableClients() {
	// TODO get all online clients
		ArrayList<String> usernames = new ArrayList<String>();
		Statement myStmt = null;
		String sql = "select username from user where online=1;";
		try {
			ResultSet result = myStmt.executeQuery(sql);
			
			while (result.next()){
				usernames.add(result.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usernames;
	}

	@Override
	public void setOnline(String username) {
		// TODO set client online
		//create MySQL query
				PreparedStatement myStmt;
				
				try {
					String sql = "UPDATE user SET online=1 WHERE username LIKE ?";
					
					myStmt = connect.prepareStatement(sql);
					myStmt.setString(1, username);
					
					//set user online
					myStmt.execute();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}


	@Override
	public void setOffline(String username) {
		// TODO set client offline
		//create MySQL query
		PreparedStatement myStmt;
		
		try {
			String sql = "UPDATE user SET online=0 WHERE username LIKE ?";
			
			myStmt = connect.prepareStatement(sql);
			myStmt.setString(1, username);
			
			//set user online
			myStmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void sendMessage(int chatId) {
		// TODO save the message to all clients with the chatid (message and timestamp)
		
	}

	@Override
	public ArrayList<bufferdMessage> getMessagesSince(int chatId, Timestamp since) {
		// TODO get all messages since timestamp
		return null;
	}

}
