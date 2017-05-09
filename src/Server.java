import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;




public class Server {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
			System.out.println("server is running...");
			Connection connect = Login.connectToDatabase();
	
			//starting server
			ServerSocket server;
			try {
				server = new ServerSocket(55555);
				//ServerSocket server = new ServerSocket(0, 10);
				while (true){
					Socket client = server.accept();
					
					Thread th = new Thread(new AcceptClients(client, connect));
					th.start();
					
				}
				//server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
}
