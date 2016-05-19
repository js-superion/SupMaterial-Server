package cn.superion.cssd.entity;

/**
 * CssdBiologyFiles entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CssdBiologyFiles implements java.io.Serializable {

	// Fields

	private String autoId;
	private String serialNo;
	private String sysFile;
	private String fileName;
	private String filePath;
	private String mainAutoId;

	// Constructors

	/** default constructor */
	public CssdBiologyFiles() {
	}

	/** minimal constructor */
	public CssdBiologyFiles(String autoId, String serialNo, String sysFile,
			String mainAutoId) {
		this.autoId = autoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
		this.mainAutoId = mainAutoId;
	}

	/** full constructor */
	public CssdBiologyFiles(String autoId, String serialNo, String sysFile,
			String fileName, String filePath, String mainAutoId) {
		this.autoId = autoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
		this.fileName = fileName;
		this.filePath = filePath;
		this.mainAutoId = mainAutoId;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSysFile() {
		return this.sysFile;
	}

	public void setSysFile(String sysFile) {
		this.sysFile = sysFile;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
	}

}