
public class ClientMain {

	public static void main(String[] args) {
		Client client = new Client("", 8888);
		
		Thread t = new Thread(client);
		t.start();
	}

}
