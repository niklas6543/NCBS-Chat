import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Test {
	public static void main(String[] args) {
		ServerMessage s = new ServerMessage("message", "sen\"d", "receive", 399, ServerMessage.MESSAGE);
		System.out.println("s \"to string: " + s.toString());
		try {
			ServerMessage ser = new ServerMessage(s.toString());
			System.out.println("chatid: " + ser.getChatId() + " header: "+ ser.getheader() + " sendName: " + ser.getSendName() +" receiveName: " + ser.getReceiveName() +" message: " + ser.getMessage() );
			
			
			String text = "test";
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
			
			digest.update(text.getBytes());

		    byte byteData[] = digest.digest();

		    
		    StringBuffer sb = new StringBuffer();
		    
		    for (int i = 0; i < byteData.length; i++) {
		    	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    }

		    System.out.println("Hex format : " + sb.toString());
			
		} catch (Exception e) {
			System.out.println("Failed to parse");
		}
	}
}
