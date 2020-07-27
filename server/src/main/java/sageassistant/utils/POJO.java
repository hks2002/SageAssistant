package sageassistant.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * It's an tool to help to generate POJO by sql during develop.
 */
public class POJO {

	/**
	 * Run it as an Java Application In IDE.<br>
	 * Change your sql statement, then copy the output.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			InputStream is = new FileInputStream("src/main/resources/application-prod.properties");
			prop.load(is);
			
			String className = prop.getProperty("spring.datasource.driver-class-name");
			String url = prop.getProperty("spring.datasource.url");
			String user = prop.getProperty("spring.datasource.username");
			String password = prop.getProperty("spring.datasource.password");
			Class.forName(className);

			Connection con = (Connection) DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(RawSQL());

			ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
			System.out.println("=================================================");
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				System.out.print("private ");
				System.out.print(POJOType(metadata.getColumnName(i),metadata.getColumnTypeName(i)) + " "); // type
				System.out.print(metadata.getColumnName(i)); // name
				System.out.println(";"); // key
			}
			result.close();
			statement.close();
			con.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* 
	 * Past your sql statement here.
	 */
	public static String RawSQL() {
		String sql="SELECT TOP 1 ";
		sql += "";
		
		return sql;
	}
	
	public static String POJOType(String name, String type) {
		if (name.toLowerCase().contains("qty") || name.toLowerCase().contains("amt") || name.toLowerCase().contains("pri")) {
			return "Float";
		}
		switch (type) {
		case "varchar":
			return "String";
		case "nvarchar":
			return "String";
		case "tinyint":
			return "Byte";
		case "smallint":
			return "Short";
		case "int":
			return "Integer";
		case "bigint":
			return "Long";
		case "float":
			return "Float";
		case "double":
			return "Double";
		case "numberic":
			return "BigDecimal";
		case "date":
			return "Date";
		case "datetime":
			return "Date";
		default:
			return "String";
		}
	}
	
}