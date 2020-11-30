package sageassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.dao.CommonMapper;
import sageassistant.model.Sites;

@Service
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	public String getAllSites(){
		String s="[";
		for (Sites o : commonMapper.getAllSites() ) {
			s += "\"" + o.getSite()+"\",";
		}
		if (commonMapper.getAllSites().size()>0) {
			s = s.substring(0, s.lastIndexOf(','));
		}
		s += "]";
		return s;
	}
	
}
