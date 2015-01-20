package shapes_server;

public class C {

	public static final String REGEX = "/";
	public static final String JOIN = "<100>";

	//Client will recieve this from the Server
	public static final String JOIN_CONFIRMED = "<101>";
	public static final String JOIN_FAIL = "<102>";
	
	//Client and Manager will recieve both of this: 
	public static final String PLAYER_INFO = "<103>";
	public static final String NEW = "<104>";
	public static final String OLD = "<105>";
	public static final String DECOY = "<123>";


}
