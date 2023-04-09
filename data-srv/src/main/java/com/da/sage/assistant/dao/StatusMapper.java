/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:31:50                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/StatusMapper.java                                *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.da.sage.assistant.model.TobeClosedWO;
import com.da.sage.assistant.model.TobeDealWithOrderLine;
import com.da.sage.assistant.model.TobeDelivery;
import com.da.sage.assistant.model.TobePurchaseBom;
import com.da.sage.assistant.model.TobeReceive;
import com.da.sage.assistant.model.TobeTrackingBOMLine;
import com.da.sage.assistant.model.TobeTrackingPurchaseOrderLine;
import com.da.sage.assistant.model.TobeTrackingQALine;
import com.da.sage.assistant.model.TobeTrackingReceiptLine;
import com.da.sage.assistant.model.TobeTrackingSalesOrderLine;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
