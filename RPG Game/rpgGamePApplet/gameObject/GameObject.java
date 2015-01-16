package gameObject;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import sprite.Sprite;

public class GameObject {

	private PVector pos;
	private float width, height;
	private Sprite sprite;
	private String name;
	private float health = 100f;
	private boolean canMove = true;
	private PApplet parent;
	
	public GameObject(PApplet p, PVector pos, float width, float height, String name) {
		this.parent = p;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.sprite = new Sprite(parent, 0.20f);
		this.name = name;
	}
	
	public GameObject(PApplet p, float x, float y, float width, float height, String name){
		this(p, new PVector(x, y), width, height, name);
	}
	
	public void draw(PApplet parent){
		//System.out.println(sprite.down.size());
		
		parent.image(sprite.currentImg(), pos.x, pos.y, width, height);
		
	
		parent.textSize(10);
		parent.fill(0, 0, 0);
		parent.text(toString(), pos.x - width/2, pos.y - 15);
	}
	
	public void update(PApplet parent){
		sprite.update();
	}
	
	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
	public void moveBy(float x, float y){
		if(this.canMove){
			this.pos.x += x;
			this.pos.y += y;
		}
		if(x == 0){
			if(y > 0)//down
				this.sprite.setDirection(FourSprite.DOWN);
			else
				this.sprite.setDirection(FourSprite.UP);
		}
		
		else
		{
			if(x > 0) //right
				this.sprite.setDirection(FourSprite.RIGHT);
			else
				this.sprite.setDirection(FourSprite.LEFT);
		}
	}
	
	public void addUp(PImage i) {
		this.sprite.addUp(i);
	}

	public void addDown(PImage i) {
		this.sprite.addDown(i);
	}

	public void addLeft(PImage i) {
		this.sprite.addLeft(i);
	}

	public void addRight(PImage i) {
		this.sprite.addRight(i);
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
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public PImage getCurrentImage(){
		return this.sprite.nextImage();
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

	public String toString(){
		return name + " (" + pos.x + ", " + pos.y + " w: " + width + " h: " + height;
	}
	
}
