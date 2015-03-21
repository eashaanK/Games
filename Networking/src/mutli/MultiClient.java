package mutli;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClient {

	public static void main(String[] args) throws IOException{

		int id = (int)(Math.random() * 100);
		
		MulticastSocket socket = new MulticastSocket();
		
		InetAddress ipAddressGroup = InetAddress.getByName("224.0.0.1");
		socket.joinGroup(ipAddressGroup);
		
		DatagramPacket packet = null;
		
		System.out.println("Now geting ready: ");
		for(int i = 0 ; i < 10; i++){
			byte[] buffer = new byte[1000];
			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			
			String message = new String(packet.getData());
			System.out.println("Client " + id + " received: " + message);
		}
	}
}
