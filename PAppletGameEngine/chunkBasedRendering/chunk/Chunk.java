package chunk;

public class Chunk
{

	private final int row, column;
	
	public Chunk(int r, int c){
		this.row = r;
		this.column = c;
	}
	
	public String toString(){
		return row + " " + column;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof Chunk))
			return false;
		
		Chunk c = (Chunk)o;
		
		return c.row == this.row && c.column == this.column && c.toString().equals(this.toString());
	}
	
}
