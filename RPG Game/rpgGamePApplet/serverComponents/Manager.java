package serverComponents;

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
					String message = in.nextLine();
					String parts[] = message.split(C.REGEX);
					if (parts[0].equals(C.PLAYER)) {
						handlePlayer(message);
					}

					else if (parts[0].equals(C.MAP)) {
						handleMap(message);

					} else if (parts[0].equals(C.MULTIPLAYER_GAME_STATUS)) {
						handleMGS(message);

					}

					else if (parts[0].equals(C.MESSAGE)) {
						handleMessage(message);

					} else if (parts[0].equals(C.AI)) {
						handleAI(message);
					}

					else if (parts[0].equals(C.DISCONNECT)) {
						handleDisconnect(parts);
					}

					else if (parts[0].equals(C.JOIN)) {
						handleJoin(parts);
					}

					else if (parts[0].equals(C.FETCH_PLAYER)) {
						handleFetchPlayer(parts);
					}

					else if (parts[0].equals(C.FETCH_MAP)) {
						handleFetchMap(parts);
					}

					else if (parts[0].equals(C.FETCH_MULTIPLAYER_GAME_STATUS)) {
						handleFetchMGS(parts);
					}

					else if (parts[0].equals(C.FETCH_MESSAGE)) {
						handleFetchMessage(parts);
					}

					else if (parts[0].equals(C.FETCH_AI)) {
						handleFetchAI(parts);
					} else
						console.println("THE MESSAGE RECIEVED BY MANAGER DID NOT CONTAIN ANY UNDERSTANBLE PREFIX: "
								+ message);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*********************************************** Only allowed to use PrintWriter for fetch commands ********************************************/
	private String lastAI, lastMessage, lastMGS, lastMap, lastPlayer;

	private void handleFetchAI(String[] parts) {
		String temp = Server.ai.poll();
		if (temp == null)
			return;
		lastAI = temp;
		out.println(lastAI);
		out.flush();
	}

	private void handleFetchMessage(String[] parts) {
		String temp = Server.messages.poll();
		if (temp == null)
			return;
		lastMessage = temp;
		out.println( lastMessage);
		out.flush();
	}

	private void handleFetchMGS(String[] parts) {
		String temp = Server.mgs.poll();
		if (temp == null)
			return;
		lastMGS = temp;
		out.println(lastMGS);
		out.flush();
	}

	private void handleFetchMap(String[] parts) {
		String temp = Server.map.poll();
		if (temp == null)
			return;
		lastMap = temp;
		out.println(lastMap);
		out.flush();
	}

	/**
	 * Does not send the player if his name isn't in the list of names online
	 * 
	 * @param parts
	 */
	private void handleFetchPlayer(String[] parts) {
		String temp = Server.players.poll();
		if (temp == null)
			return;

		// also remove from player list
		String[] partsOfPlayerToSend = temp.split(C.REGEX);
		
		if(Server.names.contains(partsOfPlayerToSend[5])){//ok to send
			lastPlayer = temp;
			out.println(lastPlayer);
			out.flush();
		}
		
		else
			console.println(partsOfPlayerToSend[5] + " was not found in names: " + Server.names.toString() + " so Server will not send its info.");
	}

	// send either success or failure and reason for failure
	private void handleJoin(String[] parts) {
		boolean success = false;

		if (!Server.names.contains(parts[1]))
			success = true;

		if (success) {
			Server.names.add(parts[1]);
			out.println(C.JOIN_SUCCESSFUL + C.REGEX + "SUCCESS. Ignore this");
			console.println(parts[1] + " joined");
		}

		else {
			out.println(C.JOIN_FAILURE
					+ C.REGEX
					+ "The name: "
					+ parts[1]
					+ "was either not appropriate or already in use. Try another name");
			console.println(parts[1] + " was rejected join request");
		}
		out.flush();
	}

	/******************************************* Cannot Use PrintWriter here *************************************************************/

	private void handleDisconnect(String[] parts) {
		console.println(parts[0]);
		Server.names.remove(parts[1]);

		Server.messages.add("Server: " + parts[1] + " left the game");
	}

	private void handleAI(String message) {
		Server.ai.add(message);
	}

	private void handleMessage(String message) {
		Server.messages.add(message);

	}

	private void handleMGS(String message) {
		Server.mgs.add(message);

	}

	private void handleMap(String message) {
		Server.map.add(message);

	}

	private void handlePlayer(String message) {
		Server.players.add(message);

	}

}
