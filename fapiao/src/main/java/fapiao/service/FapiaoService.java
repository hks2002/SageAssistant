package fapiao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fapiao.dao.BodyDao;
import fapiao.dao.HeaderDao;
import fapiao.model.Body;
import fapiao.model.Header;

@Service
public class FapiaoService {
	@Autowired
	HeaderDao headerDao;
	
	@Autowired
	BodyDao bodyDao;
	
	public List<Header> getHeader(String Lbdm, String Fphm) {
		return headerDao.getHeader(Lbdm, Fphm);
	}
	
	public List<Header> getHeader(String Fphm) {
		return headerDao.getHeader(Fphm);
	}

	public List<Body> getBody(String Lbdm, String Fphm, String xh) {
		return bodyDao.getBody(Lbdm, Fphm, xh);
	}
	
	public List<Body> getBody(String Lbdm, String Fphm) {
		return bodyDao.getBody(Lbdm, Fphm);
	}
	
	public List<Body> getBody(String Fphm) {
		return bodyDao.getBody(Fphm);
	}
	
	public List<String> getLbdm(String Lbdm) {
		return headerDao.getLbdm(Lbdm);
	}
	
	public List<String> getFphm(String Lbdm, String Fphm) {
		return headerDao.getFphm(Lbdm, Fphm);
	}
	
	public List<String> getFphm(String Fphm) {
		return headerDao.getFphm(Fphm);
	}
}
