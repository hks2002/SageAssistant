package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.Attachment;


@Mapper
public interface AttachmentMapper {
	List<Attachment> getAttachment(@Param("Pn") String Pn);
}
