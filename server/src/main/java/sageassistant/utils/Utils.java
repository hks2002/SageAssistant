package sageassistant.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.io.ByteStreams;

//@Slf4j  //using it cause this class Utils cannot directly use in other class method
public class Utils {
	private static final Logger log = LogManager.getLogger(Utils.class);

	public static String readFileContent(String filename) {
		// Reading files in jar, use getResourceAsStream(filename), here is reading for
		// war distribution
		String path = Thread.currentThread().getContextClassLoader().getResource(filename).getPath();
		log.debug("Resource base path :" + path);

		try {
			InputStream inputStream = new FileInputStream(path);
			return new String(ByteStreams.toByteArray(inputStream));
		} catch (FileNotFoundException e) {
			log.info("FileNotFound: " + path + filename);
			return "";
		} catch (IOException e) {
			log.error("IOException: " + "When reading " + filename);
			return "";
		}

	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (date== null) {
			return "";
		}
		return formatter.format(date);
	}

	public static String[] findFiles(File dir) {
		String[] files = {};

		if (!dir.exists()) {
			log.debug("Path is not exists: " + dir.getAbsolutePath());
			return files;
		}

		if (dir.exists() && dir.isDirectory()) {
			log.debug("Files In: " + dir.getAbsolutePath());

			String[] filesPaths = dir.list();
			List<String> fileNames = new ArrayList<>();

			for (int i = 0; i < filesPaths.length; i++) {
				File file = new File(filesPaths[i]);

				if (file.getName().startsWith("~")) {
				} else if (file.getName().toLowerCase().equals("thumbs.db")) {
				} else {
					fileNames.add(file.getName());
				}
			}
			files = fileNames.toArray(new String[fileNames.size()]);
		} else {
			log.warn("Path is not folder: " + dir.getAbsolutePath());
		}

		return files;
	}

	public static String[] findFiles(String path) {
		return findFiles(new File(path));
	}

