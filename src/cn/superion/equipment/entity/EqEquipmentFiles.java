package cn.superion.equipment.entity;

/**
 * EqEquipmentFiles entity. @author MyEclipse Persistence Tools
 */

public class EqEquipmentFiles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6839031070228250753L;
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
	public EqEquipmentFiles() {
	}

	/** minimal constructor */
	public EqEquipmentFiles(String autoId, Short serialNo, String fileNo) {
		this.autoId = autoId;
		this.serialNo = serialNo;
		this.fileNo = fileNo;
	}

	/** full constructor */
	public EqEquipmentFiles(String autoId, Short serialNo, String fileNo,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result + ((fileNo == null) ? 0 : fileNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqEquipmentFiles other = (EqEquipmentFiles) obj;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (fileNo == null) {
			if (other.fileNo != null)
				return false;
		} else if (!fileNo.equals(other.fileNo))
			return false;
		return true;
	}

}