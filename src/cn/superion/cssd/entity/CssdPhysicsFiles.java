package cn.superion.cssd.entity;

/**
 * CssdPhysicsFiles entity. @author MyEclipse Persistence Tools
 */

public class CssdPhysicsFiles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -710096199460913393L;
	private String autoId;
	private String mainAutoId;
	private String serialNo;
	private String sysFile;
	private String fileName;
	private String filePath;
	private String column7;

	// Constructors

	/** default constructor */
	public CssdPhysicsFiles() {
	}

	/** minimal constructor */
	public CssdPhysicsFiles(String autoId, String mainAutoId, String serialNo,
			String sysFile) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
	}

	/** full constructor */
	public CssdPhysicsFiles(String autoId, String mainAutoId, String serialNo,
			String sysFile, String fileName, String filePath, String column7) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
		this.fileName = fileName;
		this.filePath = filePath;
		this.column7 = column7;
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

	public String getColumn7() {
		return this.column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

}