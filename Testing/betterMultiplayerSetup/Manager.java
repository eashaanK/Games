

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Manager extends StoppableThread implements Runnable {

	private Socket socket;
	private EKConsole console;
	private int id;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	

	public Manager(Socket socket, EKConsole console, int id) {
		this.socket = socket;
		this.console = console;

	}
	
	private void setUpStreams(Socket socket){
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//MAKE SURE TO SETUP STREAM EVERYTIME
	@Override
	public void run() {

		while (this.isActive()) {
			this.setUpStreams(socket);

			try {
				out.writeObject(new Rectangle(10, 10, 10, 10));
				out.flush();

				this.setUpStreams(socket); //MUST RESET OUTPUT STREAM AND INPUT STREAM EVERY TIME
				
				out.writeObject(new Point(1, 1));
				out.flush();
				

			} catch (IOException e) {
				//when something disconnects
				//e.printStackTrace();
				//QUIT RIGHT HERE
			}	
				
		}

		
	}

	/*********************************************** Only allowed to use PrintWriter for fetch commands ********************************************/
	
}
