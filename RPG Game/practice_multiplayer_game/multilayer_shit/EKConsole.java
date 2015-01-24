package multilayer_shit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class EKConsole extends StoppableThread implements Runnable {

	private JFrame mainWindow = new JFrame();

	// private JTextArea ta_console = new JTextArea();
	private JTextArea ta_console = new JTextArea();
	private JScrollPane sp_console = new JScrollPane();

	private Dimension dimension;

	private Color backgroundColor, textColor;

	public EKConsole(Dimension d, String title, Color bkg, Color txt) {
		this.dimension = d;
		this.mainWindow.setTitle(title);
		this.println("Console started");
		this.backgroundColor = bkg;
		textColor = txt;
	}

	public EKConsole(int w, int h, String title, Color bkg, Color txt) {
		this.dimension = new Dimension(w, h);
		this.mainWindow.setTitle(title);
		this.println("Console started");
		this.backgroundColor = bkg;
		textColor = txt;
	}

	public void run() {
		ta_console = new JTextArea();
		mainWindow();

	}

	private void mainWindow() {
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(dimension);
		mainWindow.setResizable(true);
		configureMainWindow();
		mainWindow.setVisible(true);

	}

	private void configureMainWindow() {
		mainWindow.setLayout(null);

		ta_console.setBackground(backgroundColor);
		ta_console.setForeground(this.textColor);
		ta_console.setColumns(20);
		ta_console.setFont(new Font("Tahoma", 0, 12));
		ta_console.setLineWrap(true);
		ta_console.setRows(5);
		ta_console.setEditable(false);

		sp_console
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp_console
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_console.setViewportView(ta_console);
		sp_console.setBounds(0, 0, mainWindow.getWidth() ,
				mainWindow.getHeight() - 20);
		mainWindow.add(sp_console);
	}
	
	private void autoScroll(){
		ta_console.setCaretPosition(ta_console.getDocument().getLength());

	}
	
	public void appendToPane(String message){
		ta_console.append(message);
		autoScroll();
	}

	public void printTimeStamp() {
		appendToPane("(" + Helper.getTimeStamp() + ") ");
		autoScroll();
	}

	public void printlnTimeStamp() {
		appendToPane("(" + Helper.getTimeStamp() + ") " + "\n");
		autoScroll();
	}

	public void print() {
		appendToPane("");
		autoScroll();
	}

	public void println() {
		appendToPane("\n");
		autoScroll();
	}

	public void print(String message) {
		appendToPane( "(" + Helper.getTimeStamp() + ") " + message);
		autoScroll();
	}

	public void println(String message) {
		appendToPane( "(" + Helper.getTimeStamp() + ") " + message + "\n");
		autoScroll();
	}
	
	
}
