package rpg_game_helpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import rpg_game_main.RPGMain;

public class DrawHelp {

	public static void drawText(Graphics g, int size, String str, int x, int y){
		g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), size));
		g.drawString(str, x, y);
	}
	
	public static void drawFixedText(Graphics g, String text, Color c, int x, int y, int anchorX, int anchorY){
		g.setColor(c);
		DrawHelp.drawText(g, 20, "FPS: " + text, anchorX - (RPGMain.WIDTH / 2 - x ), anchorY - (RPGMain.HEIGHT / 2 - y ));
	}
}
