package org.openmrs.module.cag.cag;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Visit;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	//	@Column(name = "patient_id")
	//	private Integer patient_id;
	
	@Transient
	private List<String> patientUuidList;
	
	//	@Transient
	@OneToMany
	@JoinTable(name = "cag_visit_visit", joinColumns = { @JoinColumn(name = "cag_visit_id") }, inverseJoinColumns = { @JoinColumn(name = "visit_id") })
	private List<Visit> visits = new ArrayList<Visit>();
	
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
	
	public List<Visit> getVisits() {
		return visits;
	}
	
	public List<String> getPatientUuidList() {
		return patientUuidList;
	}
	
	public void setPatientUuidList(List<String> patientUuidList) {
		this.patientUuidList = patientUuidList;
	}
	
	public void setVisits(List<Visit> visits) {
		this.visits = visits;
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
	
	@Override
	public String toString() {
		return "CagVisit{" + "id=" + id + ", cag_id=" + cag_id + ", cagUuid='" + cagUuid + '\'' + ", patientUuidList="
		        + patientUuidList + ", visits=" + visits + ", voided=" + this.getVoided() + ", uuid=" + this.getUuid()
		        + ", date_created=" + this.getDateCreated() + ", creator=" + this.getCreator() + '}';
	}
}
