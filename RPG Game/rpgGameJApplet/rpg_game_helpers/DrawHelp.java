package rpg_game_helpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import rpg_game_main.RPGMain;

public class DrawHelp {

	public static void drawText(Graphics g, int size, String str, Point pos){
		g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), size));
		g.drawString(str, pos.x, pos.y);
	}
	
	public static void drawText(Graphics g, int size, String str, int x, int y){
		g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), size));
		g.drawString(str, x, y);
	}
	
	public static void drawFixedText(Graphics g, String text, Color c, int x, int y, int anchorX, int anchorY){
		g.setColor(c);
		Point temp = getFixedPoint(x, y, anchorX, anchorY);
		DrawHelp.drawText(g, 20, text, temp);
	}
	
	public static void drawFixedText(Graphics g, String text, Color c, int x, int y, int anchorX, int anchorY, int size){
		g.setColor(c);
		Point temp = getFixedPoint(x, y, anchorX, anchorY);
		DrawHelp.drawText(g, size, text, temp);
	}
	
	public static void fillFixedRect(Graphics g, Color c, int x, int y, int width, int height, int anchorX, int anchorY){
		g.setColor(c);
		Point temp = getFixedPoint(x, y, anchorX, anchorY);
		g.fillRect(temp.x, temp.y, width, height);
	}
	
	public static Point getFixedPoint(int x, int y, int anchorX, int anchorY){
		return new Point(anchorX - (RPGMain.WIDTH / 2 - x ), anchorY - (RPGMain.HEIGHT / 2 - y ));
	}
}
