package serverComponents;

public class C {
	public static final String PLAYER = "PLAYER";
	public static final String MAP = "MAP";
	public static final String MULTIPLAYER_GAME_STATUS = "MULTIPLAYER_GAME_STATUS";
	public static final String MESSAGE = "MESSAGE";
	public static final String AI = "AI";
	public static final String DISCONNECT = "DISCONNECT";
	public static final String JOIN = "JOIN";
	
	public static final String JOIN_SUCCESSFUL = "JOIN_SUCCESSFUL";
	public static final String JOIN_FAILURE = "JOIN_FAILURE";

	public static final String FETCH_PLAYER = "FETCH_PLAYER";
	public static final String FETCH_MAP = "FETCH_MAP";
	public static final String FETCH_MULTIPLAYER_GAME_STATUS = "FETCH_MULTIPLAYER_GAME_STATUS";
	public static final String FETCH_MESSAGE = "FETCH_MESSAGE";
	public static final String FETCH_AI = "FETCH_AI";
	
	public static final String REGEX = "/";
	
	public static String getRestOfMessage(int index, String[] parts){
		String ans = "";
		for(int i = index; i < parts.length; i++)
			ans += parts[i];
		return ans;
	}
}
