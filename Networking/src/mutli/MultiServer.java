package mutli;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiServer {

	public static void main(String[] args) throws IOException, InterruptedException{
		DatagramSocket socket = null;
		
		socket = new DatagramSocket(1331);
		
		while(true){
			byte[] buffer = new byte[1000];
			String message = "YOLO. Screw u clients";
			
			buffer  = message.getBytes();
			
			InetAddress ipAddressGroup = InetAddress.getByName("224.0.0.1");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ipAddressGroup, 1331);
			
			socket.send(packet);
			
			Thread.sleep(2000);
			
		}
		
	}
}
