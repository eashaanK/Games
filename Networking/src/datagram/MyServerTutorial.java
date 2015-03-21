package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MyServerTutorial {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(1331);

			byte[] buffer = new byte[1000];
			while (true) {
				String[] arrayMsg = null;
				DatagramPacket request = null;
				request = new DatagramPacket(buffer, buffer.length);

				socket.receive(request);

				arrayMsg = (new String(request.getData()).split(" "));

				System.out.println("Server recieved: "
						+ new String(request.getData()));

				byte[] sendMessage = (arrayMsg[1] + " server processed ")
						.getBytes();

				DatagramPacket reply = new DatagramPacket(sendMessage,
						sendMessage.length, request.getAddress(),
						request.getPort());

				socket.send(reply);
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
