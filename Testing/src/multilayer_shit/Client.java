package multilayer_shit;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends StoppableThread implements Runnable{

	private int port;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	public Client(int port){
		this.port = port;
	}
	
	public void findServer(String host){
		try {
			socket = new Socket(host, this.port);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			this.stopThread();
			e.printStackTrace();
		} catch (IOException e) {
			this.stopThread();
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		ClientFetch cF = new ClientFetch();
		cF.add(C.FETCH_MESSAGE, 1000, 1);
		cF.add(C.FETCH_USERS, 10000, 1);
		Thread t = new Thread(cF);
		t.start();
		while(this.isActive()){
			if(this.in.hasNext()){
				String message = in.nextLine();
				String[] split = message.split(C.REGEX);
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	private class ClientFetch implements Runnable{

		private ArrayList<Command> thingsToSend = new ArrayList<Command>();
		
		@Override
		public void run() {
			while(Client.this.isActive()){
				for(Command c: thingsToSend){
					if(c.update()){ //if its time to send
						out.println(c.command);
						out.flush();
					}
				}
			}
		}
		
		public void add(String command, int maxCount, int inc){
			thingsToSend.add(new Command(command, maxCount, inc));
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	private class Command{
		public String command;
		public final int maxCount;
		public int current, inc;
		
		public Command(String c, int m, int inc){
			this.command = c;
			this.maxCount = m;
			this.inc = inc;
		}
		
		/**
		 * True if it is time to send
		 * @return
		 */
		public boolean update(){
			current += inc;
			if(current >= maxCount){
				current = 0;
				return true;
			}
			return false;
		}
		
	}
}
