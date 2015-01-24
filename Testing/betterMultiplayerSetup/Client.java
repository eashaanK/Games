import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client extends StoppableThread implements Runnable{

	private int port;
	private String host;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private void setUpStreams(Socket socket){
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream()); //ALWAYS put out first and flush it
			out.flush();
			this.in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public Client(String host, int port){
		this.port = port;
		this.host = host;
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			this.stopThread();
			e.printStackTrace();
		} catch (IOException e) {
			this.stopThread();
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (this.isActive()) {
			this.setUpStreams(socket);

			try {
				Object o = in.readObject();
				
				if(o instanceof Rectangle){
					Rectangle t = (Rectangle)o;
					System.out.println("Rectangle received " + t.toString());
				}
				
				if(o instanceof Point){
					Point t = (Point)o;
					System.out.println("Point received " + t.toString());
				}
				
				//NOTE: RESET IT IF U WANT TO SEND STUFF
				
			} catch (ClassNotFoundException e) {
				//the class u casted didnt exist retard
				e.printStackTrace();
			} catch (IOException e) {
				//when something disconnects
				//e.printStackTrace();
				//QUIT RIGHT HERE
			}	
				
		}
	}
}
