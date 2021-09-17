package sageassistant.model;

import java.math.BigDecimal;

public class StockSummary extends AmodelTemplate {

	private static final long serialVersionUID = 1L;

	private String G;
	private String A;
	private String Location;
	private String PN;
	private String Description;
	private String OptionPN;
	private Float Qty;
	private BigDecimal Cost;

	public String getG() {
		return G;
	}

	public void setG(String g) {
		G = g;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public Float getQty() {
		return Qty;
	}

	public void setQty(Float qty) {
		Qty = qty;
	}

	public BigDecimal getCost() {
		return Cost;
	}

	public void setCost(BigDecimal cost) {
		Cost = cost;
	}

	public String getOptionPN() {
		return OptionPN;
	}

	public void setOptionPN(String optionPN) {
		OptionPN = optionPN;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
}
