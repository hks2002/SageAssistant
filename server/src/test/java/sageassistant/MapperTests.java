package sageassistant;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sageassistant.dao.PnMapper;
import sageassistant.dao.StatusMapper;
import sageassistant.dao.StockMapper;
import sageassistant.model.DeliveryDuration;
import sageassistant.model.PnDetails;
import sageassistant.model.PnRootPn;
import sageassistant.model.PnStatus;
import sageassistant.model.QuoteHistory;
import sageassistant.model.SalesHistory;
import sageassistant.model.StockInfo;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.utils.Utils;


@SpringBootTest
class MapperTests {
	private static final Logger log = LoggerFactory.getLogger(MapperTests.class);
	
	@Autowired
    private PnMapper pnMapper;
	
	@Autowired
    private StatusMapper statusMapper;
	
	@Autowired
	private StockMapper stockMapper;
		
	@Test
	void testPnRootPn() {
		PageHelper.startPage(1,50);
		List<PnRootPn> list = pnMapper.findPnByLike("956A%");

		log.info(list.toString());
		for (PnRootPn o : list) {
			log.info(o.toString());	
        }
		
		PageInfo<PnRootPn> pageInfo = new PageInfo<>(list);
		
		log.info("Pages:"+pageInfo.getPages());
		log.info("Total:"+pageInfo.getTotal());
		Assert.assertEquals(pageInfo.getSize(),50);
		
	}
	
	@Test
	void testListString(){
		List<PnRootPn> listOri = pnMapper.findPnByLike("956A1001%");
		
		PageHelper.startPage(1,50);
		List<PnRootPn> listPage = pnMapper.findPnByLike("956A1001%");
		Assert.assertEquals(listOri.toString(), Utils.listToString(listPage));
		
	}
	
	@Test
	void testALLPn() {
		List<PnDetails> list = pnMapper.findAllPnByPnRoot("956A1006G01");
		
		for (PnDetails o : list) {
			log.info(o.toString());	
        }
						
		Assert.assertTrue(list.size()>0);
		
	}
	
	@Test
	void testSalesHistory() {
		PageHelper.startPage(1,50);
		List<SalesHistory> list = pnMapper.findSalesHistoryByPnRoot("HLBA-4");
		
		for (SalesHistory o : list) {
			log.info(o.toString());	
        }
		
		PageInfo<SalesHistory> pageInfo = new PageInfo<>(list);
		
		log.info("Pages:"+pageInfo.getPages());
		log.info("Total:"+pageInfo.getTotal());
		
		Assert.assertTrue(pageInfo.getSize()>=4);
		
	}
	
	@Test
	void testQuoteHistory() {
		PageHelper.startPage(1,50);
		List<QuoteHistory> list = pnMapper.findQuoteHistoryByPnRoot("HLBA-4");
		
		for (QuoteHistory o : list) {
			log.info(o.toString());	
        }
		
		PageInfo<QuoteHistory> pageInfo = new PageInfo<>(list);
		
		log.info("Pages:"+pageInfo.getPages());
		log.info("Total:"+pageInfo.getTotal());
		
		Assert.assertTrue(pageInfo.getSize()>=0);
		
	}
	
	@Test
	void testDeliveryDuration()	{
		List<DeliveryDuration> list = pnMapper.findDeliveryDurationByPnRoot("HLBA-4");
		
		for (DeliveryDuration o : list) {
			log.info(o.toString());	
        }
	}
	
	@Test
	void testStockInfo()	{
		List<StockInfo> list = stockMapper.findStockInfoByPnRoot("HLBA-4");
		
		for (StockInfo o : list) {
			log.info(o.toString());
        }
	}
	
	@Test
	void testTobeDeliveryBySite() {
		List<TobeDelivery> list = statusMapper.findTobeDeliveryBySite("ZHU");
		
		for (TobeDelivery o : list) {
			log.info(o.toString());	
        }
	}
	
	@Test
	void testTobeDealWithOrderLineBySite() {
		List<TobeDealWithOrderLine> list = statusMapper.findTobeDealWithOrderLineBySite("ZHU");
		
		for (TobeDealWithOrderLine o : list) {
			log.info(o.toString());	
        }
	}
	
	@Test
	void testTobePurchaseBomBySite() {
		List<TobePurchaseBom> list = statusMapper.findTobePurchaseBomBySite("ZHU");
		
		for (TobePurchaseBom o : list) {
			log.info(o.toString());	
        }
	}
	
	@Test
	void testTobeReceiveBySite() {
		List<TobeReceive> list = statusMapper.findTobeReceiveBySite("ZHU");
		
		for (TobeReceive o : list) {
			log.info(o.toString());	
        }
	}
	
	@Test
	void testPnObseleteBySite() {
		List<PnStatus> list = pnMapper.findObseletPnBySite("ZHU");
		
		for (PnStatus o : list) {
			log.info(o.toString());	
        }
	}
}
