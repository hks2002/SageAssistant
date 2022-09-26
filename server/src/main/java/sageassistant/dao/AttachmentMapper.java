/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:11:57
 * @FilePath       : \server\src\main\java\sageassistant\dao\AttachmentMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.Attachment;

@Mapper
public interface AttachmentMapper {
    List<Attachment> getAttachment(@Param("Pn") String Pn);
}