	public static boolean isWin() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) { // windows
			return true;
		} else { // linux 和mac
			return false;
		}
	}

	public static String getFileExt(String filename) {
		int dot = filename.lastIndexOf('.');
		if ((dot > -1) && (dot < (filename.length() - 1))) {
			return filename.substring(dot + 1).toUpperCase();
		} else {
			return "";
		}

	}

	public static Boolean isServerAtZhuhai() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			if (addr.getHostAddress().startsWith("192.168.0.")) {
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static long dateDiff(Date start, Date end) {
		return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
	}

	public static String makeShortPn(String pn) {
		// if PN startwith
		// 2C,7C,9C,9R,11C,97A,98A,98D,98F,98S,98L,98V,98F,99A,99D,99F,856A,956A,HU,RRT,330A,332A,350A,365A,9446M,9426M,9429M,9465M,9471M,9448M,9486M
		// end with G01, G01_A
		// remove G01-G09,P01-G09
		// remvoe end _-
		log.debug("[makeShortPn] " + pn);
		// change / \ * ? to -
		String newPn = pn.replaceAll("(\\\\|\\*|\\/|\\?)", "-");
		log.debug("[makeShortPn00] " + newPn);
		// remove _-|__-- at tail
		newPn = newPn.replaceAll("_+", "_");
		newPn = newPn.replaceAll("-+", "-");
		newPn = newPn.replaceAll("(.*?)(_-)$", "$1");
		log.debug("[makeShortPn01] " + newPn);
		// remove _DRAFT|_QU|_NQ|_NQD|_CPD_PRT anywhere
		newPn = newPn.replaceAll("(.*)(_DRAFT|_QU|_NQD|_NQ|_CPD|_PRT)(.*)", "$1$3");
		log.debug("[makeShortPn02] " + newPn);
		// remove DRAFT|QU|NQ|NQD|CPDPRT at tail
		newPn = newPn.replaceAll("(.*)(DRAFT|QU|NQD|NQ|CPD|PRT)$", "$1");
		log.debug("[makeShortPn03] " + newPn);
		// remove DRAFT|QU|NQ|NQD|CPD|PRT|AF at midst
		newPn = newPn.replaceAll("(.*)(DRAFT|QU|NQD|NQ|CPD|PRT|AF)([_|-][A-Z|\\d]{1,3})$", "$1$3");
		newPn = newPn.replaceAll("(.*)(DRAFT|QU|NQD|NQ|CPD|PRT|AF)(-[\\d]{1,3})(.*)", "$1$4");
		log.debug("[makeShortPn04] " + newPn);
		// remove P01 at tail
		newPn = newPn.replaceAll("(.*)(P\\d{2})$", "$1");
		log.debug("[makeShortPn05] " + newPn);
		// remove PO1 at midst
		newPn = newPn.replaceAll("(.*)(P\\d{2})(_[A-Z]{1,3})$", "$1$3");
		log.debug("[makeShortPn06] " + newPn);
		// remove G01 at tail
		newPn = newPn.replaceAll("(.*)(G\\d{2})$", "$1");
		log.debug("[makeShortPn07] " + newPn);
		// remve G01|G01XX at midst
		newPn = newPn.replaceAll("(.*)(G\\d{2})([A-Z]{2})?(_[A-Z]{1,3})$", "$1$4");
		log.debug("[makeShortPn08] " + newPn);

		///////////////////////////////////////////////////////////////////////////////
		// 9[7|8|9][ADFGKSLV] + 8 bit
		// remove -00, _P-00
		newPn = newPn.replaceAll("^([9][7|8|9][ADFGKSLV])(\\d{8})\\d*(.*)", "$1$2$3");
		newPn = newPn.replaceAll("^([9][7|8|9][ADFGKSLV])(\\d{8})((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$2$6");
		log.debug("[makeShortPn09] " + newPn);

		// 856A|956A + 4 bit
		// remove -00, _P-00
		newPn = newPn.replaceAll("^(856A|956A)(\\d{4})\\d*(.*)", "$1$2$3");
		newPn = newPn.replaceAll("^(856A|956A)(\\d{4})((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$2$6");
		log.debug("[makeShortPn10] " + newPn);

		// 2C|7C|9C|9R|11C + 4 or 5 bit and 2 ?
		// remove -00, _P-00
		newPn = newPn.replaceAll("^(2C|7C|9C|9R|11C\\d{4,5})(-\\d{1,2})?((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)",
				"$1$6");
		log.debug("[makeShortPn11] " + newPn);

		newPn = newPn.replaceAll("^(RRT\\d{6})(-\\d{1,3})?((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$2$6");
		log.debug("[makeShortPn12] " + newPn);

		newPn = newPn.replaceAll("^(HU\\d{5})(-\\d{1,3})?((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$2$6");
		log.debug("[makeShortPn13] " + newPn);

		newPn = newPn.replaceAll("^(330A|332A|350A|365A)(\\d{6})\\d*((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$2$6");
		log.debug("[makeShortPn14] " + newPn);

		newPn = newPn.replaceAll("^(94\\d{2}M\\d{2})\\d*((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$5");
		log.debug("[makeShortPn15] " + newPn);

		newPn = newPn.replaceAll("^(98AMS\\d{6})\\d*((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$5");
		log.debug("[makeShortPn16] " + newPn);

		newPn = newPn.replaceAll("^(98DNSA\\d{5})\\d*((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$5");
		log.debug("[makeShortPn17] " + newPn);

		newPn = newPn.replaceAll("^([A|B|C|F|G|J|K]\\d{5})(-\\d{1,3})?((-[0-9|A-Z]{0,2})|(_P-\\d{1,3}))?(.*)", "$1$6");
		log.debug("[makeShortPn18] " + newPn);

		if (Utils.isNullOrEmpty(newPn)) {
			log.error("[makeShortPn] " + pn);
		}
		return newPn;
	}
	
	// Page{count=true, pageNum=1, pageSize=50, startRow=0, endRow=50, total=13346, pages=267, reasonable=true, pageSizeZero=false}[{},{}]
	// [{},{}]
	public static String listToString(List<?> list) {
		return list.toString().replaceAll("(Page\\{.*?\\})(.*)","$2");
	}

}
