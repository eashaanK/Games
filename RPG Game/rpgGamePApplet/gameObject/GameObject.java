package gameObject;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import rpg_game_helpers.Sprite;
import sprite.SpriteSheet;

public class GameObject {

	protected PVector pos;
	protected float width, height;
	protected SpriteSheet sprite;
	protected String name;
	protected float health = 100f;
	protected boolean canMove = true;
	protected PApplet parent;
	
	public GameObject(PApplet p, String spritePath, PVector pos, float width, float height, String name) {
		this.parent = p;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.sprite = new SpriteSheet(parent,spritePath, width, height, 0.20f);
		this.name = name;
	}
	
	public GameObject(PApplet p, String spritePath, float x, float y, float width, float height, String name){
		this(p, spritePath, new PVector(x, y), width, height, name);
	}
	
	public void draw(){
		
		if(!this.checkAlive()){
			return;
		}
		
		sprite.draw(pos.x, pos.y);
		parent.textSize(10);
		parent.fill(0, 0, 0);
		parent.text(toString(), pos.x - width/2, pos.y - 15);
	}
	
	public void update(){
		if(!this.checkAlive()){
			return;
		}
	}
	
	public boolean checkAlive(){
		return health > 0;
	}
	
	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
	public void moveBy(float x, float y){
		
		if(!this.checkAlive()){
			return;
		}
		
		if(this.canMove){
			this.pos.x += x;
			this.pos.y += y;
		}
		if(x == 0){
			if(y > 0)//down
				sprite.chaneToDown();
			else if(y < 0)//up
				sprite.chaneToUp();
		}
		
		if(y == 0){
			if(x > 0)//right
				sprite.chaneToRight();
			else if(x < 0)//left
				sprite.chaneToLeft();
		}
		
		sprite.update();
	}
	
	public void setCanMove(boolean cm){
		this.canMove = cm;
	}
	
	public float X(){
		return pos.x;
	}
	
	public float Y(){
		return pos.y;
	}
	
	public boolean canMove(){
		return this.canMove;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.sprite.setWidth(width);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.sprite.setHeight(height);
	}

	public SpriteSheet getSprite() {
		return sprite;
	}

	public void setSprite(String path, float width, float height, float speed) {
		this.sprite = new SpriteSheet(parent, path, width, height, speed);
	}

	public PImage getCurrentImage(){
		return this.sprite.currentImage();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void reduceHealth(float amt){
		this.health -= amt;
	}
	
	public float getHealth(){
		return health;
	}
	
	protected void setHealth(float h){
		this.health = h;
	}
	public PApplet getParent(){
		return this.parent;
	}

	public String toString(){
		return name + " (" + pos.x + ", " + pos.y + " w: " + width + " h: " + height;
	}
	
}
