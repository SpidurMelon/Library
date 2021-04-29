package en.lib.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * 
 * @author SpidurMelon
 *
 */
public class IO {
	/**
	 * A function for getting the contents of a file
	 * @param path
	 * The path of the file to read from
	 * @return
	 * A string containing all the contents of a file
	 */
	public static String readFile(String path) {
		String result = "";
		try {
			FileReader reader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(reader);
			
			String line;
			while ((line = bReader.readLine()) != null) {
				result += line + "\n";
			}
			
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * A function for writing a string to a file, note that it overrides the file's current contents
	 * @param path
	 * The path of the file to write to
	 * @param string
	 * The string to write to the file
	 */
	public static void writeFile(String path, String string) {
		try {
			FileWriter writer = new FileWriter(path);
			BufferedWriter bWriter = new BufferedWriter(writer);
			
			bWriter.write(string);
			
			bWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * A function for extracting a single line from a string
	 * @param string
	 * A string containing the line you want
	 * @param line
	 * The index of the line you want to extract
	 * @return
	 * The line of text on the given index 
	 */
	public static String getLine(String string, int line) {
		String result;
		int bIndex = 0, eIndex = string.indexOf("\n");
		for (int i = 0; i < line; i++) {
			bIndex = string.indexOf("\n", bIndex+1);
			eIndex = string.indexOf("\n", bIndex+1);
		}
		result = string.substring(bIndex, eIndex);
		return result;
	}
	/**
	 * A function for getting a number from a string
	 * @param string
	 * The string containing your number
	 * @param index
	 * The index of the number you want to extract
	 * @return
	 * The number at the index given
	 */
	public static int getNumber(String string, int index) {
		String spaceString = " " + string.replaceAll("[^0-9]", " ") + " ";
		String spaceStringN = spaceString.replaceAll("[0-9]", "N");
		return Integer.valueOf(spaceString.substring(ordinalIndexOf(spaceStringN, " N", index)+1, ordinalIndexOf(spaceStringN, "N ", index)+1));
	}
	
	/**
	 * 
	 */
	public static ArrayList<String> readStringArray(String string) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s:string.split(",")) {
			result.add(s);
		}
		return result;
	}
	/**
	 * A function for turning a string into an array of numbers of all the positive numbers contained in that string
	 * @param string
	 * The string containing an array of numbers
	 * @return
	 * The array of numbers contained in the given string
	 */
	public static ArrayList<Integer> readPositiveArray(String string) {
		String spaceString = " " + string.replaceAll("[^0-9]", " ") + " ";
		String spaceStringN = spaceString.replaceAll("[0-9]", "N");
		
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		int startIndex = 0;
		int endIndex = 0;
		while (true) {
			startIndex = spaceStringN.indexOf(" N", endIndex)+1;
			endIndex = spaceStringN.indexOf("N ", endIndex)+1;
			if (endIndex <= startIndex || endIndex == -1 || startIndex == -1) {
				break;
			}
			ints.add(Integer.valueOf(spaceString.substring(startIndex, endIndex)));
		}
		return ints;
	}
	/**
	 * A function for turning a string into an array of numbers of all the positive and negative numbers contained in that string
	 * @param string
	 * The string containing an array of numbers
	 * @return
	 * The array of numbers contained in the given string
	 */
	public static ArrayList<Integer> readPosNegArray(String string) {
		String spaceString = "+" + string.replaceAll("[^0-9-+]", "+") + "+";
		String spaceStringN = spaceString.replaceAll("[0-9]", "N");
		
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		int startIndex = 0;
		int endIndex = 0;
		while (true) {
			if (spaceStringN.indexOf("-N", endIndex) == -1 || spaceStringN.indexOf("+N", endIndex) < spaceStringN.indexOf("-N", endIndex)) {
				startIndex = spaceStringN.indexOf("+N", endIndex);
			} else {
				startIndex = spaceStringN.indexOf("-N", endIndex);
			}
			if (spaceStringN.indexOf("N-", endIndex) == -1 || spaceStringN.indexOf("N+", endIndex) < spaceStringN.indexOf("N-", endIndex)) {
				endIndex = spaceStringN.indexOf("N+", endIndex)+1;
			} else {
				endIndex = spaceStringN.indexOf("N-", endIndex)+1;
			}
			if (endIndex <= startIndex || endIndex == -1 || startIndex == -1) {
				break;
			}
			ints.add(Integer.valueOf(spaceString.substring(startIndex, endIndex)));
		}
		return ints;
	}
	/**
	 * A function to get position of the nth index of a substring inside of another string
	 * @param str
	 * String containing your substring
	 * @param substr
	 * The substring to search for
	 * @param n
	 * The index of the substring you want to search for
	 * @return
	 * The position of the nth substring
	 */
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n >= 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
	
	/**
	 * Function to get a property from a file, properties need to be in the format "property=[value];"
	 * @param property
	 * Name of the property you want to search for
	 * @param file
	 * File which contains your property
	 * @return
	 * The value of the given property
	 */
	public static String getPropertyValue(String property, File file) {
		return getPropertyValue(property, IO.readFile(file.getPath()));
	}
	
	/**
	 * Function to get a property from a string, properties need to be in the format "property=[value];"
	 * @param property
	 * Name of the property you want to search for
	 * @param string
	 * String which contains your property
	 * @return
	 * The value of the given property
	 */
	public static String getPropertyValue(String property, String string) {
		String value;
		if (!string.contains(property + "=")) {
			return null;
		}
		int valueStartIndex = string.indexOf(property + "=") + property.length()+1;
		int valueEndIndex = string.indexOf(";", valueStartIndex);
		value = string.substring(valueStartIndex, valueEndIndex);
		return value;
	}
	
	/**
	 * Function to write a value to a specific property in a file, if the property does not exist it will create it
	 * @param property
	 * The name of the property you want to write to
	 * @param newValue
	 * The value you want to replace the current value with
	 * @param path
	 * The path of the file you want to write to
	 */
	public static void writePropertyValue(String property, String newValue, String path) {
		String string = readFile(path);
		if (string.contains(property)) {
			string = string.replaceAll("(?s)" + property + "=.*?;", property + "=" + newValue + ";");
		} else {
			string += "\n" + property + "=" + newValue + ";";
		}
		writeFile(path, string);
	}
}
