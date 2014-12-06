package testers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ek_server_basic.BasicServer;

public class BasicServerTester {

	public static void main(String[] args){
		BasicServer server = new BasicServer(8888, -1);
		Thread t = new Thread(server);
		t.start();
		
		for(int i = 0; i < 100; i++){
			try {
				Socket socket = new Socket("localhost", 8888);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
