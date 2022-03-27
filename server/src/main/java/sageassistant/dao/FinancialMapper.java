package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.FinancialBalance;
import sageassistant.model.FinancialInvoicePay;
import sageassistant.model.FinancialInvoicePayPro;

@Mapper
public interface FinancialMapper {
	List<FinancialBalance> findAccoutBalanceForAll(
			@Param("Site") String Site,
			@Param("Year") String Year);

	// AcountNO AcountNOList only one
	List<FinancialBalance> findAccoutBalanceByAccountNO(
			@Param("Site") String Site,
			@Param("Year") String Year,
			@Param("AccountNOs") String[] AccountNOs);

	List<FinancialInvoicePay> findInvoicePay(
			@Param("Site") String Site,
			@Param("BPCode") String BPCode,
			@Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo);

	List<FinancialInvoicePayPro> findInvoicePayPro(
			@Param("Site") String Site,
			@Param("BPCode") String BPCode,
			@Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo);
}
