/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:22:48
 * @FilePath       : \server\src\main\java\sageassistant\dao\SupplierMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.SupplierDelayHistory;
import sageassistant.model.SupplierDeliveryHistory;
import sageassistant.model.SupplierDetails;
import sageassistant.model.SupplierName;
import sageassistant.model.SupplierOpenItems;
import sageassistant.model.SupplierSummaryAmount;
import sageassistant.model.SupplierSummaryQty;

@Mapper
public interface SupplierMapper {
    List<SupplierName> findSupplierByCodeOrName(
        @Param("SupplierCodeOrName") String SupplierCodeOrName,
        @Param("Count") Integer Count
    );

    List<SupplierDetails> findSupplierDetailsByCode(@Param("SupplierCode") String SupplierCode);

    List<SupplierSummaryAmount> findSupplierOpenAmount(@Param("SupplierCode") String SupplierCode);

    List<SupplierSummaryQty> findSupplierOpenProjectQty(@Param("SupplierCode") String SupplierCode);

    List<SupplierSummaryQty> findSupplierOpenItemQty(@Param("SupplierCode") String SupplierCode);

    List<SupplierSummaryQty> findSupplierOpenProductQty(@Param("SupplierCode") String SupplierCode);

    List<SupplierOpenItems> findSupplierOpenItems(@Param("SupplierCode") String SupplierCode);

    List<SupplierSummaryAmount> findSupplierTotalAmount(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<SupplierSummaryQty> findSupplierTotalProjectQty(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<SupplierSummaryQty> findSupplierTotalItemQty(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<SupplierSummaryQty> findSupplierTotalProductQty(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<SupplierDeliveryHistory> findSupplierDeliveryHistory(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<SupplierDelayHistory> findSupplierDelayHistory(
        @Param("SupplierCode") String SupplierCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    String findPurchaseDate(@Param("PurchaseNO") String PurchaseNO);
}
