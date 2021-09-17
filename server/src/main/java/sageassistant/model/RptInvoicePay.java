package sageassistant.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class RptInvoicePay {
	private String NUM_0;
	private Date DUDDAT_0;
	private Integer PAMTYP_0;
	private String PAM_0;
	private String CUR_0;
	private BigDecimal AMTCUR_0;
	private BigDecimal PAYCUR_0;
	private BigDecimal TMPCUR_0;
	
	public String getNUM_0() {
		return NUM_0;
	}
	public void setNUM_0(String nUM_0) {
		NUM_0 = nUM_0;
	}
	public Date getDUDDAT_0() {
		return DUDDAT_0;
	}
	public void setDUDDAT_0(Date dUDDAT_0) {
		DUDDAT_0 = dUDDAT_0;
	}
	public Integer getPAMTYP_0() {
		return PAMTYP_0;
	}
	public void setPAMTYP_0(Integer pAMTYP_0) {
		PAMTYP_0 = pAMTYP_0;
	}
	public String getPAM_0() {
		return PAM_0;
	}
	public void setPAM_0(String pAM_0) {
		PAM_0 = pAM_0;
	}
	public String getCUR_0() {
		return CUR_0;
	}
	public void setCUR_0(String cUR_0) {
		CUR_0 = cUR_0;
	}
	public BigDecimal getAMTCUR_0() {
		return AMTCUR_0;
	}
	public void setAMTCUR_0(BigDecimal aMTCUR_0) {
		AMTCUR_0 = aMTCUR_0;
	}
	public BigDecimal getPAYCUR_0() {
		return PAYCUR_0;
	}
	public void setPAYCUR_0(BigDecimal pAYCUR_0) {
		PAYCUR_0 = pAYCUR_0;
	}
	public BigDecimal getTMPCUR_0() {
		return TMPCUR_0;
	}
	public void setTMPCUR_0(BigDecimal tMPCUR_0) {
		TMPCUR_0 = tMPCUR_0;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}

}
