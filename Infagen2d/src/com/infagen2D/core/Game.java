package com.infagen2D.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.infagen2D.components.InputHandler;
import com.infagen2D.components.Ref;
import com.infagen2D.entities.Player;
import com.infagen2D.graphics.Screen;
import com.infagen2D.graphics.SpriteSheet;
import com.infagen2D.level.Level;
import com.infagen2D.network.GameClient;
import com.infagen2D.network.GameServer;

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
    
    private boolean shouldDrawDebugScreen = true;

    private JFrame frame;

    public boolean running = false;
    public int tickCount = 0;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                    BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
                    .getData();
    private int[] colors = new int[6 * 6 * 6];

    private Screen screen;
    public InputHandler input;
    public Level level;
    public Player player;
    
    public int globalTicks, globalFrames;
    
    private GameClient socketClient;
    private GameServer socketServer;
    
    public Game() {
            setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
            setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
            setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

            frame = new JFrame(NAME);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(this, BorderLayout.CENTER);
            frame.pack();

            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }

    public void init() {
            int index = 0;
            for (int r = 0; r < 6; r++) {
                    for (int g = 0; g < 6; g++) {
                            for (int b = 0; b < 6; b++) {
                                    int rr = (r * 255 / 5);
                                    int gg = (g * 255 / 5);
                                    int bb = (b * 255 / 5);

                                    colors[index++] = rr << 16 | gg << 8 | bb;
                            }
                    }
            }

            screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/SpriteSheet.png"));
            input = new InputHandler(this);
            ///level = new Level("/Levels/Water_Test_Level.png");
            level = new Level(null);

            player = new Player(level,JOptionPane.showInputDialog("Enter Name:"), 0, 0, input);
            level.addEntity(player);
            //PLAYER MUST BE THE FIRST ENTITY
            
            System.out.println("SEED: " + Ref.SEED);
            
            socketClient.sendData("ping".getBytes());
    }

    public synchronized void start() {
            running = true;
            new Thread(this).start();
            
            if(JOptionPane.showConfirmDialog(this, "Run Server?") == 0){
            	socketServer = new GameServer(this);
            	socketServer.start();
            }
            
            socketClient = new GameClient(this, "localhost");
            socketClient.start();
    }

    public synchronized void stop() {
            running = false;
    }

    public void run() {
            long lastTime = System.nanoTime();
            long lastTimer = System.currentTimeMillis();
            double nsPerTick = 1000000000D / 60D;
            double delta = 0;
            int ticks = 0;
            int frames = 0;

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
                           // System.out.println(ticks + " ticks , " + frames+ " frames per second");
                            //this.frame.setTitle(NAME + " " + ticks + " ticks , " + frames+ " frames per second");
                            globalTicks = ticks;
                            globalFrames = frames;
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

           /* for (int x = 0; x < level.width; x++) {
                    int colour = Colors.get(-1, -1, -1, 000);
                    if (x % 10 == 0 && x != 0) {
                            colour = Colors.get(-1, -1, -1, 500);
                    }
                    FunFont.render((x % 10) + "", screen, 0 + (x * 8), 0, colour, 1);
            }*/

            level.renderEntities(screen);

            for (int y = 0; y < screen.height; y++) {
                    for (int x = 0; x < screen.width; x++) {
                            int ColourCode = screen.pixels[x + y * screen.width];
                            if (ColourCode < 255) {
                                    pixels[x + y * WIDTH] = colors[ColourCode];

                            }
                    }
            }

            Graphics g = bs.getDrawGraphics();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            //health
            drawHealth(g);
            drawDebugScreen(g);
            g.dispose();
            bs.show();
    }
    
    private void drawDebugScreen(Graphics g){
    	int x = 0, y = 0;
    	if(input.debug.isPressed()){
    		shouldDrawDebugScreen = false;
    	}else shouldDrawDebugScreen = true;
    	
    	if(shouldDrawDebugScreen){
    		g.setColor(new Color(0, 0, 0, 120));
    		g.fillRect(x, y, frame.getWidth()/4, frame.getWidth()/4);
    		g.setColor(Color.white);
        	g.setFont(new Font(Font.SANS_SERIF , Font.BOLD, 20));
        	g.drawString("Ticks: " + this.globalTicks, x + 20, y + 25);
        	g.drawString("Frames: " + this.globalFrames, x + 20, y + 50);

        	//g.drawString( GlobalTicks + " ticks , " + frames+ " frames per second", x, y);
    	}
    	
	//	System.out.println(this.shouldDrawDebugScreen);

    }
    
    private void drawHealth(Graphics g){
    	String h = (int)player.getHealth() + "";
    	int fontSize = 50;
    	int x =  frame.getWidth() / 2 - (h.length() * fontSize)/2;
    	int y =  frame.getHeight()  - 100;
    	
    	g.setColor(Color.white);
    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    	
        g.drawString( h, x, y);
        

    }

    public static void main(String[] args) {
            new Game().start();
    }

}
