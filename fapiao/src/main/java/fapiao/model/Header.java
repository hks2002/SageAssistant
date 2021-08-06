package fapiao.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Header implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int rowid;
	String Fpzl;
	String Lbdm;
	String Fphm;
	String Kprq;
	String Gfmc;
	String Gfsh;
	String Gfyhzh;
	String Gfdzdh;
	String Xfmc;
	String Xfsh;
	String Xfyhzh;
	String Xfdzdh;
	String Hjje;
	String Hjse;
	String Bz;
	String Kpr;
	String Fhr;
	String Skr;
	String Fpzt;
	
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getFpzl() {
		return Fpzl;
	}
	public void setFpzl(String fpzl) {
		Fpzl = fpzl;
	}
	public String getLbdm() {
		return Lbdm;
	}
	public void setLbdm(String lbdm) {
		Lbdm = lbdm;
	}
	public String getFphm() {
		return Fphm;
	}
	public void setFphm(String fphm) {
		Fphm = fphm;
	}
	public String getKprq() {
		return Kprq;
	}
	public void setKprq(String kprq) {
		Kprq = kprq;
	}
	public String getGfmc() {
		return Gfmc;
	}
	public void setGfmc(String gfmc) {
		Gfmc = gfmc;
	}
	public String getGfsh() {
		return Gfsh;
	}
	public void setGfsh(String gfsh) {
		Gfsh = gfsh;
	}
	public String getGfyhzh() {
		return Gfyhzh;
	}
	public void setGfyhzh(String gfyhzh) {
		Gfyhzh = gfyhzh;
	}
	public String getGfdzdh() {
		return Gfdzdh;
	}
	public void setGfdzdh(String gfdzdh) {
		Gfdzdh = gfdzdh;
	}
	public String getXfmc() {
		return Xfmc;
	}
	public void setXfmc(String xfmc) {
		Xfmc = xfmc;
	}
	public String getXfsh() {
		return Xfsh;
	}
	public void setXfsh(String xfsh) {
		Xfsh = xfsh;
	}
	public String getXfyhzh() {
		return Xfyhzh;
	}
	public void setXfyhzh(String xfyhzh) {
		Xfyhzh = xfyhzh;
	}
	public String getXfdzdh() {
		return Xfdzdh;
	}
	public void setXfdzdh(String xfdzdh) {
		Xfdzdh = xfdzdh;
	}
	public String getHjje() {
		return Hjje;
	}
	public void setHjje(String hjje) {
		Hjje = hjje;
	}
	public String getHjse() {
		return Hjse;
	}
	public void setHjse(String hjse) {
		Hjse = hjse;
	}
	public String getBz() {
		return Bz;
	}
	public void setBz(String bz) {
		Bz = bz;
	}
	public String getKpr() {
		return Kpr;
	}
	public void setKpr(String kpr) {
		Kpr = kpr;
	}
	public String getFhr() {
		return Fhr;
	}
	public void setFhr(String fhr) {
		Fhr = fhr;
	}
	public String getSkr() {
		return Skr;
	}
	public void setSkr(String skr) {
		Skr = skr;
	}
	public String getFpzt() {
		return Fpzt;
	}
	public void setFpzt(String fpzt) {
		Fpzt = fpzt;
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