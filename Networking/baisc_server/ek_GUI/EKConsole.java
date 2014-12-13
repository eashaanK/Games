package ek_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import ek_Misc.Helper;
import ek_Misc.StoppableThread;

public class EKConsole extends StoppableThread implements Runnable {

	private static JFrame mainWindow = new JFrame();

	// private JTextArea ta_console = new JTextArea();
	private JTextPane tp_console = new JTextPane();
	private JScrollPane sp_console = new JScrollPane();

	private Dimension dimension;

	private Color backgroundColor;

	public EKConsole(Dimension d, String title, Color bkg) {
		this.dimension = d;
		this.mainWindow.setTitle(title);
		this.println("Console started", new Color(255 - bkg.getRed(), 255 - bkg.getGreen(), 255 - bkg.getBlue(), 255 ));
		this.backgroundColor = bkg;
	}

	public EKConsole(int w, int h, String title, Color bkg) {
		this.dimension = new Dimension(w, h);
		this.mainWindow.setTitle(title);
		this.println("Console started", new Color(255 - bkg.getRed(), 255 - bkg.getGreen(), 255 - bkg.getBlue(), 255 ));
		this.backgroundColor = bkg;
	}

	public void run() {
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

		tp_console.setBackground(backgroundColor);
		tp_console.setMargin(new Insets(5, 5, 5, 5));

		sp_console
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp_console
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_console.setViewportView(tp_console);
		sp_console.setBounds(0, 0, mainWindow.getWidth() ,
				mainWindow.getHeight() - 20);
		mainWindow.add(sp_console);
		

	}

	public void printTimeStamp() {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " , Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void printlnTimeStamp() {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + "\n", Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void print() {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "" , Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void println() {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "\n", Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void print(String message) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + message, Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void println(String message) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + message + "\n", Color.black);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}
	
	public void printTimeStamp(Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " , c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);

	}

	public void printlnTimeStamp(Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + "\n", c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);

	}

	public void print(Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "" , c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);
	}

	public void println(Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "\n", c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);

	}

	public void print(String message, Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + message, c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);

	}
	
	public void println(String message, Color c) {
		tp_console.setEditable(true);
		appendToPane(this.tp_console, "(" + Helper.getTimeStamp() + ") " + message + "\n", c);
		tp_console.setCaretPosition(tp_console.getDocument().getLength());
		tp_console.setEditable(false);

	}
	
	private void appendToPane(JTextPane tp, String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily,
				"Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment,
				StyleConstants.ALIGN_JUSTIFIED);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
	}
}
