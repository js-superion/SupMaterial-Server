package cn.superion.cssd.entity;

/**
 * CssdWorkRuleFiles entity. @author MyEclipse Persistence Tools
 */

public class CssdWorkRuleFiles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8078704848827256042L;
	private String autoId;
	private String mainAutoId;
	private String serialNo;
	private String sysFile;
	private String fileName;
	private String filePath;
	private String tempfileName;

	// Constructors

	/** default constructor */
	public CssdWorkRuleFiles() {
	}

	/** minimal constructor */
	public CssdWorkRuleFiles(String autoId, String mainAutoId, String serialNo,
			String sysFile) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
	}

	/** full constructor */
	public CssdWorkRuleFiles(String autoId, String mainAutoId, String serialNo,
			String sysFile, String fileName, String filePath) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
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

	public void setTempfileName(String tempfileName) {
		this.tempfileName = tempfileName;
	}

	public String getTempfileName() {
		return tempfileName;
	}

}