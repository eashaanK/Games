package multilayer_shit;

public class Tester {

	public static void main(String[] args){
		Server server = new Server(8888, -1, "Testing Server");
		
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		Client c = new Client(8888);
		c.findServer("localhost");
		Thread t = new Thread(c);
		t.start();
	}
}
