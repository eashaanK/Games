
public class ServerMain {

	public static void main(String[] args){
		Server server = new Server(8888, -1, "Server");
		
		Thread t = new Thread(server);
		t.start();
	}
}
