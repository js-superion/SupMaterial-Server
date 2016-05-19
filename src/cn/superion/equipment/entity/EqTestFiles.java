package cn.superion.equipment.entity;

/**
 * EqTestFiles entity. @author MyEclipse Persistence Tools
 */

public class EqTestFiles implements java.io.Serializable {

	// Fields

	private String autoId;
	private Short serialNo;
	private String fileNo;
	private String fileName;
	private String fileRemark;
	private String filePath;
	//扩展
	private byte[] data;
	//是否要修改
	private boolean toUpdated;

	// Constructors

	/** default constructor */
	public EqTestFiles() {
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isToUpdated() {
		return toUpdated;
	}

	public void setToUpdated(boolean toUpdated) {
		this.toUpdated = toUpdated;
	}

	/** minimal constructor */
	public EqTestFiles(String autoId, Short serialNo, String fileNo) {
		this.autoId = autoId;
		this.serialNo = serialNo;
		this.fileNo = fileNo;
	}

	/** full constructor */
	public EqTestFiles(String autoId, Short serialNo, String fileNo,
			String fileName, String fileRemark, String filePath) {
		this.autoId = autoId;
		this.serialNo = serialNo;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.fileRemark = fileRemark;
		this.filePath = filePath;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRemark() {
		return this.fileRemark;
	}

	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}