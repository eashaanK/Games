package com.colorPicker.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import controlP5.Button;
import controlP5.ColorPicker;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.ListBox;
import controlP5.ListBoxItem;

public class ColorPickerGame extends PApplet {

	boolean hasSetupClose = false;

	ControlP5 gui;
	ColorPicker cp;
	Button saveButton;
	ListBox savedColors;

	private final String FILE_NAME = "files/Colors.txt";
	private File file;
	private Scanner scanner;
	private PrintWriter writer;

	private float x = 0, y = 0;

	public void setup() {
		size(275, 500);
		x = width / 2 - 255 / 2 - 10;
		y = 0;
		this.frameRate(60);
		this.smooth();
		gui = new ControlP5(this);
		cp = gui.addColorPicker("ColorPicker").setPosition(x + 10, y + 10)
				.setColorValue(color(255, 255, 255, 255));
		saveButton = gui.addButton("Save");
		saveButton.setPosition(width / 2 - saveButton.getWidth() / 2, cp.getPosition().y + 4 * cp.getHeight()
						+ saveButton.getHeight() + 10);

		savedColors = gui.addListBox("Saved Colors").setSize(width, height/2);
		savedColors.setPosition(width / 2 - savedColors.getWidth() / 2, saveButton.getPosition().y + savedColors.getHeight() + saveButton.getHeight() + 50).setItemHeight(30).setBarHeight(30);
		
		/*for (int i=0;i<80;i++) {
			    ListBoxItem lbi = savedColors.addItem("item "+i, i);
			    lbi.setColorBackground(0xffff0000);
		}*/

		frame.setTitle("Color Picker V 0.1");

		initFile();
	}

	private void initFile() {
		file = new File(FILE_NAME);
		file.getParentFile().mkdirs();

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Scanner object could not be contructed.\nDid you change the Colors.txt file in anyway?",
							"Scanner Error", JOptionPane.ERROR_MESSAGE);
		}

		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)));
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"PrintWriter object could not be contructed.\nDid you change the Colors.txt file in anyway?",
							"Scanner Error", JOptionPane.ERROR_MESSAGE);
		}
		
		scanAndAddFromFile();
	}
	

	public void draw() {
		if (!hasSetupClose) {
			ChangeWindowListener();
		}

		background(0);
		fill(0, 80);
		rect(x, y, 275, 80);
		gui.draw();

	}

	private void ChangeWindowListener() {
		WindowListener[] wls = frame.getWindowListeners();
		println("Found " + wls.length + " listeners");
		if (wls.length > 0) {
			frame.removeWindowListener(wls[0]); // Suppose there is only one...
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.out.println("Have a Nice Day");
					writer.close();
					scanner.close();
					// Do something useful here
					exit();
				}
			});
			hasSetupClose = true;
		}
	}

	public void controlEvent(ControlEvent theEvent) {
		if (theEvent.getController().getName().equals("Save")) {
			System.out.println("Save pressed");
			save();
		}

	}

	private void save() {
		int r = (int) (cp.getArrayValue(0));
		int g = (int) (cp.getArrayValue(1));
		int b = (int) (cp.getArrayValue(2));
		int a = (int) (cp.getArrayValue(3));
		addToFile(r, g, b, a);
		addToList(r, g, b, a);
	}
	
	private void addToFile(int r, int g, int b, int a){
		writer.println(r + "," + g + "," + b + "," + a);
		writer.flush();
		
	}
	
	private void addToList(int r, int g, int b, int a){
		  ListBoxItem lbi = savedColors.addItem(r + " "+ g + " " + b + " " + a, 0);
		  lbi.setColorBackground(color(r, g, b, a));
		  lbi.setColorLabel(color(255 - r, 255 - g, 255 - b));
	}
	
	private void scanAndAddFromFile(){
		while(scanner.hasNext()){
			String nextLine = scanner.nextLine();
			String[] parts = nextLine.split(",");
			addToList(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
		}
	}
}
