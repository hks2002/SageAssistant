package sageassistant.model;

import java.io.Serializable;
import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class TobeReceive implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ProjectNO;
	private String PurchaseNO;
	private String Line;
	private String VendorCode;
	private String VendorName;
	private String PN;
	private Integer Qty;
	private String Unit;
	private String Description;
	private Float NetPrice;
	private String Currency;
	private Float RMB;
	private Float Rate;
	private Date OrderDate;
	private Date AckDate;
	private Date ExpectDate;
	private String CreateUser;

	public Date getAckDate() {
		return AckDate;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public String getCurrency() {
		return Currency;
	}

	public String getDescription() {
		return Description;
	}

	public Date getExpectDate() {
		return ExpectDate;
	}

	public String getLine() {
		return Line;
	}

	public Float getNetPrice() {
		return NetPrice;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public String getPN() {
		return PN;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public String getPurchaseNO() {
		return PurchaseNO;
	}

	public int getQty() {
		return Qty;
	}

	public Float getRate() {
		return Rate;
	}

	public Float getRMB() {
		return RMB;
	}

	public String getUnit() {
		return Unit;
	}

	public String getVendorCode() {
		return VendorCode;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setAckDate(Date ackDate) {
		AckDate = ackDate;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setExpectDate(Date expectDate) {
		ExpectDate = expectDate;
	}

	public void setLine(String line) {
		Line = line;
	}

	public void setNetPrice(Float netPrice) {
		NetPrice = netPrice;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public void setPurchaseNO(String purchaseNO) {
		PurchaseNO = purchaseNO;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public void setRMB(Float rMB) {
		RMB = rMB;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
