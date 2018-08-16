package QAInfo.TaTocAdvance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.UtilityFileReader;
import util.Log;
public class ConnectToMySQL {
	Connection conn = null;
	Statement stmt = null;
	ResultSet resultSet1 = null;
	ResultSet resultSet2 = null;
public ConnectToMySQL() throws Exception {
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("jdbc name");
	UtilityFileReader configReader=new UtilityFileReader();
	// Open a connection
	//System.out.println(configReader.getDbUrl()+configReader.getDbUsername()+configReader.getDbPsswd());
	conn = DriverManager.getConnection(configReader.getDbUrl(), 

configReader.getDbUsername(),configReader.getDbPsswd());
	System.out.println("connection created");
	
}
public String[] getName(String symbol)throws Exception {
	// Execute a query
	stmt = conn.createStatement();
	String name=null;
	String[] passkey=new String[2];
	resultSet1 = stmt.executeQuery("select id from identity where symbol='"+symbol

+"'");
	Log.info("id query executed");
	while (resultSet1 .next()) {
		name=resultSet1 .getString(1);	
	}
	resultSet2 = stmt.executeQuery("select name,passkey from credentials where id="+name);
	Log.info("name and passkey query executed");
	while (resultSet2 .next()) {
		passkey[0]=resultSet2.getString(1);
		passkey[1]=resultSet2.getString(2);
	}
	if (resultSet1 != null) {
		try {
			resultSet1.close();
			resultSet2.close();
		} catch (Exception e) {
		}
	}
	
	if (stmt != null) {
		try {
			stmt.close();
		} catch (Exception e) {
		}
	}
	
	if (conn != null) {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
	return (passkey);
}

}