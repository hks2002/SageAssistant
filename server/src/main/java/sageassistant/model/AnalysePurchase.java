package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class AnalysePurchase extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String ProjectNO;
	private String PurchaseNO;
	private BigDecimal NetPrice;
	private Date ProjectDate;
	private Date PurchaseDate;
	private Integer ROWNUM;

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getPurchaseNO() {
		return PurchaseNO;
	}

	public void setPurchaseNO(String purchaseNO) {
		PurchaseNO = purchaseNO;
	}

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public Date getProjectDate() {
		return ProjectDate;
	}

	public void setProjectDate(Date projectDate) {
		ProjectDate = projectDate;
	}

	public Date getPurchaseDate() {
		return PurchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}

	public Integer getROWNUM() {
		return ROWNUM;
	}

	public void setROWNUM(Integer rOWNUM) {
		ROWNUM = rOWNUM;
	}

}
