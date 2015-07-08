package com.enums;



public enum Command {

	INVALID(-1), BROWSE_WEB(00), OPEN_FILE(1), PRINT_FILE(2), EDIT_FILE(3), WRITE_EMAIL(4);
	
	private int commandID;
	private Command(int commandID){
		this.commandID = commandID;
	}
	
	public int getID()
	{
		return this.commandID;
	}
	
	public static Command lookupCommand(String id) {
		try {
			return lookupPacket(Integer.parseInt(id));

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Command.INVALID;
		}
	}
	
	public static Command lookupPacket(int id) {
		for (Command p : Command.values()) {
			if (p.getID() == id)
				return p;
		}

		return Command.INVALID;
	}
}
