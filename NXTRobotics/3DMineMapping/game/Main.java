
package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
	public static void main(String[] args)
	{
		/*CoreEngine engine = new CoreEngine(800, 600, 60, new TestGame());
		engine.CreateWindow("3D Game Engine");
		engine.Start();*/
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Server Started. Waiting for Robot to join:");

		
		Socket socket = null;
		try {
			 socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Robot has joined!");

		try {
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
