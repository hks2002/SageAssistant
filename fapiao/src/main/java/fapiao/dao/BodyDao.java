package fapiao.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fapiao.model.Body;
import fapiao.utils.Utils;

@Repository
public class BodyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addBody(Body body) {
		return jdbcTemplate.update("insert into body ("
				+ "lbdm,"
				+ "fphm,"
				+ "xh,"
				+ "spmc,"
				+ "ggxh,"
				+ "jldw,"
				+ "dj,"
				+ "sl,"
				+ "je,"
				+ "slv,"
				+ "se"
				+ ") values (?,?,?,?,?,?,?,?,?,?,?) ",
				new Object[] {
						body.getLbdm(),
						body.getFphm(),
						body.getXh(),
						body.getSpmc(),
						body.getGgxh(),
						body.getJldw(),
						body.getDj(),
						body.getSl(),
						body.getJe(),
						body.getSlv(),
						body.getSe()
				});
	}
	
	public int updateBody(Body body) {
		return jdbcTemplate.update("update body set"
				+ " spmc=?,"
				+ " ggxh=?,"
				+ " jldw=?,"
				+ " dj=?,"
				+ " sl=?,"
				+ " je=?,"
				+ " slv=?,"
				+ " se=?"
				+ " where  lbdm=? and  fphm=? and xh=?",
			new Object[] {
					body.getSpmc(),
					body.getGgxh(),
					body.getJldw(),
					body.getDj(),
					body.getSl(),
					body.getJe(),
					body.getSlv(),
					body.getSe(),
					

					body.getLbdm(),
					body.getFphm(),
					body.getXh()
			});
	}
	
	public List<Body> getBody(String lbdm, String fphm, String xh) {
		List<Body> listBody = new ArrayList<Body>();
		try {
			String sql = "select * from body"
				+ " where 1=1"
				+ " and lbdm = '" + lbdm + "'"
				+ " and fphm = '" + fphm + "'"
				+ " and xh = '" + xh + "'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Body body = new Body();
				body = Utils.map2bean(map, body);
				listBody.add(body);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listBody;
	}
	
	public List<Body> getBody(String lbdm, String fphm) {
		List<Body> listBody = new ArrayList<Body>();
		try {
			String sql = "select * from body"
				+ " where 1=1"
				+ " and lbdm = '" + lbdm + "'"
				+ " and fphm = '" + fphm + "'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Body body = new Body();
				body = Utils.map2bean(map, body);
				listBody.add(body);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listBody;
	}
	
	public List<Body> getBody(String fphm) {
		List<Body> listBody = new ArrayList<Body>();
		try {
			String sql = "select * from body"
				+ " where 1=1"
				+ " and fphm = '" + fphm + "'"
				+ " oder by lbdm desc"
				+ " limit 1 ";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Body body = new Body();
				body = Utils.map2bean(map, body);
				listBody.add(body);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listBody;
	}
	
}
