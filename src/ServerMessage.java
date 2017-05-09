
public class ServerMessage {
	
	/* header,chatId,"senderName","receiveName",the rest is message */
	
	// Flags for handle the flag
	public static final int LOGIN = 0;
	public static final int MESSAGE = 1;
	public static final int LOGOUT = 2;

	private String message = "";
	private String sendName = "";
	private String receiveName = "";
	private int chatId; // TODO not in use already
	private int header; // how to handle the message



	public ServerMessage(String serverMessage) throws Exception {
		String[] s;
		s = serverMessage.split(",");
		this.header = Integer.parseInt(s[0]);
		this.chatId = Integer.parseInt(s[1]);

		int k = s[0].length() + s[1].length() + 2;
		try {
			this.sendName = retrimm(k, serverMessage);
			k += this.sendName.length() + 4;
			this.receiveName = retrimm(k, serverMessage);
		} catch (Exception e) {
			throw e;
		}
		k += receiveName.length() + 3;
		this.message = serverMessage.substring(k);

	}

	public ServerMessage(String message, String sendName, String receiveName, int chatId, int header) {
		this.message = message;
		this.sendName = sendName;
		this.receiveName = receiveName;
		this.chatId = chatId;
		this.header = header;
	}
	
	public void setHeader(int header){
		this.header = header;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getSendName() {
		return sendName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public int getChatId() {
		return chatId;
	}

	public int getheader() {
		return header;
	}

	public String toString() {
		String re = new String();
		re = Integer.toString(this.header);
		re += ",";
		re += Integer.toString(this.chatId);
		re += ",\"";
		re += trimm(this.sendName);
		re += "\",\"";
		re += trimm(this.receiveName);
		re += "\",";
		re += this.message;
		return re;
	}
	//quote
	private String trimm(String unsave) {
		String save = "";
		for (int i = 0; i < unsave.length(); i++) {
			if (unsave.charAt(i) == '/') {
				save += "//";

			} else if (unsave.charAt(i) == '"') {
				save += "/\"";
			} else {
				save += Character.toString(unsave.charAt(i));
			}
		}
		return save;
	}
	//unquote
	private String retrimm(int start, String serverMessage) throws Exception {
		// Opposite of trimm()
		String re = "";
		while (++start < (serverMessage.length() - 1)) {

			if ((serverMessage.charAt(start) == '/') && (serverMessage.charAt(start - 1) != '/')
					&& (serverMessage.charAt(start + 1) != '"') && (serverMessage.charAt(start + 1) != '/')) {
				throw new Exception();
			} else if (serverMessage.charAt(start) == '/' && serverMessage.charAt(start - 1) == '/') {
				re += "/";
			} else if (serverMessage.charAt(start) == '/' && serverMessage.charAt(start + 1) == '"') {
				re += "\"";
				start++;
			} else if ((serverMessage.charAt(start) == '"') && (serverMessage.charAt(start - 1) != '/')) {
				break;
			} else {
				re += Character.toString(serverMessage.charAt(start));
			}
		}
		return re;
	}
}
