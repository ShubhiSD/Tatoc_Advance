package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UtilityFileReader {
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";
	public UtilityFileReader(){
		BufferedReader reader;
		try {
				reader = new BufferedReader(new FileReader(propertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}		
		}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}	
	public String getDbUrl(){
		String DbUrl = properties.getProperty("dburl");
		if(DbUrl!= null) return DbUrl;
		else throw new RuntimeException("DbUrl not specified in the Configuration.properties file.");		
	}
	public String getDbUsername(){
		String DbUsername = properties.getProperty("dbusername");
		if(DbUsername!= null) return DbUsername;
		else throw new RuntimeException("DbUsername not specified in the Configuration.properties file.");		
	}
	public String getDbPsswd(){
		String DbPsswd = properties.getProperty("dbpassword");
		if(DbPsswd!= null) return DbPsswd;
		else throw new RuntimeException("DbPsswd not specified in the Configuration.properties file.");		
	}
	public String getExcelPath(){
		String ExcelPath = properties.getProperty("excelPath");
		if(ExcelPath!= null) return ExcelPath;
		else throw new RuntimeException("ExcelPath not specified in the Configuration.properties file.");		
	}
	
	public String getSheetName(){
		String SheetName = properties.getProperty("sheetName");
		if(SheetName!= null) return SheetName;
		else throw new RuntimeException("SheetName not specified in the Configuration.properties file.");		
	}
	public String getlog4jXmlpath(){
		String log4jXmlpath = properties.getProperty("log4jXmlFilepath");
		if(log4jXmlpath!= null) return log4jXmlpath;
		else throw new RuntimeException("SheetName not specified in the Configuration.properties file.");		
	}
	public String getlog4jXmlFilName(){
		String log4jXmlFilName = properties.getProperty("log4jXmlFilName");
		if(log4jXmlFilName!= null) return log4jXmlFilName;
		else throw new RuntimeException("SheetName not specified in the Configuration.properties file.");		
	}
	
	
}
