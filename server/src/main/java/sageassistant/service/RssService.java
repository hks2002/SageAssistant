package sageassistant.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.model.PnStatus;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.utils.Utils;

@Service
public class RssService {
	// private static final Logger log = LoggerFactory.getLogger(RssService.class);

	@Autowired
	private PnService pnService;

	@Autowired
	private StatusService statusService;

	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public String checkPnUpdate(String site) {
		List<PnStatus> list = pnService.findObseletPnBySite(site);

		String content = "<table border=\"2\" style=\"border-collapse:collapse;\">";
		content += "<tr>";
		content += "<th>PN</th>";
		content += "<th>Desc1</th>";
		content += "<th>Desc2</th>";
		content += "<th>Desc3</th>";
		content += "<th>PN Status</th>";
		content += "<th>SalesOrder</th>";
		content += "<th>CustomerCode</th>";
		content += "<th>CustomerName</th>";
		content += "</tr>";

		for (PnStatus o : list) {
			content += "<tr style=\"background-color:red;\">";
			content += "<td>" + o.getPN() + "</td>";
			content += "<td>" + o.getDesc1() + "</td>";
			content += "<td>" + o.getDesc2() + "</td>";
			content += "<td>" + o.getDesc3() + "</td>";
			content += "<td>" + o.getPNStatus() + "</td>";
			content += "<td>" + o.getSalesOrderNO() + "</td>";
			content += "<td>" + o.getCustomerCode() + "</td>";
			content += "<td>" + o.getCustomerName() + "</td>";
			content += "</tr>";
		}
		content += "</table>";

		if (list.size() > 0) {
			return content;
		} else {
			return "";
		}

	}

	public String checkTobeDelivery(String site) {
		List<TobeDelivery> list = statusService.findTobeDeliveryBySite(site, 0);

		String content = "<table border=\"2\" style=\"border-collapse:collapse;\">";

		content += "<tr>";
		content += "<th>ProjectNO</th>";
		content += "<th>PN</th>";
		content += "<th>Desc</th>";
		content += "<th>Qty</th>";
		content += "<th>OrderDate</th>";
		content += "<th>RequestDate</th>";
		content += "<th>OverDueDays</th>";
		content += "</tr>";

		for (TobeDelivery o : list) {
			long diff = Utils.dateDiff(o.getRequestDate(), new Date());
			if (diff > 0) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getRequestDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			}
			if (diff < 2) {
				content += "<tr style=\"background-color:yellow;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getRequestDate()) + "</td>";
				content += "<td></td>";
				content += "</tr>";
			}
		}
		content += "</table>";

		if (list.size() > 0) {
			return content;
		} else {
			return "";
		}
	}

	public String checkTobeReceive(String site) {
		List<TobeReceive> list = statusService.findTobeReceiveBySite(site, 0);

		String content = "<table border=\"2\" style=\"border-collapse:collapse;\">";

		content += "<tr>";
		content += "<th>PurchaseNO</th>";
		content += "<th>Line</th>";
		content += "<th>ProjectNO</th>";
		content += "<th>PN</th>";
		content += "<th>Desc</th>";
		content += "<th>Qty</th>";
		content += "<th>OrderDate</th>";
		content += "<th>AckDate</th>";
		content += "<th>ExpectDate</th>";
		content += "<th>OverDueDays</th>";
		content += "</tr>";

		for (TobeReceive o : list) {
			long diff = Utils.dateDiff(o.getExpectDate(), new Date());

			if (diff > 0) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getPurchaseNO() + "</td>";
				content += "<td>" + o.getLine() + "</td>";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getAckDate()) + "</td>";
				content += "<td>" + fmt.format(o.getExpectDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			}
			if (diff < 2) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getPurchaseNO() + "</td>";
				content += "<td>" + o.getLine() + "</td>";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getAckDate()) + "</td>";
				content += "<td>" + fmt.format(o.getExpectDate()) + "</td>";
				content += "<td></td>";
				content += "</tr>";
			}
		}
		content += "</table>";

		if (list.size() > 0) {
			return content;
		} else {
			return "";
		}
	}

	public String checkTobePurchaseBom(String site) {
		List<TobePurchaseBom> list = statusService.findTobePurchaseBomBySite(site, 0);

		String content = "<table border=\"2\" style=\"border-collapse:collapse;\">";

		content += "<tr>";
		content += "<th>ProjectNO</th>";
		content += "<th>PN2</th>";
		content += "<th>WorkOrderNO</th>";
		content += "<th>getBomSeq</th>";
		content += "<th>PN</th>";
		content += "<th>Desc</th>";
		content += "<th>Qty</th>";
		content += "<th>ShortQty</th>";
		content += "<th>AllQty</th>";
		content += "<th>CreateDate</th>";
		content += "<th>Days</th>";
		content += "</tr>";

		for (TobePurchaseBom o : list) {
			long diff = Utils.dateDiff(o.getCreateDate(), new Date());

			if (diff > 7) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getForPN() + "</td>";
				content += "<td>" + o.getWorkOrderNO() + "</td>";
				content += "<td>" + o.getBomSeq() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + o.getShortQty() + "</td>";
				content += "<td>" + o.getAllQty() + "</td>";
				content += "<td>" + fmt.format(o.getCreateDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			} else if (diff > 3) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getForPN() + "</td>";
				content += "<td>" + o.getWorkOrderNO() + "</td>";
				content += "<td>" + o.getBomSeq() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + o.getShortQty() + "</td>";
				content += "<td>" + o.getAllQty() + "</td>";
				content += "<td>" + fmt.format(o.getCreateDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			}
		}
		content += "</table>";

		if (list.size() > 0) {
			return content;
		} else {
			return "";
		}
	}

	public String checkTobeDealWithOrderLine(String site) {
		List<TobeDealWithOrderLine> list = statusService.findTobeDealWithOrderLineBySite(site, 0);

		String content = "<table border=\"2\" style=\"border-collapse:collapse;\">";

		content += "<tr>";
		content += "<th>ProjectNO</th>";
		content += "<th>OrderType</th>";
		content += "<th>PN</th>";
		content += "<th>Desc</th>";
		content += "<th>Qty</th>";
		content += "<th>Unit</th>";
		content += "<th>Customer</th>";
		content += "<th>OrDerDate</th>";
		content += "<th>DemandDate</th>";
		content += "<th>Days</th>";
		content += "</tr>";

		for (TobeDealWithOrderLine o : list) {
			long diff = Utils.dateDiff(o.getOrderDate(), new Date());

			if (diff > 7) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getOrderType() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + o.getUnit() + "</td>";
				content += "<td>" + o.getCustomerCode() + "</td>";
				content += "<td>" + o.getCustomerName() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getDemandDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			} else if (diff > 3) {
				content += "<tr style=\"background-color:red;\">";
				content += "<td>" + o.getProjectNO() + "</td>";
				content += "<td>" + o.getOrderType() + "</td>";
				content += "<td>" + o.getPN() + "</td>";
				content += "<td>" + o.getDescription() + "</td>";
				content += "<td>" + o.getQty() + "</td>";
				content += "<td>" + o.getUnit() + "</td>";
				content += "<td>" + o.getCustomerCode() + "</td>";
				content += "<td>" + o.getCustomerName() + "</td>";
				content += "<td>" + fmt.format(o.getOrderDate()) + "</td>";
				content += "<td>" + fmt.format(o.getDemandDate()) + "</td>";
				content += "<td>" + diff + "</td>";
				content += "</tr>";
			}
		}
		content += "</table>";

		if (list.size() > 0) {
			return content;
		} else {
			return "";
		}

	}
}
