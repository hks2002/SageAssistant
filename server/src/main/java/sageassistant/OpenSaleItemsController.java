package sageassistant;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@CrossOrigin
@RestController
public class OpenSaleItemsController {
	private static final Logger log = LoggerFactory.getLogger(OpenSaleItemsController.class);

	private String sql = Utils.readFileContent("sql/openSaleItems.sql");
	private boolean initFlag = false;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	/**
	 * google guava cache
	 */
	private LoadingCache<String, String> cache;

	private String getFromDB(String facility) {
		try {
			MapSqlParameterSource sqlParas = new MapSqlParameterSource();
			sqlParas.addValue("facility", facility);

			log.debug("OpenSaleItems sql :" + sql);
			log.debug("OpenSaleItems facility :" + facility);

			List<Map<String, Object>> list;
			list = namedParameterJdbcTemplate.queryForList(sql, sqlParas);

			log.info("Fetched <OpenSaleItems> From database for :" + facility);

			ObjectMapper MAPPER = new ObjectMapper();
			MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

			return MAPPER.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException");
			return "";
		}

	}

	private void initCache() {
		cache = CacheBuilder.newBuilder()
							.refreshAfterWrite(30, TimeUnit.MINUTES)
							.build(new CacheLoader<String, String>() {
								@Override
								public String load(String facility) {
									return getFromDB(facility);
								}
							});
	}

	public String getSql() {
		return sql;
	}

	@GetMapping("/Data/OpenSaleItems")
	public String get(@RequestParam(value = "facility", required = false, defaultValue = "ZHU") String facility) {
		try {
			if (!initFlag) {
				initCache();
				initFlag = true;
			}
			return cache.get(facility);
		} catch (ExecutionException e) {
			log.error("ExecutionException :" + "When getting OpenSaleItems for " + facility);
			return "";
		}
	}

}
