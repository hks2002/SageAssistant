package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.FinancialBalance;

@Mapper
public interface FinancialMapper {
	List<FinancialBalance> findAccoutBalanceForAll(@Param("Site") String Site, @Param("Year") String Year);
	// AcountNO AcountNOList only one
	List<FinancialBalance> findAccoutBalanceByAccountNO(@Param("Site") String Site, @Param("Year") String Year, @Param("AccountNO") String AccountNO, @Param("AccountNOs") String[] AccountNOs);
}
