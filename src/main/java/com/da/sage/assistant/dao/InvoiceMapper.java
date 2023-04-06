/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:31:30                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/InvoiceMapper.java                               *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.da.sage.assistant.model.InvoiceBody;
import com.da.sage.assistant.model.InvoiceHeader;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InvoiceMapper {
    InvoiceHeader findInvoiceHeaderByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<InvoiceHeader> findInvoiceHeaderByFaPiao(@Param("FaPiao") String FaPiao);

    List<String> findInvoiceNOByInvoiceNO(@Param("InvoiceNO") String InvoiceNO, @Param("Count") Integer Count);

    List<InvoiceBody> findInvoiceBodyByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<InvoiceBody> findInvoiceBodyByFaPiao(@Param("FaPiao") String FaPiao);
}
