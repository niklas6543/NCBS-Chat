import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//create client
			Socket client = new Socket("localhost", 5555);
			System.out.println("client started...");
			
			//create streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			//send data to the server
			writer.write("Hallo Welt!!!\n");
			writer.flush();
			
			//search for receiving data
			String s = null;
			
			while ((s = reader.readLine()) != null) {
				System.out.println("receive from server: "+s);
				
			}
			
			writer.close();
			reader.close();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
