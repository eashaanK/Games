package com.AI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class DesktopManager {

	private Desktop desktop = null;
	private final Pattern SIMPLE_CHARS = Pattern.compile("[a-zA-Z0-9]");

	public DesktopManager() {
		if (!Desktop.isDesktopSupported()) {
			System.err.println("Desktop is not supported");
			JOptionPane
					.showMessageDialog(
							null,
							"Desktop Manger cannot be created on this computer",
							"The use of applications through the Desktop Manager is not allowed",
							JOptionPane.ERROR_MESSAGE);
			return;
		}
		desktop = Desktop.getDesktop();
	
	}

	/**
	 * Browses a web site based on the URL.
	 * 
	 * @param urlString
	 * @return 0 if it worked, 1 if wrong syntax url, 2 if something really went
	 *         wrong
	 */
	public byte browse(String urlString) {
		URI uri;
		try {
			uri = new URI(urlString);
			desktop.browse(uri);
			return 0;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * Composes an Email
	 * @return 0 if succeeded, 1 if wrong url/emails, 2 if other error
	 */
	public byte composeEmail(String notSubject, String notGreeting, String notMessageBody, String notEnding, ArrayList<String> emails){
		String subject = getEmailSubject(notSubject);
		String messageBody = getEmailBody(notGreeting, notMessageBody, notEnding);
		String emailList = emails.get(0);
		for(int i = 1; i < emails.size(); i++)
		{
			emailList += "," +  emails.get(i);
		}
		String url = "mailTo:" + emailList + "?subject=" + subject + "&body=" + messageBody;
		URI mailTo;
		try {
			mailTo = new URI(url);
			desktop.mail(mailTo);
			return 0;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 2;
		} 
	}
	
	/**
	 * Opens an application on this computer
	 * @param filepath
	 * @return -1 if file does not exit, 0 success, 1 other error
	 */
	public byte openApplication(String filepath){
		if(filepath.charAt(0) != '/')
			filepath = "/" + filepath;
		Path path = Paths.get(filepath);
		File file = path.toFile();
		if(!file.exists()){
			System.err.println(filepath + " Does not Exist");
			return -1;
		}
		else{
			try {
				desktop.open(file);
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 1;
			}
		}
	}
	
	
	/**
	 * Opens an application on this computer
	 * @param filepath
	 * @return -1 if file does not exit, 0 success, 1 other error
	 */
	public byte printApplication(String filepath){
		if(filepath.charAt(0) != '/')
			filepath = "/" + filepath;
		Path path = Paths.get(filepath);
		File file = path.toFile();
		if(!file.exists()){
			System.err.println(filepath + " Does not Exist");
			return -1;
		}
		else{
			try {
				desktop.print(file);
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 1;
			}
		}
	}
	
	/**
	 * Opens an application on this computer
	 * @param filepath
	 * @return -1 if file does not exit, 0 success, 1 other error
	 */
	public byte editApplication(String filepath){
		if(filepath.charAt(0) != '/')
			filepath = "/" + filepath;
		Path path = Paths.get(filepath);
		File file = path.toFile();
		if(!file.exists()){
			System.err.println(filepath + " Does not Exist");
			return -1;
		}
		else{
			try {
				desktop.edit(file);
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 1;
			}
		}
	}

	//ALL FOR EMAILS
	
	private String getEmailSubject(String subject) {
		return encodeUnusualChars(subject);
	}

	private String getEmailBody(String greeting, String body, String end) {
		StringBuilder result = new StringBuilder();
		String NL = System.getProperty("line.separator");
		result.append(greeting);
		result.append(NL);
		result.append(NL);
		// exercises a range of common characters :
		result.append(body);
		result.append(NL);
		result.append(NL);
		result.append(end);

		return encodeUnusualChars(result.toString());
	}

	/**
	 * This is needed to handle special characters. This method hasn't been
	 * tested with non-Latin character sets.
	 * 
	 * Encodes all text except characters matching [a-zA-Z0-9]. All other
	 * characters are hex-encoded. The encoding is '%' plus the hex
	 * representation of the character in UTF-8.
	 * 
	 * <P>
	 * See also : http://tools.ietf.org/html/rfc2368 - mailto
	 * http://tools.ietf.org/html/rfc1738 - URLs
	 */
	private String encodeUnusualChars(String aText) {
		StringBuilder result = new StringBuilder();
		CharacterIterator iter = new StringCharacterIterator(aText);
		for (char c = iter.first(); c != CharacterIterator.DONE; c = iter
				.next()) {
			char[] chars = { c };
			String character = new String(chars);
			if (isSimpleCharacter(character)) {
				result.append(c);
			} else {
				hexEncode(character, "UTF-8", result);
			}
		}
		return result.toString();
	}

	private boolean isSimpleCharacter(String aCharacter) {
		Matcher matcher = SIMPLE_CHARS.matcher(aCharacter);
		return matcher.matches();
	}

	/**
	 * For the given character and encoding, appends one or more hex-encoded
	 * characters. For double-byte characters, two hex-encoded items will be
	 * appended.
	 */
	private void hexEncode(String aCharacter, String aEncoding,
			StringBuilder aOut) {
		try {
			String HEX_DIGITS = "0123456789ABCDEF";
			byte[] bytes = aCharacter.getBytes(aEncoding);
			for (int idx = 0; idx < bytes.length; idx++) {
				aOut.append('%');
				aOut.append(HEX_DIGITS.charAt((bytes[idx] & 0xf0) >> 4));
				aOut.append(HEX_DIGITS.charAt(bytes[idx] & 0xf));
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

}
