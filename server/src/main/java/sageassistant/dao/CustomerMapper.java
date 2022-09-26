/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 16:25:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:21:22
 * @FilePath       : \server\src\main\java\sageassistant\dao\CustomerMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.CustomerDelayHistory;
import sageassistant.model.CustomerDeliveryHistory;
import sageassistant.model.CustomerDetails;
import sageassistant.model.CustomerName;
import sageassistant.model.CustomerOpenItems;
import sageassistant.model.CustomerSummaryAmount;
import sageassistant.model.CustomerSummaryQty;

@Mapper
public interface CustomerMapper {
    List<CustomerDetails> findCustomerDetailsByCode(@Param("CustomerCode") String CustomerCode);

    List<CustomerName> findCustomerByCodeOrName(
        @Param("CustomerCodeOrName") String CustomerCodeOrName,
        @Param("Count") Integer Count
    );

    List<CustomerSummaryAmount> findCustomerOpenAmount(@Param("CustomerCode") String CustomerCode);

    List<CustomerSummaryQty> findCustomerOpenProjectQty(@Param("CustomerCode") String CustomerCode);

    List<CustomerSummaryQty> findCustomerOpenItemQty(@Param("CustomerCode") String CustomerCode);

    List<CustomerSummaryQty> findCustomerOpenProductQty(@Param("CustomerCode") String CustomerCode);

    List<CustomerOpenItems> findCustomerOpenItems(@Param("CustomerCode") String CustomerCode);

    List<CustomerSummaryAmount> findCustomerTotalAmount(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<CustomerSummaryQty> findCustomerTotalProjectQty(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<CustomerSummaryQty> findCustomerTotalItemQty(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<CustomerSummaryQty> findCustomerTotalProductQty(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<CustomerDeliveryHistory> findCustomerDeliveryHistory(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<CustomerDelayHistory> findCustomerDelayHistory(
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );
}
