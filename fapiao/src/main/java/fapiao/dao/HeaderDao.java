package fapiao.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fapiao.model.Header;
import fapiao.utils.Utils;

@Repository
public class HeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addHeader(Header header) {
		return jdbcTemplate.update("insert into header ("
			+ "lbdm,"
			+ "fphm,"
			+ "fpzl,"
			+ "kprq,"
			+ "gfmc,"
			+ "gfsh,"
			+ "gfyhzh,"
			+ "gfdzdh,"
			+ "xfmc,"
			+ "xfsh,"
			+ "xfyhzh,"
			+ "xfdzdh,"
			+ "hjje,"
			+ "hjse,"
			+ "bz,"
			+ "kpr,"
			+ "fhr,"
			+ "skr,"
			+ "fpzt"
			+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
			new Object[] {
					header.getLbdm(),
					header.getFphm(),
					header.getFpzl(),
					header.getKprq(),
					header.getGfmc(),
					header.getGfsh(),
					header.getGfyhzh(),
					header.getGfdzdh(),
					header.getXfmc(),
					header.getXfsh(),
					header.getXfyhzh(),
					header.getXfdzdh(),
					header.getHjje(),
					header.getHjse(),
					header.getBz(),
					header.getKpr(),
					header.getFhr(),
					header.getSkr(),
					header.getFpzt()
			});
	}
	
	public int updateHeader(Header header) {
	
			return jdbcTemplate.update("update header set"
				+ " fpzl=?,"
				+ " kprq=?,"
				+ " gfmc=?,"
				+ " gfsh=?,"
				+ " gfyhzh=?,"
				+ " gfdzdh=?,"
				+ " xfmc=?,"
				+ " xfsh=?,"
				+ " xfyhzh=?,"
				+ " xfdzdh=?,"
				+ " hjje=?,"
				+ " hjse=?,"
				+ " bz=?,"
				+ " kpr=?,"
				+ " fhr=?,"
				+ " skr=?,"
				+ " fpzt=?"
				+ " where  lbdm=? and  fphm=?",
				new Object[] {
						header.getFpzl(),
						header.getKprq(),
						header.getGfmc(),
						header.getGfsh(),
						header.getGfyhzh(),
						header.getGfdzdh(),
						header.getXfmc(),
						header.getXfsh(),
						header.getXfyhzh(),
						header.getXfdzdh(),
						header.getHjje(),
						header.getHjse(),
						header.getBz(),
						header.getKpr(),
						header.getFhr(),
						header.getSkr(),
						header.getFpzt(),
						
						header.getLbdm(),
						header.getFphm()
				});
	}
	
	public List<Header> getHeader(String lbdm, String fphm) {
		List<Header> listHeader = new ArrayList<Header>();
		try {
			String sql = "select * from header"
				+ " where 1=1"
				+ " and lbdm = '" + lbdm + "'"
				+ " and fphm = '" + fphm + "'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Header header = new Header();
				header = Utils.map2bean(map, header);
				listHeader.add(header);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listHeader;
	}
	
	public List<Header> getHeader(String fphm) {
		List<Header> listHeader = new ArrayList<Header>();
		try {
			String sql = "select * from header"
				+ " where 1=1"
				+ " and fphm = '" + fphm + "'"
				+ " order by lbdm desc"
				+ " limit 1 ";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Header header = new Header();
				header = Utils.map2bean(map, header);
				listHeader.add(header);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listHeader;
	}
	
	public List<String> getLbdm(String lbdm){
		List<String> listLbdm = new ArrayList<String>();
		try {
			String sql = "select distinct lbdm from header"
				+ " where 1=1"
				+ " and lbdm like '%" + lbdm + "%'"
				+ " order by lbdm desc"
				+ " limit 10";
			listLbdm = jdbcTemplate.queryForList(sql, String.class);
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listLbdm;
	}
	
	public List<String> getFphm(String lbdm, String fphm){
		List<String> listLbdm = new ArrayList<String>();
		try {
			String sql = "select distinct fphm from header"
				+ " where 1=1"
				+ " and lbdm = '" + lbdm + "'"
				+ " and lbdm like '%" + fphm + "%'"
				+ " order by fphm desc"
				+ " limit 10";
			listLbdm = jdbcTemplate.queryForList(sql, String.class);
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listLbdm;
	}
	
	public List<String> getFphm(String fphm){
		List<String> listLbdm = new ArrayList<String>();
		try {
			String sql = "select fphm from header"
				+ " where 1=1"
				+ " and lbdm like '%" + fphm + "%'"
				+ " order by fphm desc"
				+ " limit 10";
			listLbdm = jdbcTemplate.queryForList(sql, String.class);
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listLbdm;
	}
}
