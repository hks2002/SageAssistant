package fapiao.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Body implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int rowid;
	String Lbdm;
	String fphm;
	String Xh;
	String Spmc;
	String Ggxh;
	String Jldw;
	String Dj;
	String Sl;
	String Je;
	String Slv;
	String Se;
	
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getLbdm() {
		return Lbdm;
	}
	public void setLbdm(String lbdm) {
		Lbdm = lbdm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getXh() {
		return Xh;
	}
	public void setXh(String xh) {
		Xh = xh;
	}
	public String getSpmc() {
		return Spmc;
	}
	public void setSpmc(String spmc) {
		Spmc = spmc;
	}
	public String getGgxh() {
		return Ggxh;
	}
	public void setGgxh(String ggxh) {
		Ggxh = ggxh;
	}
	public String getJldw() {
		return Jldw;
	}
	public void setJldw(String jldw) {
		Jldw = jldw;
	}
	public String getDj() {
		return Dj;
	}
	public void setDj(String dj) {
		Dj = dj;
	}
	public String getSl() {
		return Sl;
	}
	public void setSl(String sl) {
		Sl = sl;
	}
	public String getJe() {
		return Je;
	}
	public void setJe(String je) {
		Je = je;
	}
	public String getSlv() {
		return Slv;
	}
	public void setSlv(String slv) {
		Slv = slv;
	}
	public String getSe() {
		return Se;
	}
	public void setSe(String se) {
		Se = se;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper=new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
	}
}