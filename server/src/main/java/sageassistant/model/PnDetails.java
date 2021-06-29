package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class PnDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ROWID;
	private String PNROOT;
	private String PN;
	private String Cat;
	private String Version;
	private String WC;
	private String Desc1;
	private String Desc2;
	private String Desc3;
	private Integer Status;
	private Date CreateDate;

	public Integer getROWID() {
		return ROWID;
	}

	public void setROWID(Integer rOWID) {
		ROWID = rOWID;
	}

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

	public String getCat() {
		return Cat;
	}

	public void setCat(String cat) {
		Cat = cat;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getWC() {
		return WC;
	}

	public void setWC(String wC) {
		WC = wC;
	}

	public String getDesc1() {
		return Desc1;
	}

	public void setDesc1(String desc1) {
		Desc1 = desc1;
	}

	public String getDesc2() {
		return Desc2;
	}

	public void setDesc2(String desc2) {
		Desc2 = desc2;
	}

	public String getDesc3() {
		return Desc3;
	}

	public void setDesc3(String desc3) {
		Desc3 = desc3;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
