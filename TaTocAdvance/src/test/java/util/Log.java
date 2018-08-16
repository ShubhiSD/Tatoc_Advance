package util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {

	// Initialize Log4j logs
	 
		 private static Logger Log = Logger.getLogger(Log.class.getName());//
		 public static void logConfig() {
		 DOMConfigurator.configure("log4j2.xml");
		 }
	
		 public static void info(String message) {
		 
		Log.info(message);
 
		}
 
 public static void warn(String message) {
 
    Log.warn(message);
 
	}
 
 public static void error(String message) {
 
    Log.error(message);
 
	}
 
 public static void fatal(String message) {
 
    Log.fatal(message);
 
	}
 
 public static void debug(String message) {
 
    Log.debug(message);
 
	}
 public static void close(String message) {
	 
	    Log.shutdown();
	 
		}
}
