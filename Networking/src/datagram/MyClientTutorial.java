package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MyClientTutorial {

	public static void main(String[] args) {
		
		
		try {
			
			DatagramSocket socket = new DatagramSocket();
			String message = "test message";
			byte[] data = message.getBytes();
			InetAddress host = InetAddress.getByName("localhost");
			int serverSocket = 1331;
			DatagramPacket request = new DatagramPacket(data, data.length, host, serverSocket);
			socket.send(request);
			
			//ignore
			byte[] buffer = new byte[5000];
			
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			
			socket.receive(reply);
			
			System.out.println("Client recieved: " + new String(reply.getData()));
			
			socket.close();
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
