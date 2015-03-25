package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

public class GameServerDebugger extends JFrame {

	private static JTextArea ta_conversation = new JTextArea();
	private JScrollPane sp_console = new JScrollPane();
	private JCheckBox cb_canPrintMoved = new JCheckBox();
	private JCheckBox cb_canPrintMessages = new JCheckBox();

	public GameServerDebugger(String name, int width, int height) {
		super(name);
		super.setPreferredSize(new Dimension(width, height));
		super.setMinimumSize(new Dimension(width, height));
		super.setMaximumSize(new Dimension(width, height));

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(null);

		super.setResizable(true);
		super.setLocationRelativeTo(null);
		configureMainWindow();
	}

	private void configureMainWindow() {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.gray);
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(panel);

		// giant textField
		ta_conversation.setMargin(new Insets(5, 5, 5, 5));

		sp_console
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp_console
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_console.setViewportView(ta_conversation);
		sp_console.setBounds(0, 150, this.getWidth() - 200,
				this.getHeight() - 170);

		ta_conversation.setBackground(Color.DARK_GRAY);
		ta_conversation.setForeground(Color.white);

		panel.add(sp_console);

		JLabel canPrintLabel = new JLabel("Follow Player Movements");
		canPrintLabel.setBounds(65, 10, 200, 50);
		this.cb_canPrintMoved.setBounds(40, 25, 20, 20);
		panel.add(cb_canPrintMoved);
		panel.add(canPrintLabel);

		
		JLabel canPrintMessages = new JLabel("Dispay Player Messages");
		canPrintMessages.setBounds(65, 35, 200, 50);
		this.cb_canPrintMessages.setBounds(40, 50, 20, 20);
		panel.add(cb_canPrintMessages);
		panel.add(canPrintMessages);


	}
	

	public void printMoved(String username, float x, float y) {
		if (cb_canPrintMoved.isSelected()) {
			this.println("MOVED>" + username + " moved to: (" + x + ", " + y
					+ ")");
		}
	}

	public void printMessage(String username, String message) {
		if (cb_canPrintMessages.isSelected()) {
			this.println("MESSAGE>" + username + " said: " + message);
		}
	}

	public void printTimeStamp() {
		addToConversation("");
	}

	public void printlnTimeStamp() {
		addToConversation("\n");
	}

	public void print() {
		addToConversation("");
	}

	public void println() {
		addToConversation("\n");
	}

	public void print(String message) {
		addToConversation(message);
	}

	public void println(String message) {
		addToConversation(message + "\n");
	}

	public void addToConversation(String message) {
		ta_conversation.append(getTimeStamp() + " " + message);
		ta_conversation.setCaretPosition(ta_conversation.getDocument()
				.getLength());
	}

	private String getTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime()).toString();
	}

}
