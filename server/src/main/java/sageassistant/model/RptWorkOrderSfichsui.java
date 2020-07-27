package sageassistant.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class RptWorkOrderSfichsui {
	private String ATEXTRA_TEXTE;
	private Short OPENUM_0;
	private Float EXTOPETIM_0;
	private Float EXTSETTIM_0;
	private Float EXTUNTTIM_0;
	private String EXTWST_0;
	private String MFGFCY_0;
	private String MFGNUM_0;
	private Byte OPESTA_0;
	private String ROODES_0;
	private String SCOITMREF_0;
	private Byte DACMST_0;
	private Byte MFGMST_0;
	private String TEXTE_0;
	private String WCR_0;
	private Byte WSTTYP_0;
	
	public String getATEXTRA_TEXTE() {
		return ATEXTRA_TEXTE;
	}
	public void setATEXTRA_TEXTE(String aTEXTRA_TEXTE) {
		ATEXTRA_TEXTE = aTEXTRA_TEXTE;
	}
	public Short getOPENUM_0() {
		return OPENUM_0;
	}
	public void setOPENUM_0(Short oPENUM_0) {
		OPENUM_0 = oPENUM_0;
	}
	public Float getEXTOPETIM_0() {
		return EXTOPETIM_0;
	}
	public void setEXTOPETIM_0(Float eXTOPETIM_0) {
		EXTOPETIM_0 = eXTOPETIM_0;
	}
	public Float getEXTSETTIM_0() {
		return EXTSETTIM_0;
	}
	public void setEXTSETTIM_0(Float eXTSETTIM_0) {
		EXTSETTIM_0 = eXTSETTIM_0;
	}
	public Float getEXTUNTTIM_0() {
		return EXTUNTTIM_0;
	}
	public void setEXTUNTTIM_0(Float eXTUNTTIM_0) {
		EXTUNTTIM_0 = eXTUNTTIM_0;
	}
	public String getEXTWST_0() {
		return EXTWST_0;
	}
	public void setEXTWST_0(String eXTWST_0) {
		EXTWST_0 = eXTWST_0;
	}
	public String getMFGFCY_0() {
		return MFGFCY_0;
	}
	public void setMFGFCY_0(String mFGFCY_0) {
		MFGFCY_0 = mFGFCY_0;
	}
	public String getMFGNUM_0() {
		return MFGNUM_0;
	}
	public void setMFGNUM_0(String mFGNUM_0) {
		MFGNUM_0 = mFGNUM_0;
	}
	public Byte getOPESTA_0() {
		return OPESTA_0;
	}
	public void setOPESTA_0(Byte oPESTA_0) {
		OPESTA_0 = oPESTA_0;
	}
	public String getROODES_0() {
		return ROODES_0;
	}
	public void setROODES_0(String rOODES_0) {
		ROODES_0 = rOODES_0;
	}
	public String getSCOITMREF_0() {
		return SCOITMREF_0;
	}
	public void setSCOITMREF_0(String sCOITMREF_0) {
		SCOITMREF_0 = sCOITMREF_0;
	}
	public Byte getDACMST_0() {
		return DACMST_0;
	}
	public void setDACMST_0(Byte dACMST_0) {
		DACMST_0 = dACMST_0;
	}
	public Byte getMFGMST_0() {
		return MFGMST_0;
	}
	public void setMFGMST_0(Byte mFGMST_0) {
		MFGMST_0 = mFGMST_0;
	}
	public String getTEXTE_0() {
		return TEXTE_0;
	}
	public void setTEXTE_0(String tEXTE_0) {
		TEXTE_0 = tEXTE_0;
	}
	public String getWCR_0() {
		return WCR_0;
	}
	public void setWCR_0(String wCR_0) {
		WCR_0 = wCR_0;
	}
	public Byte getWSTTYP_0() {
		return WSTTYP_0;
	}
	public void setWSTTYP_0(Byte wSTTYP_0) {
		WSTTYP_0 = wSTTYP_0;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
