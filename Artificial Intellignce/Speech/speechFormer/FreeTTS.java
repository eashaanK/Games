package speechFormer;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FreeTTS {

	public static void main(String[] args) throws URISyntaxException, IOException {
	/*	EKVoice speaker = new EKVoice();
		Scanner user = new Scanner(System.in);
		
		String input =  "";
	
		
		while(!input.equals("quit")){
			System.out.print("Message: ");
			
			input = user.nextLine();
			
			speaker.speak(input);

		}*/
		if(!Desktop.isDesktopSupported()){
			System.out.println("Desktop is not supported");
			return;
		}
		
		Desktop desktop = Desktop.getDesktop();
	/*	String urlString = "http://www.javapractices.com/topic/TopicAction.do?Id=242";
	    URI uri = new URI(urlString);
		desktop.browse(uri);*/
		
		/*String filePath = "/Users/eashaan/Desktop/JustMade-it.png";
		Path path = Paths.get(filePath);
		File file = path.toFile();
		if(!file.exists()){
			System.out.println(filePath + " Does not Exist");
			return;
		}
		else
			desktop.open(file);*/
		String subject = getEmailSubject("This is the subject");
		String messageBody = getEmailBody("Hello, ", "This is the body.", "Thanks!");
		String url = "mailTo:eashaan@kumar.co,eashaankumar1@gmail.com" + "?subject=" + subject + "&body=" + messageBody;
		URI mailTo = new URI(url); //log(mailTo);
		desktop.mail(mailTo);
		
	}
	
	//All of this for writing an email
	
	  private static final Pattern SIMPLE_CHARS = Pattern.compile("[a-zA-Z0-9]");

	
	  private static String getEmailSubject(String subject){
		    return encodeUnusualChars(subject);
		  }
		  
	  private static String getEmailBody(String greeting, String body, String end){
	    StringBuilder result = new StringBuilder();
	    String NL = System.getProperty("line.separator"); 
	    result.append(greeting);
	    result.append(NL);
	    result.append(NL);
	    //exercises a range of common characters :
	    result.append(body);
	    result.append(NL);
	    result.append(NL);
	    result.append(end);

	    return encodeUnusualChars(result.toString());
	  }
	  
	  /** 
	   This is needed to handle special characters.
	   This method hasn't been tested with non-Latin character sets.
	   
	   Encodes all text except characters matching [a-zA-Z0-9]. 
	   All other characters are hex-encoded. The encoding is '%' plus the hex 
	   representation of the character in UTF-8. 
	   
	   <P>See also :
	   http://tools.ietf.org/html/rfc2368 - mailto
	   http://tools.ietf.org/html/rfc1738 - URLs
	  */
	  private static String encodeUnusualChars(String aText){
	    StringBuilder result = new StringBuilder();
	    CharacterIterator iter = new StringCharacterIterator(aText);
	    for(char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
	      char[] chars = {c};
	      String character = new String(chars);
	      if(isSimpleCharacter(character)){
	        result.append(c);
	      }
	      else {
	        hexEncode(character, "UTF-8", result);
	      }
	    }
	    return result.toString();
	  }
	  
	  private static boolean isSimpleCharacter(String aCharacter){
		    Matcher matcher = SIMPLE_CHARS.matcher(aCharacter);
		    return matcher.matches();
		  }
		  
		  /**
		   For the given character and encoding, appends one or more hex-encoded characters.
		   For double-byte characters, two hex-encoded items will be appended.
		  */
	  private static void hexEncode(String aCharacter, String aEncoding, StringBuilder aOut) {
	    try  {
	      String HEX_DIGITS = "0123456789ABCDEF"; 
	      byte[] bytes = aCharacter.getBytes(aEncoding);
	      for (int idx = 0; idx < bytes.length; idx++) {
	        aOut.append('%');
	        aOut.append(HEX_DIGITS.charAt((bytes[idx] & 0xf0) >> 4));
	        aOut.append(HEX_DIGITS.charAt(bytes[idx] & 0xf));
	      }
	    }
	    catch (UnsupportedEncodingException ex) {
	      ex.printStackTrace();
	    }
	  }
	

}