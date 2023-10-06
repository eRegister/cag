package org.openmrs.module.cag.cag;

import javafx.util.Pair;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
@Entity(name = "cag_visit")
@JsonIgnoreProperties({ "creator", "changedBy", "patientUuidList" })
public class CagVisit extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_visit_id")
	private Integer id;
	
	@Column(name = "cag_id")
	private Integer cag_id;
	
	@Transient
	private String cagUuid;
	
	private Date date_started;
	
	private Date date_stopped;
	
	@Column(name = "patient_id")
	private int patient_id;
	
	@Transient
	private String attenderUuid;
	
	//	@Transient
	@Column(name = "location")
	private String locationName;
	
	@Transient
	private List<String> patientUuidList;
	
	@Transient
	private List<String> visitUuidList;
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "cag_visit_visit", joinColumns = { @JoinColumn(name = "cag_visit_id") }, inverseJoinColumns = { @JoinColumn(name = "visit_id") })
	private List<Visit> visitList = new ArrayList<Visit>();
	
	@Transient
	private Map<String, String> absentees = new HashMap<String, String>();
	
	@Transient
	private String display;
	
	@Transient
	private Map<Patient, String> missedPatients = new HashMap<Patient, String>();
	
	public List<String> getVisitUuidList() {
		return visitUuidList;
	}
	
	public void setVisitUuidList(List<String> visitUuidList) {
		this.visitUuidList = visitUuidList;
	}
	
	public String getAttenderUuid() {
		return attenderUuid;
	}
	
	public void setAttenderUuid(String attenderUuid) {
		this.attenderUuid = attenderUuid;
	}
	
	public int getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public Date getDate_started() {
		return date_started;
	}
	
	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}
	
	public Date getDate_stopped() {
		return date_stopped;
	}
	
	public void setDate_stopped(Date date_stopped) {
		this.date_stopped = date_stopped;
	}
	
	public List<Visit> getVisitList() {
		return visitList;
	}
	
	public List<String> getPatientUuidList() {
		return patientUuidList;
	}
	
	public void setPatientUuidList(List<String> patientUuidList) {
		this.patientUuidList = patientUuidList;
	}
	
	public void setVisitList(List<Visit> visitList) {
		this.visitList = visitList;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCag_id() {
		return cag_id;
	}
	
	public void setCag_id(Integer cag_id) {
		this.cag_id = cag_id;
	}
	
	public String getCagUuid() {
		return cagUuid;
	}
	
	public void setCagUuid(String cagUuid) {
		this.cagUuid = cagUuid;
	}
	
	public Map<String, String> getAbsentees() {
		return absentees;
	}
	
	public void setAbsentees(Map<String, String> absentees) {
		this.absentees = absentees;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}
	
	public Map<Patient, String> getMissedPatients() {
		return missedPatients;
	}
	
	public void setMissedPatients(Map<Patient, String> missedPatients) {
		this.missedPatients = missedPatients;
	}
	
	@Override
	public String toString() {
		return "CagVisit{" + "id=" + id + ", cag_id=" + cag_id + ", cagUuid='" + cagUuid + '\'' + ", date_started="
		        + date_started + ", date_stopped=" + date_stopped + ", patient_id=" + patient_id + ", attenderUuid='"
		        + attenderUuid + '\'' + ", locationName='" + locationName + '\'' + ", patientUuidList=" + patientUuidList
		        + ", visitUuidList=" + visitUuidList + ", visitList=" + visitList + ", absentees=" + absentees + '}';
	}
}
