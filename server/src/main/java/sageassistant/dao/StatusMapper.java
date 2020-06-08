package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;

@Mapper
public interface StatusMapper {		
	List<TobeDelivery> findTobeDeliveryBySite(@Param("site") String site);
	List<TobeReceive> findTobeReceiveBySite(@Param("site") String site);
	List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(@Param("site") String site);
	List<TobePurchaseBom> findTobePurchaseBomBySite(@Param("site") String site);
	
}
