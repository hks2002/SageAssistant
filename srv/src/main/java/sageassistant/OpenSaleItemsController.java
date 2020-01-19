package sageassistant;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@CrossOrigin
@RestController
public class OpenSaleItemsController {
	private static final Logger logger = LoggerFactory.getLogger(OpenSaleItemsController.class);

	@Autowired
	private  JdbcTemplate jdbcTemplate=new JdbcTemplate();
	
	@Autowired
	private  NamedParameterJdbcTemplate namedTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);

	private static ObjectMapper MAPPER = new ObjectMapper();
    private static String sql=Utils.readFileContent("sql/openSaleItems.sql");
    private static List<Map<String, Object>> list;
    private static MapSqlParameterSource sqlParas = new MapSqlParameterSource();
    
	/**
	 * guava cache
	 */
	private static LoadingCache<String, String> cache;
	
	public OpenSaleItemsController () {
		cache = CacheBuilder.newBuilder().refreshAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String facility) throws JsonProcessingException {
						return getFromDB(facility);
					}

					private String getFromDB(String facility) throws JsonProcessingException {
						logger.debug(sql);
						
						sqlParas.addValue("facility", facility);
						list = namedTemplate.queryForList(sql, sqlParas);

						logger.info("Fetched <OpenSaleItems> From database");

						MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
						return MAPPER.writeValueAsString(list);
					}
				});
	}

	@GetMapping("Data/OpenSaleItems")
	public String get(@RequestParam(value = "facility", required = false, defaultValue = "ZHU") String facility)
			throws ExecutionException {
		return cache.get(facility);
	}

}
