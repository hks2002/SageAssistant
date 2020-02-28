package sageassistant;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NamedParameterJdbcTemplateController {
	private static final Logger log = LoggerFactory.getLogger(NamedParameterJdbcTemplateController.class);

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	ObjectMapper MAPPER = new ObjectMapper();

	NamedParameterJdbcTemplateController() {
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	protected String getFromDB(String name, String sqlPath, MapSqlParameterSource sqlParas) {
		try {

			String sql = Utils.readFileContent(sqlPath);
			log.debug("sql :" + sql);

			List<Map<String, Object>> list;
			list = namedParameterJdbcTemplate.queryForList(sql, sqlParas);
			log.info("Fetched <" + name + "> From database for :" + sqlParas.getValues().toString());

			return MAPPER.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException");
			log.error(e.getMessage());
			return "";
		} catch (DataAccessException e1) {
			log.error("DataAccessException");
			log.error(e1.getMessage());
			return "";
		}

	}
}
