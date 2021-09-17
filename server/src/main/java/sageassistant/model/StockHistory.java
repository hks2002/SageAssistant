package sageassistant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class StockHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	private String Location;
	private String Seq;
	private String PN;
	private String Description;
	private Float Qty;
	private String Unit;
	private BigDecimal Cost;
	private String ProjectNO;
	private String SourceNO;
	private String SourceLine;
	private String EntryNO;
	private String EntryLine;
	private String CreateUser;
	private Date CreateDate;

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getSeq() {
		return Seq;
	}

	public void setSeq(String seq) {
		Seq = seq;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Float getQty() {
		return Qty;
	}

	public void setQty(Float qty) {
		Qty = qty;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public BigDecimal getCost() {
		return Cost;
	}

	public void setCost(BigDecimal cost) {
		Cost = cost;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getSourceNO() {
		return SourceNO;
	}

	public void setSourceNO(String sourceNO) {
		SourceNO = sourceNO;
	}

	public String getSourceLine() {
		return SourceLine;
	}

	public void setSourceLine(String sourceLine) {
		SourceLine = sourceLine;
	}

	public String getEntryNO() {
		return EntryNO;
	}

	public void setEntryNO(String entryNO) {
		EntryNO = entryNO;
	}

	public String getEntryLine() {
		return EntryLine;
	}

	public void setEntryLine(String entryLine) {
		EntryLine = entryLine;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
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
