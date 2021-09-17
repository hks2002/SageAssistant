package sageassistant.model;

public class Attachment extends AmodelTemplate {

	private static final long serialVersionUID = 1L;
	private String PN;
	private String DocType;
	private String Path;
	private String File;
	private String Cat;

	public String getPn() {
		return PN;
	}

	public void setPn(String pn) {
		PN = pn;
	}

	public String getDocType() {
		return DocType;
	}

	public void setDocType(String docType) {
		DocType = docType;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}

	public String getCat() {
		return Cat;
	}

	public void setCat(String cat) {
		Cat = cat;
	}

}
