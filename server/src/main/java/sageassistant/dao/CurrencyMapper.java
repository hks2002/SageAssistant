/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-06-27 13:39:10
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:12:17
 * @FilePath       : \server\src\main\java\sageassistant\dao\CurrencyMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.CurrencyHistory;

@Mapper
public interface CurrencyMapper {
    List<CurrencyHistory> findCurrencyRate(
        @Param("Sour") String Sour,
        @Param("Dest") String Dest,
        @Param("Date") String Date
    );
}
