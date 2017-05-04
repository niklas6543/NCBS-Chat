import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Server {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {
			//starting server
			ServerSocket server = new ServerSocket(5555);
			System.out.println("server is running...");
			Connection connect = Login.connectToDatabase();
			
			Socket client = server.accept();
			
			//create streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			
			//search for receiving data
			String s = null;
			
			while ((s = reader.readLine()) != null) {
				writer.write(s+"\n");
				writer.flush();
				
				ServerMessage get;
				try {
					get = new ServerMessage(s);
					
					switch (get.getheader()) {
					case ServerMessage.MESSAGE:
						// TODO send to get.getReceiveName()
						System.out.println("receive from "+get.getSendName()+": "+ get.getMessage());
						break;
					case ServerMessage.LOGIN:
						// TODO das hier ist Stelle für deine Anmeldung
						//try to login
						if (Login.tryToLogin(connect, get.getSendName(), get.getMessage())){
							get.setHeader(ServerMessage.MESSAGE);
							writer.write(get.toString().concat("\n"));
							writer.flush();
							searchForData(reader);
						}
						
						break;
					case ServerMessage.LOGOUT:
						// TODO
						break;
					default:
						System.out.println("Houston, we have a problem");
						break;
					}
					
				} catch (Exception e) {
					System.out.println("not able to parse message");
				}
				
				
			}
			System.out.println("client is unrechable");

			writer.close();
			reader.close();
			server.close();
			
			
			
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			// TODO only for debuging
			e.printStackTrace();
			System.out.println("Socket is allready used");

		}
	}
	public static void searchForData(BufferedReader reader){
		// search for receiving data
		String s = null;
		try {
			if ((s = reader.readLine()) != null) {
				try {
					ServerMessage got = new ServerMessage(s);
					System.out.println("receive from client: " + got.getMessage());
				} catch (Exception e) {
					System.out.println("Fuck");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
