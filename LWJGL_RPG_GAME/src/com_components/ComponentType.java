package com_components;

public enum ComponentType {

	TRANSFORM(0);
	
	private int componentID;
	private ComponentType(int componentID){
		this.componentID = componentID;
	}
	
	public int getID()
	{
		return this.componentID;
	}
}
