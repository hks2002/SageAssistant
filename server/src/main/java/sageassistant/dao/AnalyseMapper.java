package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.AnalyseQuoteSalesCost;

@Mapper
public interface AnalyseMapper {
	List<AnalyseQuoteSalesCost> analyseQuoteSalesCost(@Param("Site") String Site,
			@Param("CategoryCode") String CategoryCode, @Param("PnRoot") String PnRoot, @Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo, @Param("Limit") Integer Limit);
}
