package chatProgram;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ChatClient implements Runnable{

	Socket sock;
	Scanner input;
	Scanner send = new Scanner(System.in);
	PrintWriter out;
	
	public ChatClient(Socket soc){
		this.sock = soc;
	}

	public void run() {
		try{
			try{
				input = new Scanner(this.sock.getInputStream());
				out = new PrintWriter(this.sock.getOutputStream());
				out.flush();
				updateStream();
			}
			finally
			{
				this.sock.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void DISCONNECT() throws IOException{
		out.println(ChatGUI.userName + " has disconnected");
		out.flush();
		sock.close();
		JOptionPane.showMessageDialog(null, "You Disconnected");
		System.exit(0);
	}
	
	public void RECIEVE(){
		if(input.hasNext()){
			String message = input.nextLine();
			if(message.contains(ChatServer.specialCommand)){
				String t1 = message.substring(ChatServer.specialCommand.length());
				t1 = t1.replace("[", "");
				t1 = t1.replace("]", "");
				
				String[] currrentUsers = t1.split(", ");
				ChatGUI.jl_online.setListData(currrentUsers);
			}
			else
			{
				ChatGUI.ta_conversation.append(message + "\n");
			}
		}
	}

	public void SEND(String message){
		out.println(ChatGUI.userName + ": " + message);
		out.flush();
		ChatGUI.tf_message.setText("");
	}
	
	private void updateStream(){
		while(true){
			RECIEVE();
		}
	}
}
