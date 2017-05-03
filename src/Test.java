
public class Test {
	public static void main(String[] args) {
		ServerMessage s = new ServerMessage("message", "sen\"d", "receive", 399, ServerMessage.MESSAGE);
		System.out.println("s \"to string: " + s.toString());
		try {
			ServerMessage ser = new ServerMessage(s.toString());
			System.out.println("chatid: " + ser.getChatId() + " header: "+ ser.getheader() + " sendName: " + ser.getSendName() +" receiveName: " + ser.getReceiveName() +" message: " + ser.getMessage() );
		} catch (Exception e) {
			System.out.println("Failed to parse");
		}
	}
}
