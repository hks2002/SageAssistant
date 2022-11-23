/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-15 23:00:00
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
import sageassistant.model.TobeTrackingBOMLine;
import sageassistant.model.TobeTrackingPurchaseOrderLine;
import sageassistant.model.TobeTrackingQALine;
import sageassistant.model.TobeTrackingReceiptLine;
import sageassistant.model.TobeTrackingSalesOrderLine;

@Mapper
public interface StatusMapper {
    List<TobeDelivery> findTobeDeliveryBySite(@Param("Site") String Site);

    List<TobeReceive> findTobeReceiveBySite(@Param("Site") String Site);

    List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(@Param("Site") String site);

    List<TobePurchaseBom> findTobePurchaseBomBySite(@Param("Site") String Site);

    List<TobeClosedWO> findTobeClosedWOBySite(@Param("Site") String Site);

    Integer findTobeTrackingSalesOrderLineCntBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode
    );

    List<TobeTrackingSalesOrderLine> findTobeTrackingSalesOrderLineBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode,
        @Param("OrderBy") String OrderBy,
        @Param("Descending") String Descending,
        @Param("Offset") Integer Offset,
        @Param("Limit") Integer Limit
    );

    List<TobeTrackingBOMLine> findTobeTrackingBOMLineBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode,
        @Param("OrderBy") String OrderBy,
        @Param("Descending") String Descending,
        @Param("Offset") Integer Offset,
        @Param("Limit") Integer Limit
    );

    List<TobeTrackingPurchaseOrderLine> findTobeTrackingPurchaseOrderLineBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode,
        @Param("OrderBy") String OrderBy,
        @Param("Descending") String Descending,
        @Param("Offset") Integer Offset,
        @Param("Limit") Integer Limit
    );

    List<TobeTrackingReceiptLine> findTobeTrackingReceiptLineBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode,
        @Param("OrderBy") String OrderBy,
        @Param("Descending") String Descending,
        @Param("Offset") Integer Offset,
        @Param("Limit") Integer Limit
    );

    List<TobeTrackingQALine> findTobeTrackingQALineBySite(
        @Param("Site") String Site,
        @Param("OrderType") String OrderType,
        @Param("CustomerCode") String CustomerCode,
        @Param("VendorCode") String VendorCode,
        @Param("OrderBy") String OrderBy,
        @Param("Descending") String Descending,
        @Param("Offset") Integer Offset,
        @Param("Limit") Integer Limit
    );
}
