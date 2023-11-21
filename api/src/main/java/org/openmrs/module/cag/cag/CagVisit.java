package org.openmrs.module.cag.cag;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
@Entity(name = "cag_visit")
@JsonIgnoreProperties({ "creator", "changedBy" })
public class CagVisit extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_visit_id")
	private Integer id;
	
	@Column(name = "date_started")
	private Date dateStarted;
	
	@Column(name = "date_stopped")
	private Date dateStopped;
	
	//	@Column(name = "patient_id")
	//	private int patientId;
	
	@Column(name = "location")
	private String locationName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient attender;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cag_id")
	private Cag cag;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "cag_visit_visit", joinColumns = { @JoinColumn(name = "cag_visit_id") }, inverseJoinColumns = { @JoinColumn(name = "visit_id") })
	private Set<Visit> visits = new HashSet<Visit>();
	
	//	@Transient
	//	private Set<Visit> otherMemberVisits = new HashSet<Visit>();
	//
	//	@Transient
	//	private Visit attenderVisit;
	
	@Transient
	private Map<String, String> absentees = new HashMap<String, String>();
	
	//	@Transient
	//	private String display;
	//
	//	public int getPatientId() {
	//		return patientId;
	//	}
	//
	//	public void setPatientId(int patientId) {
	//		this.patientId = patientId;
	//	}
	
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
	
	public Set<Visit> getVisits() {
		return visits;
	}
	
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	
	//	public Set<Visit> getOtherMemberVisits() {
	//		return otherMemberVisits;
	//	}
	//
	//	public void setOtherMemberVisits(Set<Visit> otherMemberVisits) {
	//		this.otherMemberVisits = otherMemberVisits;
	//	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Map<String, String> getAbsentees() {
		return absentees;
	}
	
	public void setAbsentees(Map<String, String> absentees) {
		this.absentees = absentees;
	}
	
	//	public String getDisplay() {
	//		return display;
	//	}
	//
	//	public void setDisplay(String display) {
	//		this.display = display;
	//	}
	
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
	
	//	public Visit getAttenderVisit() {
	//		return attenderVisit;
	//	}
	//
	//	public void setAttenderVisit(Visit attenderVisit) {
	//		this.attenderVisit = attenderVisit;
	//	}
	
	@Override
	public String toString() {
		return "CagVisit{" + "id=" + id + ", cag=" + cag + ", cagUuid='" + '\'' + ", dateStarted=" + dateStarted
		        + ", dateStopped=" + dateStopped + ", attenderUuid='" + '\'' + ", locationName='" + locationName + '\''
		        + ", presentPatients=" + ", visitUuidList=" + ", visitList=" + ", absentees=" + absentees + ", display='"
		        + '\'' + '}';
	}
}
