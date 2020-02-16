package sageassistant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

//@Slf4j  //using it cause this class Utils cannot directly use in other class method
public class Utils {
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

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

	public static String readFileContent(String filename) {
		// Reading files in jar, use getResourceAsStream(filename), here is reading for
		// war distribution
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		log.debug("Resource base path :" + path);

		try {
			InputStream inputStream = new FileInputStream(path + filename);
			return new String(ByteStreams.toByteArray(inputStream));
		} catch (FileNotFoundException e) {
			log.info("FileNotFound: " + path + filename);
			return "";
		} catch (IOException e) {
			log.error("IOException: " + "When reading " + filename);
			return "";
		}

	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
