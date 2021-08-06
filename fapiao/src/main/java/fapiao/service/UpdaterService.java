package fapiao.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fapiao.dao.BodyDao;
import fapiao.dao.HeaderDao;
import fapiao.model.Body;
import fapiao.model.Header;

@Service
public class UpdaterService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	HeaderDao headerDao;
	
	@Autowired
	BodyDao bodyDao;
	
	private boolean isWin() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) { // windows
			return true;
		} else { // linux 和mac
			return false;
		}
	}

	private String getFapiaoPath() {
		if (isWin()) {
			return "d:/fapiao";
		} else {
			return "/mnt/fapiao";
		}
	}

	private boolean parseFapiao(File file) {
		try {
			SAXReader reader = new SAXReader();
			FileInputStream fis = new FileInputStream(file);
			Document doc = reader.read(fis);
			Element root = doc.getRootElement();

			Element Fpsj = (Element) root.selectSingleNode("//Fpxx//Fpsj");

			@SuppressWarnings("rawtypes")
			Iterator iteratorFpsj = Fpsj.elementIterator(); // 再从子节点在遍历其子节点
			while (iteratorFpsj.hasNext()) {
				Element Fp = (Element) iteratorFpsj.next();

				/*System.out.println();
				System.out.print(Fp.selectSingleNode("Fpzl").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Lbdm").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Fphm").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Kprq").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Gfmc").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Gfsh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Gfyhzh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Gfdzdh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Xfmc").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Xfsh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Xfyhzh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Xfdzdh").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Hjje").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Hjse").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Bz").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Kpr").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Fhr").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Skr").getText() + "\t");
				System.out.print(Fp.selectSingleNode("Fpzt").getText() + "\t");*/

				Header header = new Header();
				
				String Lbdm = Fp.selectSingleNode("Lbdm").getText();
				String Fphm = Fp.selectSingleNode("Fphm").getText();
				header.setLbdm(Lbdm);
				header.setFphm(Fphm);
				header.setFpzl(Fp.selectSingleNode("Fpzl").getText());
				header.setKprq(Fp.selectSingleNode("Kprq").getText());
				header.setGfmc(Fp.selectSingleNode("Gfmc").getText());
				header.setGfsh(Fp.selectSingleNode("Gfsh").getText());
				header.setGfyhzh(Fp.selectSingleNode("Gfyhzh").getText());
				header.setGfdzdh(Fp.selectSingleNode("Gfdzdh").getText());
				header.setXfmc(Fp.selectSingleNode("Xfmc").getText());
				header.setXfsh(Fp.selectSingleNode("Xfsh").getText());
				header.setXfyhzh(Fp.selectSingleNode("Xfyhzh").getText());
				header.setXfdzdh(Fp.selectSingleNode("Xfdzdh").getText());
				header.setHjje(Fp.selectSingleNode("Hjje").getText());
				header.setHjse(Fp.selectSingleNode("Hjse").getText());
				header.setBz(Fp.selectSingleNode("Bz").getText());
				header.setKpr(Fp.selectSingleNode("Kpr").getText());
				header.setFhr(Fp.selectSingleNode("Fhr").getText());
				header.setSkr(Fp.selectSingleNode("Skr").getText());
				header.setFpzt(Fp.selectSingleNode("Fpzt").getText());
				
				
				if (headerDao.getHeader(Lbdm, Fphm).isEmpty()){
					log.info("++发票:" + Fp.selectSingleNode("Fphm").getText());
					headerDao.addHeader(header);
				} else {
					log.info("^^发票:" + Fp.selectSingleNode("Fphm").getText());
					headerDao.updateHeader(header);
				}
				
				Element Spxx = (Element) Fp.selectSingleNode("Spxx");
				@SuppressWarnings("rawtypes")
				Iterator iteratorSpxx = Spxx.elementIterator(); // 再从子节点在遍历其子节点
				while (iteratorSpxx.hasNext()) {
					Element Sph = (Element) iteratorSpxx.next();
					
					Body body = new Body();
					
					String Xh = Sph.selectSingleNode("Xh").getText();
					log.debug(Xh);
					
					body.setLbdm(Lbdm);
					body.setFphm(Fphm);
					body.setXh(Xh);
					body.setSpmc(Sph.selectSingleNode("Spmc").getText());
					body.setGgxh(Sph.selectSingleNode("Ggxh").getText());
					body.setJldw(Sph.selectSingleNode("Jldw").getText());
					body.setDj(Sph.selectSingleNode("Dj").getText());
					body.setSl(Sph.selectSingleNode("Sl").getText());
					body.setJe(Sph.selectSingleNode("Je").getText());
					body.setSlv(Sph.selectSingleNode("Slv").getText());
					body.setSe(Sph.selectSingleNode("Se").getText());
					
					if (bodyDao.getBody(Lbdm, Fphm, Xh).isEmpty()){
						log.debug("++商品:" + Sph.selectSingleNode("Spmc").getText());
						bodyDao.addBody(body);
					} else {
						log.debug("^^商品:" + Sph.selectSingleNode("Spmc").getText());
						bodyDao.updateBody(body);
					}
				}
				
			}
			fis.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// @Scheduled(fixedRate = 60000) // 60秒执行一次
	// @Scheduled(initialDelay = 1000, fixedDelay = 60000) // 执行完上一次后，60秒再继续执行
	// @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE) // 5秒后仅执行一次	
	@Scheduled(initialDelay = 1000, fixedDelay = 60000) // 执行完上一次后，60秒再继续执行
	public void updateFapiao() {
		log.info("读取发票数据>>>>>>>>>>");
		File fapiaoPath = new File(getFapiaoPath());

		for (File file : fapiaoPath.listFiles()) {
			if (file.isFile() && file.getName().startsWith("开票资料导出") && file.getName().endsWith(".xml")) {
				log.info(file.getName());
				if (parseFapiao(file)) {
					File doneFile = new File(file.getAbsolutePath()+".done");
					
					file.renameTo(doneFile);
				};
			}
		}
		log.info("读取发票数据<<<<<<<<<<");
	}

}