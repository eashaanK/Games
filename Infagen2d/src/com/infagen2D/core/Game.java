package com.infagen2D.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.infagen2D.components.InputHandler;
import com.infagen2D.components.WindowHandler;
import com.infagen2D.entities.Entity;
import com.infagen2D.entities.Player;
import com.infagen2D.entities.PlayerMP;
import com.infagen2D.graphics.Screen;
import com.infagen2D.graphics.SpriteSheet;
import com.infagen2D.level.Level;
import com.infagen2D.networking.GameClient;
import com.infagen2D.networking.GameServer;

/**
 * VOID , STONE, GRASS, SAND,
 * WATER(1, 2, 3),  LAVA(1, 2, 3)
https://www.youtube.com/watch?v=7Qcg6Hvx_WU&index=18&list=ELp5mgUw5g9EY 10:12
 * @author eashaan
 * 
 * for levels:
 * 555555 = void/grey
 * 00ff00 = grass
 * 
 *
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 5;
    public static final String NAME = "Game";
    public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    public static Game game;

    public JFrame frame;

    private Thread thread;

    public boolean running = false;
    public int tickCount = 0;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int[] colours = new int[6 * 6 * 6];

    private Screen screen;
    public InputHandler input;
    public WindowHandler windowHandler;
    public Level level;
    public Player player;

    public GameClient socketClient;
    public GameServer socketServer;

    public boolean debug = true;
    public boolean isApplet = false;
    
    private List<String> messagesOnScreen = new ArrayList<String>();

    public void init() {
        game = this;
        int index = 0;
        for (int r = 0; r < 6; r++) {
            for (int g = 0; g < 6; g++) {
                for (int b = 0; b < 6; b++) {
                    int rr = (r * 255 / 5);
                    int gg = (g * 255 / 5);
                    int bb = (b * 255 / 5);

                    colours[index++] = rr << 16 | gg << 8 | bb;
                }
            }
        }
        screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
        input = new InputHandler(this);
        level = new Level(null);
        player = new PlayerMP(level, socketClient.username, socketClient.spawnX, socketClient.spawnX, input, 
                null, -1);
        level.addEntity(player);
        
        if (!isApplet) {
        	this.socketClient.begin(JOptionPane.showInputDialog("Host IP Address:"));
        }
    }

    public synchronized void start() {
        running = true;

        thread = new Thread(this, NAME + "_main");
        thread.start();
        if (!isApplet) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to run the server") == 0) {
                socketServer = new GameServer();
                socketServer.start();
                socketServer.showDebugger(true);
            }
            

            socketClient = new GameClient(JOptionPane.showInputDialog(this, "Please enter a username"), this, 100, 100);
        }
    }

    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
    	this.displayMessage("Welcome!");
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                debug(DebugLevel.INFO, ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        tickCount++;
        level.tick();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        int xOffset = player.x - (screen.width / 2);
        int yOffset = player.y - (screen.height / 2);

        level.renderTiles(screen, xOffset, yOffset);
        level.renderEntities(screen);

        for (int y = 0; y < screen.height; y++) {
            for (int x = 0; x < screen.width; x++) {
                int colourCode = screen.pixels[x + y * screen.width];
                if (colourCode < 255)
                    pixels[x + y * WIDTH] = colours[colourCode];
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
      //  drawMessageScreen(g);
        
        g.dispose();
        bs.show();
    }

    private void drawMessageScreen(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		int x = 10;
		int y = 10;
		int width = this.frame.getWidth()/3;
		int height = this.frame.getHeight() - 50;
		g.fillRect(x, y, width, height);
		int fontSize = width/17;
		g.setFont(new Font("SanSerif", Font.PLAIN, fontSize));
		g.setColor(Color.white);
		for(int i = this.messagesOnScreen.size() - 1; i >= 0; i--){
			g.drawString(messagesOnScreen.get(i), x + 1, y+height - i * fontSize);
		}
	}

	public static long fact(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    public void debug(DebugLevel level, String msg) {
        switch (level) {
        default:
        case INFO:
            if (debug) {
                System.out.println("[" + NAME + "] " + msg);
            }
            break;
        case WARNING:
            System.out.println("[" + NAME + "] [WARNING] " + msg);
            break;
        case SEVERE:
            System.out.println("[" + NAME + "] [SEVERE]" + msg);
            this.stop();
            break;
        }
    }
    
    public synchronized List<Entity> getEntities(){
    	return this.level.getEntities();
    }
    
    public synchronized void addEntity(PlayerMP player){
    	this.level.addEntity(player);
    }
    
    public synchronized void removeEntity(int i){
    	this.level.getEntities().remove(i);
    }
    
    public synchronized void displayMessage(String message){
    	System.out.println("DISPLAY MESSAGE ON SCREEN: " + message);
    	messagesOnScreen.add(message);
    }
    
    public synchronized void setEntity(PlayerMP player, int i){
    	this.level.getEntities().set(i, player);
    }

    public static enum DebugLevel {
        INFO, WARNING, SEVERE;
    }
}