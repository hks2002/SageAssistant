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
	List<String> findInvoiceNOByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);
	List<InvoiceBody> findInvoiceBodyByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);
	List<InvoiceBody> findInvoiceBodyByFaPiao(@Param("FaPiao") String FaPiao);
}
