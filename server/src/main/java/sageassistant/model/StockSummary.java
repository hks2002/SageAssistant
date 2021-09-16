package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class StockSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private String G;
	private String A;
	private String Location;
	private String PN;
	private String Description;
	private String OptionPN;
	private Float Qty;
	private Float Cost;


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
	
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

	public Float getCost() {
		return Cost;
	}

	public void setCost(Float cost) {
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
