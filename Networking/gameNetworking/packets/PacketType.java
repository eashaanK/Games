package packets;

public enum PacketType {

	INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02), MESSAGE(03);
	
	private int packetID;
	private PacketType(int packetID){
		this.packetID = packetID;
	}
	
	public int getID()
	{
		return this.packetID;
	}
}
