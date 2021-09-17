package sageassistant.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.InvoiceMapper;
import sageassistant.model.InvoiceBody;
import sageassistant.model.InvoiceHeader;
import sageassistant.utils.Utils;

@Service
public class InvoiceService {
	//private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private InvoiceMapper invoiceMapper;

	public String findInvoiceNOByInvoiceNO(String InvoiceNO, Integer count) {
		PageHelper.startPage(1, count);
		List<String> listPage = invoiceMapper.findInvoiceNOByInvoiceNO("%" + InvoiceNO + "%");

		return Utils.list2String(listPage);
	}
	
	public InvoiceHeader findInvoiceHeaderByInvoiceNO(String InvoiceNO) {
		return invoiceMapper.findInvoiceHeaderByInvoiceNO(InvoiceNO);
	}
	
	public List<InvoiceBody> findInvoiceBodyByInvoiceNO(String InvoiceNO) {
		return invoiceMapper.findInvoiceBodyByInvoiceNO(InvoiceNO);
	}
	
	public InvoiceHeader findInvoiceHeaderByFaPiao(String FaPiao) {
		List<InvoiceHeader> list = invoiceMapper.findInvoiceHeaderByFaPiao(FaPiao);
		InvoiceHeader invoiceHeader= new InvoiceHeader();
		Set<String> Facility = new HashSet<String>();
		Set<String> Currency = new HashSet<String>();
		Set<String> InvoiceNO = new HashSet<String>();
		Set<String> CreateDate = new HashSet<String>();
		Set<String> CreateUser = new HashSet<String>();
		Set<String> Note = new HashSet<String>();
		Set<String> InvoiceStatus = new HashSet<String>();
		Set<String> faPiao = new HashSet<String>();
		Set<String> Address = new HashSet<String>();
		Float CurrRate = (float) 0;
		BigDecimal AmountTaxInclude = new BigDecimal("0");
		BigDecimal AmountTaxNotInclude = new BigDecimal("0");
		BigDecimal AmountTax = new BigDecimal("0");
		
		for ( InvoiceHeader o : list) {
			Facility.add(o.getFacility());
			Currency.add(o.getCurrency());
			InvoiceNO.add(o.getInvoiceNO());
			CreateDate.add(o.getCreateDate());
			CreateUser.add(o.getCreateUser());
			Note.add(o.getNote());
			InvoiceStatus.add(o.getInvoiceStatus());
			faPiao.add(o.getFaPiao());
			Address.add(o.getAddress());
			CurrRate += o.getCurrRate();
			AmountTaxInclude = AmountTaxInclude.add(o.getAmountTaxInclude());
			AmountTaxNotInclude = AmountTaxNotInclude.add(o.getAmountTaxNotInclude());
			AmountTax = AmountTax.add(o.getAmountTax());
 		}
		invoiceHeader.setFacility(Facility.toString());
		invoiceHeader.setCurrency(Currency.toString());
		invoiceHeader.setInvoiceNO(InvoiceNO.toString());
		invoiceHeader.setCreateDate(CreateDate.toString());
		invoiceHeader.setCreateUser(CreateUser.toString());
		invoiceHeader.setNote(Note.toString());
		invoiceHeader.setInvoiceStatus(InvoiceStatus.toString());
		invoiceHeader.setFaPiao(faPiao.toString());
		invoiceHeader.setAddress(Address.toString());
		if (list.size()>0) {
			invoiceHeader.setCurrRate(CurrRate/list.size());
		}else {
			invoiceHeader.setCurrRate((float) 0);
		}
		
		invoiceHeader.setAmountTaxInclude(AmountTaxInclude);
		invoiceHeader.setAmountTaxNotInclude(AmountTaxNotInclude);
		invoiceHeader.setAmountTax(AmountTax);
		
		return invoiceHeader;
	}
	
	public List<InvoiceBody> findInvoiceBodyByFaPiao(String FaPiao) {
		return invoiceMapper.findInvoiceBodyByFaPiao(FaPiao);
	}
		
	
}
