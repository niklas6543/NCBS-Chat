import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;


public class AcceptClients extends Thread{
		private Socket client;
		private Connection connect;
		
		public AcceptClients(Socket client, Connection connect){
			this.client = client;
			this.connect = connect;
		}
		
		public void run() {
	
			//create streams
			OutputStream out;
			try {
				out = this.client.getOutputStream();
			
				PrintWriter writer = new PrintWriter(out);
		
				InputStream in = this.client.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		
				//search for receiving data
				String s = null;
				
				int status = ServerMessage.LOGIN; // 0 = not logged in, 1 = logged in
		
				while ((s = reader.readLine()) != null) {
					writer.write(s+"\n");
					writer.flush();
			
					ServerMessage get;
					try {
						get = new ServerMessage(s);
						
						switch (status) {
							case ServerMessage.MESSAGE:
								// TODO send to get.getReceiveName()
								System.out.println("receive from "+get.getSendName()+": "+ get.getMessage());
								break;
							case ServerMessage.LOGIN:
								// TODO das hier ist Stelle für deine Anmeldung
								//try to login
								
								if (Login.tryToLogin(this.connect, get.getSendName(), get.getMessage())){
									get.setHeader(ServerMessage.MESSAGE);
									status = ServerMessage.MESSAGE;
									/*writer.write(get.toString().concat("\n"));
									writer.flush();
									searchForData(reader);*/
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
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

