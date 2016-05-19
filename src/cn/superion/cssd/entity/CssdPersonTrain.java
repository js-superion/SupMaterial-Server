package cn.superion.cssd.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CssdPersonTrainFiles entity. @author MyEclipse Persistence Tools
 */

public class CssdPersonTrain implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8911160419280337736L;
	private String autoId;
	private String unitsCode;
	private String personId;
	private Date startTime;
	private Date endTime;
	private String trainContent;
	private String trainOrg;
	private String trainPlace;
	private String createPerson;
	private Date createDate;
	private String verifier;
	private Date verifyDate;
	private String currentStuats;
	private String isLoaded;
	private List<CssdPersonTrainFiles> files;
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getUnitsCode() {
		return unitsCode;
	}
	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTrainContent() {
		return trainContent;
	}
	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}
	public String getTrainOrg() {
		return trainOrg;
	}
	public void setTrainOrg(String trainOrg) {
		this.trainOrg = trainOrg;
	}
	public String getTrainPlace() {
		return trainPlace;
	}
	public void setTrainPlace(String trainPlace) {
		this.trainPlace = trainPlace;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getCurrentStuats() {
		return currentStuats;
	}
	public void setCurrentStuats(String currentStuats) {
		this.currentStuats = currentStuats;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setFiles(List<CssdPersonTrainFiles> files) {
		this.files = files;
	}
	public List<CssdPersonTrainFiles> getFiles() {
		if(files==null){
			return new ArrayList<CssdPersonTrainFiles>();
		}
		return files;
	}
	public void setIsLoaded(String isLoaded) {
		this.isLoaded = isLoaded;
	}
	public String getIsLoaded() {
		return isLoaded;
	}

}