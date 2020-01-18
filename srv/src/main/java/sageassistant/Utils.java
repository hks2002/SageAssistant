package sageassistant;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Utils {
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
		// json数组
		JSONArray array = new JSONArray();

		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();

			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}

		return array.toString();
	}

	public static String RowSetToJson(RowSet rs) throws SQLException, JSONException {
		return resultSetToJson((ResultSet) rs);
	}

	//// this method is used to reading files in jar. 
	public static String readFileContent(String filename) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename); 
		
        String rtn="";
		try {
			rtn = new String(ByteStreams.toByteArray(inputStream));
		} catch (IOException e) {
			logger.error("Reading File with Error!");
			e.printStackTrace();
		}
		
		return rtn;
	}
}
