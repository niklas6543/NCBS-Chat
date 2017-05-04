import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Client {

	public static void main(String[] args) {

		try {
			// create client
			Socket client = new Socket("localhost", 5555);
			System.out.println("client started...");

			// create streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);

			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String message = "";
			// send login message to the server
			Scanner scan = new Scanner(System.in);
			ServerMessage send = new ServerMessage("test", "Schunk", "server", 0, ServerMessage.LOGIN);
			writer.write(send.toString().concat("\n"));
			writer.flush();
			//searchForData(reader);
			
			while (!message.equals(".exit")) {
				message = scan.nextLine();
				send.setMessage(message);
				writer.write(send.toString().concat("\n"));
				writer.flush();

				searchForData(reader);
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

	}
	
	public static void searchForData(BufferedReader reader){
		// search for receiving data
		String s = null;
		try {
			if ((s = reader.readLine()) != null) {
				try {
					ServerMessage got = new ServerMessage(s);
					System.out.println("receive from server: " + got.getMessage());
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
