/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:14:57
 * @FilePath       : \server\src\main\java\sageassistant\dao\StatusMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.TobeClosedWO;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.model.TobeTracking;

@Mapper
public interface StatusMapper {
    List<TobeDelivery> findTobeDeliveryBySite(@Param("site") String site, @Param("Count") Integer Count);

    List<TobeReceive> findTobeReceiveBySite(@Param("site") String site, @Param("Count") Integer Count);

    List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(
        @Param("site") String site,
        @Param("Count") Integer Count
    );

    List<TobePurchaseBom> findTobePurchaseBomBySite(@Param("site") String site, @Param("Count") Integer Count);

    List<TobeClosedWO> findTobeClosedWOBySite(@Param("site") String site, @Param("Count") Integer Count);

    List<TobeTracking> findTobeTracking(@Param("site") String site);
}
