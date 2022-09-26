/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:13:25
 * @FilePath       : \server\src\main\java\sageassistant\dao\InvoiceMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.InvoiceBody;
import sageassistant.model.InvoiceHeader;

@Mapper
public interface InvoiceMapper {
    InvoiceHeader findInvoiceHeaderByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<InvoiceHeader> findInvoiceHeaderByFaPiao(@Param("FaPiao") String FaPiao);

    List<String> findInvoiceNOByInvoiceNO(@Param("InvoiceNO") String InvoiceNO, @Param("Count") Integer Count);

    List<InvoiceBody> findInvoiceBodyByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<InvoiceBody> findInvoiceBodyByFaPiao(@Param("FaPiao") String FaPiao);
}
