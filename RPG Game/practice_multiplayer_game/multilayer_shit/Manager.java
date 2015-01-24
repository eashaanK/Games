package multilayer_shit;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Manager extends StoppableThread implements Runnable {

	private Socket socket;
	private EKConsole console;
	private int id;
	private Scanner in;
	private PrintWriter out;	
	
	private int gameObjIndex, messageIndex;
	
	public Manager(Socket socket, EKConsole console, int id) {
		this.socket = socket;
		this.console = console;
	}

	@Override
	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			// console.println("Manager " + id + " received: " + in.nextLine());

			while (this.isActive()) {
				if (in.hasNext()) {
					String message = in.nextLine(); //only fetch messages
					console.println(message);
					if(message.equals(C.FETCH_MESSAGE)){
						fetchMessage(out);
						continue;
					}
					else if(message.equals(C.FETCH_GAMEOBJS)){
						fetchGameObjs(out);
						continue;
					}
					String parts[] = message.split(C.REGEX);
				
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fetchGameObjs(PrintWriter out) {
		/*if(this.gameObjIndex < Server.gameObjects.size()){
			out.println(Server.gameObjects.get(gameObjIndex));
			out.flush();
			this.gameObjIndex++;
		}	*/
		
		out.println(1f);
		out.println(2f);
		out.println(3f);
		out.flush();
	}

	private void fetchMessage(PrintWriter out) {
		if(this.messageIndex < Server.messages.size()){
			out.println(Server.messages.get(messageIndex));
			out.flush();
			this.messageIndex++;
		}		
	}

	/*********************************************** Only allowed to use PrintWriter for fetch commands ********************************************/
	
}
