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
	private Integer cagId;
	
	@Transient
	private Cag cag;
	
	@Column(name = "date_started")
	private Date dateStarted;
	
	@Column(name = "date_stopped")
	private Date dateStopped;
	
	@Column(name = "patient_id")
	private int patientId;
	
	@Transient
	private Patient attender;
	
	//	@Transient
	@Column(name = "location")
	private String locationName;
	
	@Transient
	private List<String> patientUuidList;
	
	@Transient
	private List<Patient> presentPatients = new ArrayList<Patient>();
	
	@Transient
	private List<String> visitUuidList;
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "cag_visit_visit", joinColumns = { @JoinColumn(name = "cag_visit_id") }, inverseJoinColumns = { @JoinColumn(name = "visit_id") })
	private List<Visit> visitList = new ArrayList<Visit>();
	
	@Transient
	private Visit attenderVisit;
	
	@Transient
	private Map<String, String> absentees = new HashMap<String, String>();
	
	@Transient
	private String display;
	
	public List<String> getVisitUuidList() {
		return visitUuidList;
	}
	
	public void setVisitUuidList(List<String> visitUuidList) {
		this.visitUuidList = visitUuidList;
	}
	
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public Date getDateStarted() {
		return dateStarted;
	}
	
	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}
	
	public Date getDateStopped() {
		return dateStopped;
	}
	
	public void setDateStopped(Date dateStopped) {
		this.dateStopped = dateStopped;
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
	
	public Integer getCagId() {
		return cagId;
	}
	
	public void setCagId(Integer cagId) {
		this.cagId = cagId;
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
	
	public List<Patient> getPresentPatients() {
		return presentPatients;
	}
	
	public void setPresentPatients(List<Patient> presentPatients) {
		this.presentPatients = presentPatients;
	}
	
	public Cag getCag() {
		return cag;
	}
	
	public void setCag(Cag cag) {
		this.cag = cag;
	}
	
	public Patient getAttender() {
		return attender;
	}
	
	public void setAttender(Patient attender) {
		this.attender = attender;
	}
	
	public Visit getAttenderVisit() {
		return attenderVisit;
	}
	
	public void setAttenderVisit(Visit attenderVisit) {
		this.attenderVisit = attenderVisit;
	}
	
	@Override
	public String toString() {
		return "CagVisit{" + "id=" + id + ", cagId=" + cagId + ", cagUuid='" + '\'' + ", dateStarted=" + dateStarted
		        + ", dateStopped=" + dateStopped + ", patientId=" + patientId + ", attenderUuid='" + '\''
		        + ", locationName='" + locationName + '\'' + ", patientUuidList=" + patientUuidList + ", presentPatients="
		        + presentPatients + ", visitUuidList=" + visitUuidList + ", visitList=" + visitList + ", absentees="
		        + absentees + ", display='" + display + '\'' + '}';
	}
}
