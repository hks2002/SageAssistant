package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import sageassistant.model.Sites;


@Mapper
public interface CommonMapper {
	List<Sites> getAllSites();
}
