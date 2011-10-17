/**
 *  wrongPowder is developed by wrong-entertainment & powder
 *
 *
 *  Copyright 2011 Paul Vollmer & Tim Pulver
 *  paulvollmer.net
 *  vollmerpaul@yahoo.de
 *  timpulver.de
 *  pulver.tim@googlemail.com
 * 
 *  This file is part of wrongPowder library.
 *
 *  wrongPowder is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with wrongPowder. If not, see <http://www.gnu.org/licenses/>.
 * 
 *  @author		##author##
 *  @modified	##date##
 *  @version	##version##
 */

package wrongPowder.config;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * This is a template class and can be used to start a new processing library or tool.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own lobrary or tool naming convention.
 * 
 * @example configBasic 
 * 
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 */

public class config {

	public Properties props = new Properties();
	public String filePath;
	
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @example configBasic
	 * @param theParent
	 */
	public config() {}
	
	public config(String folderName, String fileName) {
		load(folderName, fileName);
	}
	
	
	
	/**
	 * load
	 * Check the Platform and load a Configuration File.
	 *
	 * @param folderName Name of the folder (Use your application name)
	 * @param fileName Name of the config file
	 */
	public void load(String folderName, String fileName) {  //@Tim: changed constructor
		// check Platform and save Filepath to Variable.
		
		// WINDOWS
		if(isWindows()) {
			try {
				filePath = System.getenv("APPDATA") + File.separator + folderName + File.separator + fileName;
			}
			catch(Exception e) {
				System.err.println("Environment variable APPDATA could not be read");
			}
		}
		// MACOSX
		// On MacOS we use the User Application Support Folder to search and store the files.
		// Path: /Users/NAME/Library/Application Support/APPNAME/FILENAME.SUFFIX
		else if(isMac()) {
			filePath = File.separator + "Users" + File.separator + System.getProperty("user.name") + File.separator
			+ "Library" + File.separator + "Application Support" + File.separator + folderName + File.separator + fileName;
		}
		// LINUX
		else if(isUnix()) {	
			filePath = ""; // TODO: Add Filepath
		}
		// OTHER
		else {
			System.err.println("Operating System not supported");
		}

		checkFile();
	}
	
	
	
	/**
	 * loadStatic
	 * Load a Configuration file from a static path.
	 * 
	 * @param path The Filepath.
	 */
	public void loadStatic(String path) {
		filePath = path;
		checkFile();
	}
	
	
	
	/**
	 * checkFile
	 * Private method to check if Configuration File exists.
	 * If no file is found, create a file at the directory.
	 */
	private void checkFile() {
		try {
			System.out.println("checkFile(): Checking file " + filePath);
			File file = new File(filePath);
			if (file.exists()) {
				try {
					FileInputStream in = new FileInputStream(filePath);
					props.load(in);
					System.out.println("checkFile(): Config file successfully loaded.");
					// Application counter
					int appCount = Integer.parseInt(props.getProperty("app.count", ""+0));
					appCount ++;
					props.setProperty("app.count", ""+appCount );
					store(filePath);
				}
				catch(IOException e) {
					System.err.println("checkFile(): File could not be loaded");
				}
			}
			else {  // File does not exist 
				System.out.println("checkFile(): Configuration File does not exist. Create an empty File.");
				// Create base directory
				File folder = new File(file.getParent());
				folder.mkdirs();
				// create config file
				file.createNewFile();
				// write default config data to config file
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
					String[] confText = getDefaultConfigText();
					for (int i=0; i<confText.length; i++) {
						out.write(confText[i]);
						if (i < confText.length -1)  //New newline at last line
							out.newLine();  
					}
					out.close();
				}
				catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	
	
	private String[] getDefaultConfigText() {
		// set Configuration text.
		String[] conf = { "# "+time(),
				          "app.count=1",
				          "app.width=300",
				          "app.height=300"};
		return conf;
	}
	
	
	
	/**
	 * store
	 * Method to save the Configuration File,
	 *
	 * @param path The Filepath.
	 */
	public void store(String path) {
		try {
			FileOutputStream out = new FileOutputStream(path);
			props.store(out, path+" saved at:");
			out.close();
		}
		catch(IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * store
	 * Method to save the Configuration File,
	 */
	public void store() {
		store(filePath);
	}
	
	
	
	/**
	 * getSize
	 * 
	 * @return int
	 */
	public int getSize() {
		int s = props.size();
		return s;
	}
	
	
	
	/**
	 * list
	 * 
	 * Get a list of all Property Elements. Print to console.
	 */
	public void list() {
		props.list(System.out);
	}
	
	
	
	/**
	 * getIntProperty
	 * 
	 * @param key The Property key name.
	 * @param defVal The default value.
	 * @return
	 */
	public int getIntProperty(String key, int defVal) {
		int i = Integer.parseInt(props.getProperty(key, ""+defVal));
		return i;
	}
	
	/**
	 * getFloatProperty
	 * 
	 * @param key The Property key name.
	 * @param defVal The default value.
	 * @return
	 */
	public float getFloatProperty(String key, float defVal) {
		float f = Float.parseFloat(props.getProperty(key, ""+defVal));
		return f;
	}
	
	/**
	 * getStringProperty
	 * 
	 * @param key The Property key name.
	 * @param defVal The default value.
	 * @return
	 */
	public String getStringProperty(String key, String defVal) {
		String f = props.getProperty(key, defVal);
		return f;
	}
	
	/**
	 * getBooleanProperty
	 * 
	 * @param key The Property key name.
	 * @param defVal The default value.
	 * @return
	 */
	public boolean getBooleanProperty(String key, boolean defVal) {
		boolean f = Boolean.parseBoolean(props.getProperty(key, ""+defVal));
		return f;
	}
	
	
	
	/**
	 * setProperty
	 * 
	 * @param key The Property key name.
	 * @param val The value to set.
	 */
	public void setProperty(String key, int val) {
		props.setProperty(key, ""+val);
	}
	
	
	
	private String time() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String ts = sdf.format(new Date());
		return ts;
	}
	
	
	private static boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		//windows
	    return (os.indexOf( "win" ) >= 0); 
	}
 
	private static boolean isMac() {
		String os = System.getProperty("os.name").toLowerCase();
		//Mac
	    return (os.indexOf( "mac" ) >= 0); 
	}
 
	private static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		//linux or unix
	    return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
	}
	
	
}