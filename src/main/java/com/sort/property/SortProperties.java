package com.sort.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.commons.io.FileUtils;

/**
 * The main class, having a main method & sort method for the property file
 * 
 * @author abid.khan
 *
 */
public class SortProperties {

//	static String BUILD_PROPERTY_FILE = "build.properties";
	
	/**
	 * Entry point of the project
	 *  
	 * @param args File name as a command line argument
	 * @throws FileNotFoundException File missing exception throws in runtime
	 * @throws IOException Compile time I/O exception
	 * @throws ConfigurationException Throws error while applying PropertyConfigurationLayout
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ConfigurationException {
		if (args.length == 0) {
			System.out.println("Provide property file...exiting!!");
			System.exit(0);
		} 
		else if (args[0].endsWith(".properties")) {
			System.out.println("Started sorting ... ");
			sortProperties(args[0].trim());
			System.out.println("Done..");
		}
//		sortProperties("gradle.properties");
	}

	/**
	 * This method does the sorting of the property file with Alphabet comments for each new property with the new letter
	 * 
	 * @param inputFile Input property file
	 * @throws ConfigurationException Throws error while applying PropertyConfigurationLayout
	 * @throws IOException I/O exception
	 */
	public static void sortProperties(String inputFile) throws ConfigurationException, IOException {

		// keeping backup of input file
//		File providedPropFile= File.createTempFile(inputFile.substring(0, inputFile.indexOf(".properties"))+"-old",".properties");
		File providedPropFile = new File(inputFile);
		FileUtils.copyFile(providedPropFile, new File(inputFile.substring(0, inputFile.indexOf(".properties"))+"-old.properties"));
		
		// Reading property file
		Properties prop = new Properties();
		prop.load(new FileInputStream(inputFile));
		
		// Getting keys in order
		Set<String> set = new TreeSet<String>(new CustomComaparator());
		for(Object key: prop.keySet())
			set.add(key.toString());
		
		// Converting Set to ArrayList to access element by index
		List<String> list = new ArrayList<String>();
		list.addAll(set);

		boolean firstLine = true;
		
		PropertiesConfigurationLayout propLayout = new PropertiesConfigurationLayout(new PropertiesConfiguration(inputFile));
		
		// Looping through each property stored in Array List & applies conditions to sort them and add comments
		for(int i=1; i< list.size(); i++){
//			System.out.println(list.get(i-1));

			if (firstLine) {
				propLayout.setComment(list.get(i - 1), " #######\n #  " + list.get(i - 1).toUpperCase().charAt(0) + "  #\n #######\n");
				firstLine = false;
			}

			else if (list.get(i - 1).substring(0, 1).equalsIgnoreCase(list.get(i).substring(0, 1))) {
				propLayout.setSingleLine(list.get(i - 1), true);
				propLayout.setSingleLine(list.get(i), true);
			} 
			
			else {
				propLayout.setSingleLine(list.get(i - 1), true);
				propLayout.setComment(list.get(i), "\n #######\n #  " + list.get(i).toUpperCase().charAt(0) + "  #\n #######\n");
			}
		}
		/*propLayout.setHeaderComment("##############################\n"
				+ "## - PLEASE KEEP THE DEPENDENCIES IN RIGHT ORDER\n"
				+ "## - PLEASE ENSURE THAT THERE IS NO SPACE OR TAB CHARACTER AT THE END\n"
				+ "##############################");*/
		
		propLayout.save(new FileWriter(new File(inputFile)));		// output file
	}
}

/**
 * Custom Comparator class, compares two objects (string) by ignoring the cases
 * 
 * @author abid.khan
 *
 */
class CustomComaparator implements Comparator<String>{
	/**
	 * implementation of compare() method
	 */
	public int compare(String o1, String o2) {
		return o1.compareToIgnoreCase(o2);
	}
}
