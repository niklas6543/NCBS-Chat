//package server;

import java.security.Timestamp;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface ServerDatabase {
	public ArrayList<String> getAviableClients();
	
	public void setOnline(String username);
	
	public boolean tryToLogin(String username, String passwd);
	
	//public int getReceivers(String name);
	
	public void setReceivers(String[] user, int chatid);
	
	public void setOffline(String username);
	
	public void sendMessage(int chatId);

	public class bufferdMessage {
		String message;
		String sender;
		Timestamp recievedFromServer;
		
	}
	
	public ArrayList<bufferdMessage> getMessagesSince(int chatId, Timestamp since);

	public HashMap<Integer, String> getReceivers(int chatid);

	

}
