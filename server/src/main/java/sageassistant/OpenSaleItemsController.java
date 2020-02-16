package sageassistant;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class OpenSaleItemsController {
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Autowired
	private NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

	private String sql = Utils.readFileContent("sql/openSaleItems.sql");

	/**
	 * google guava cache
	 */
	private LoadingCache<String, String> cache;

	public OpenSaleItemsController() {
		cache = CacheBuilder.newBuilder().refreshAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String facility) {						
						return getFromDB(facility);
					}

					private String getFromDB(String facility) {
						try {
							MapSqlParameterSource sqlParas = new MapSqlParameterSource();
							sqlParas.addValue("facility", facility);						

							List<Map<String, Object>> list;
							list = namedTemplate.queryForList(sql, sqlParas);

							log.info("Fetched <OpenSaleItems> From database for :" + facility);
							
							ObjectMapper MAPPER = new ObjectMapper();
							MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
							
							return MAPPER.writeValueAsString(list);
						} catch (JsonProcessingException e) {
							log.error("JsonProcessingException");
							return "";
						}

					}
				});
		
	}

	@GetMapping("Data/OpenSaleItems")
	public String get(@RequestParam(value = "facility", required = false, defaultValue = "ZHU") String facility) {
		try {
			return cache.get(facility);
		} catch (ExecutionException e) {
			log.error("ExecutionException :" + "When getting OpenSaleItems for " + facility);
			return "";
		}
	}

}
