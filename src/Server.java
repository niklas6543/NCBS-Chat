import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Server {

	public static void main(String[] args) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
		
>>>>>>> Initial Commit
		try {
			//starting server
			ServerSocket server = new ServerSocket(5555);
			System.out.println("server is running...");
			
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
				System.out.println("receive from client: "+s);
				
			}
<<<<<<< HEAD
=======
			System.out.println("DIE SCHLEIFE IST BEENDET");
>>>>>>> Initial Commit
			
			writer.close();
			reader.close();
			
			
			
		} catch (IOException e) {
<<<<<<< HEAD
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
			// TODO only for debuging
			e.printStackTrace();
			System.out.println("Socket is allready used");
>>>>>>> Initial Commit
		}
	}

}
