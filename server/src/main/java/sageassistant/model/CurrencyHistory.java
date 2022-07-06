package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class CurrencyHistory extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String Sour;
	private String Dest;
	private Float Rate;
	private Date StartDate;
	private Date EndDate;

	public String getSour() {
		return Sour;
	}

	public void setSour(String sour) {
		Sour = sour;
	}

	public String getDest() {
		return Dest;
	}

	public void setDest(String dest) {
		Dest = dest;
	}

	public Float getRate() {
		return Rate;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

}
