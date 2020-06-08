package sageassistant.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;

//@Slf4j  //using it cause this class Utils cannot directly use in other class method
public class Utils {
	private static final Logger log = LoggerFactory.getLogger(Utils.class);


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
	
	public static long dateDiff(Date start, Date end) {
		return (end.getTime()-start.getTime())/(24*60*60*1000);
	}

	public static String makeShortPn(String pn) {

		String newPn = pn;
		// if PN startwith
		// 2C,7C,9C,9R,11C,97A,98A,98D,98F,98S,98L,98V,98F,99A,99D,99F,856A,956A,HU,RRT,330A,332A,350A,365A,9446M,9426M,9429M,9465M,9471M,9448M,9486M
		// end with G01, G01_A
		// remove G01-G09,P01-G09
		// remvoe end _-
		log.debug("[makeShortPn] " + newPn);

		if (Pattern.matches("(.*)(NQ|NQD|CPD)(_|-)(.*)", newPn)) {
			Matcher matcher = Pattern.compile("(.*)(NQ|NQD|CPD)(_|-)(.*)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + matcher.group(3) + matcher.group(4);
			}
			log.debug("(.*)(NQ|NQD|CPD)(_|-)(.*) --> " + newPn);
		}
		if (Pattern.matches("(.*)(P\\d{2})$", newPn)) {
			Matcher matcher = Pattern.compile("(.*)(P\\d{2})$").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1);
			}
			log.debug("(.*)(P\\d{2})$ --> " + newPn);
		}
		if (Pattern.matches("(.*)(P\\d{2})(_|-)(.*)", newPn)) {
			Matcher matcher = Pattern.compile("(.*)(P\\d{2})(_|-)(.*)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + matcher.group(3) + matcher.group(4);
			}
			log.debug("(.*)(P\\d{2})(_|-)(.*) --> " + newPn);
		}
		if (Pattern.matches("(.*)(G\\d{2})$", newPn)) {
			Matcher matcher = Pattern.compile("(.*)(G\\d{2})$").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1);
			}
			log.debug("(.*)(G\\d{2})$ --> " + newPn);
		}
		if (Pattern.matches("(.*)(G\\d{2})(_|-)(.*)", newPn)) {
			Matcher matcher = Pattern.compile("(.*)(G\\d{2})(_|-)(.*)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + matcher.group(3) + matcher.group(4);
			}
			log.debug("(.*)(G\\d{2})(_|-)(.*) --> " + newPn);
		}
		if (Pattern.matches("^([A-Z|0-9|\\-]*)?(_\\-*)?$", newPn)) {
			Matcher matcher = Pattern.compile("^([A-Z|0-9|\\-]*)?(_\\-*)?$").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1);
				log.debug("xxxxxx--> " + newPn);
			}
			log.debug("^([A-Z|0-9|\\-]*)?(_\\-*)?$ --> " + newPn);
		}
		///////////////////////////////////////////////////////////////////////////////
		if (Pattern.matches("^([9][7|8|9][ADFSLV])(\\d{8})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^([9][7|8|9][ADFSLV])(\\d{8})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(pn);

			while (matcher.find()) {
				newPn = matcher.group(1) + matcher.group(2) + (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^([9][7|8|9][ADFSLV])(\\d{8})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);

		} else if (Pattern.matches("^(2C|7C|9C|9R|11C\\d{4,5})(-\\d{1,2})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(2C|7C|9C|9R|11C\\d{4,5})(-\\d{1,2})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(2) != null ? matcher.group(2) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(2C|7C|9C|9R|11C\\d{4,5})(-\\d{1,2})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(856A|956A)(\\d{4})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(856A|956A)(\\d{4})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(2) != null ? matcher.group(2) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(856A|956A)(\\d{4})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(RRT\\d{6})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(RRT\\d{6})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(2) != null ? matcher.group(2) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(RRT\\d{6})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(HU\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(HU\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(2) != null ? matcher.group(2) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(HU\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(330A|332A|350A|365A)(\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(330A|332A|350A|365A)(\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(2) != null ? matcher.group(2) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(330A|332A|350A|365A)(\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(94\\d{2}M\\d{2})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(94\\d{2}M\\d{2})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(3) != null ? matcher.group(3) : "")
						+ (matcher.group(4) != null ? matcher.group(4) : "");
			}
			log.debug("^(94\\d{2}M\\d{2})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^([A|B|C|F|G|J|K]\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^([A|B|C|F|G|J|K]\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)")
					.matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^([A|B|C|F|G|J|K]\\d{5})(-\\d{1,3})?((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(98AMS\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(98AMS\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(98AMS\\d{6})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else if (Pattern.matches("^(98DNSA\\d{5})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)", newPn)) {
			Matcher matcher = Pattern.compile("^(98DNSA\\d{5})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$)").matcher(newPn);

			while (matcher.find()) {
				newPn = matcher.group(1) + (matcher.group(4) != null ? matcher.group(4) : "")
						+ (matcher.group(5) != null ? matcher.group(5) : "");
			}
			log.debug("^(98DNSA\\d{5})\\d*((_[0-9|A-Z]{0,2})?$|(_P-\\d)?$) --> " + newPn);
		} else {
		}

		if (Utils.isNullOrEmpty(newPn)) {
			log.error("[makeShortPn] " + pn);
		}
		return newPn;
	}
}
