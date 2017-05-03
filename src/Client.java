import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
=======
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
>>>>>>> Initial Commit
import java.io.OutputStream;
import java.io.PrintWriter;

public class Client {
<<<<<<< HEAD
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
		
=======
	public static void start() {

		try {
			// create client
			Socket client = new Socket("localhost", 5555);
			System.out.println("client started...");

			// create streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);

			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String message = "hallo";
			// send data to the server
			Scanner scan = new Scanner(System.in);
			while (!message.equals(".exit")) {
				message = scan.nextLine();
				writer.write(message.concat("\n") );
				writer.flush();

				// search for receiving data
				String s = null;

				if ((s = reader.readLine()) != null) {
					System.out.println("receive from server: " + s);

				}
			}
			scan.close();
			client.close();
			writer.close();
			reader.close();

		} catch (UnknownHostException e) {
			// TODO only for debuging
			e.printStackTrace();
			System.out.println("host is not reachable");
		} catch (IOException e) {
			// TODO only for debuging
			e.printStackTrace();
			System.out.println("ich hab keine ahnung");
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Unbekannter Fehler");
		} finally {
			System.out.println("Bye");
		}

>>>>>>> Initial Commit
	}
}
