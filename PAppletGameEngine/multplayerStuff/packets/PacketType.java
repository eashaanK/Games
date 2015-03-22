package packets;

public enum PacketType {

	INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02), MESSAGE(03), NEW_PERSON_JUST_JOINED(04), 
	OLD_PERSON_WHO_WAS_ALREADY_HERE_BEFORE_U(05), PERSON_DISCONNECTED(06);
	
	private int packetID;
	private PacketType(int packetID){
		this.packetID = packetID;
	}
	
	public int getID()
	{
		return this.packetID;
	}
}
