package testers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ek_server_basic.BasicServer;

public class BasicServerTester {

	public static void main(String[] args){
		BasicServer server = new BasicServer(8888, -1);
		Thread t = new Thread(server);
		t.start();
		
		try {
			Socket socket = new Socket("localhost", 8888);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println("LODSSD");
			out.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
