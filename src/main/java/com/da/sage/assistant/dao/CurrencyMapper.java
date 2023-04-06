/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-06-27 13:39:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:30:47                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/CurrencyMapper.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.da.sage.assistant.model.CurrencyHistory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CurrencyMapper {
    List<CurrencyHistory> findCurrencyRate(
        @Param("Sour") String Sour,
        @Param("Dest") String Dest,
        @Param("Date") String Date
    );
}
