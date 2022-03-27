package sageassistant.model;

import sageassistant.model.base.ModelTemplate;

public class PnRootPn extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String PNROOT;
	private String PN;

	public String getPNROOT() {
		return PNROOT;
	}

	public void setPNROOT(String pNROOT) {
		PNROOT = pNROOT;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

}
