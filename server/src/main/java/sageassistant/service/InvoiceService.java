package sageassistant.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.InvoiceMapper;
import sageassistant.model.InvoiceBody;
import sageassistant.model.InvoiceHeader;

@Service
public class InvoiceService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private InvoiceMapper invoiceMapper;

	public List<String> findInvoiceNOByInvoiceNO(String InvoiceNO, Integer count) {
		PageHelper.startPage(1, count);
		List<String> listPage = invoiceMapper.findInvoiceNOByInvoiceNO("%" + InvoiceNO + "%");

		return listPage;
	}
	
	public InvoiceHeader findInvoiceHeaderByInvoiceNO(String InvoiceNO) {
		return invoiceMapper.findInvoiceHeaderByInvoiceNO(InvoiceNO);
	}
	
	public List<InvoiceBody> findInvoiceBodyByInvoiceNO(String InvoiceNO) {
		return invoiceMapper.findInvoiceBodyByInvoiceNO(InvoiceNO);
	}
		
	
}
