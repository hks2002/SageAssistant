/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:27:23                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/CommonMapper.java                                *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    List<String> getAllSites();
}
