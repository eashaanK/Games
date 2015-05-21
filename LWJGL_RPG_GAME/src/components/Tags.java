package components;

import java.util.ArrayList;

public class Tags {

	public static ArrayList<String> tags = new ArrayList<String>();
	public static final String DEFAULT = "default";
	public static final String PLAYER = "player";
	public static final String MAIN_CAMERA = "mainCamera";
	public static final String ENEMY = "enemy";

	
	public static void setUpTags(){
		tags.add(DEFAULT);
		tags.add(PLAYER);
		tags.add(MAIN_CAMERA);
		tags.add(ENEMY);
	}
	
	public static void addTag(String tag){
		tags.add(tag);
	}
	
	public static ArrayList getTags(){
		return tags;
	}
}
