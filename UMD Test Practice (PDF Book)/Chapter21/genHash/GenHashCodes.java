package genHash;

public class GenHashCodes {

	/**
	 * For other data types, convert them into classes and gen hashcodes
	 * For ints, use itself as a value. 
	 * @param s
	 * @return
	 */
	public int forString(String s){
		final int HASH_MULTIPLIER = 31;
		int h = 0;
		for(int i = 0; i < s.length(); i++){
			h = HASH_MULTIPLIER * h + s.charAt(i);
		}
		return h;
	}
	
	//NOTE: Use a prime number as a mupltiplier
	public int multipleHashcodes(int h1, int h2){
		final int HASH_MULTIPLIER = 31;
		int h = 0;
		h = HASH_MULTIPLIER * h + h1;
		h = HASH_MULTIPLIER * h + h2;
		return h;
		
	}
}
