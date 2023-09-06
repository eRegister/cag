package org.openmrs.module.cag.cag;

import org.openmrs.Visit;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Entity(name = "cag_visit")
public class CagVisit {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_visit_id")
	private Integer cag_visit_id;
	
	@Column(name = "cag_id")
	private Integer cag_id;
	
	@Transient
	private String cagUuid;
	
	@Column(name = "patient_id")
	private Integer patient_id;
	
	@Transient
	private String patientUuid;
	
	private String uuid;
	
	@OneToMany
	private List<Visit> visits = new ArrayList<Visit>();
	
	public List<Visit> getVisits() {
		return visits;
	}
	
	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	
	public Integer getCag_visit_id() {
		return cag_visit_id;
	}
	
	public void setCag_visit_id(Integer cag_visit_id) {
		this.cag_visit_id = cag_visit_id;
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
	
	public Integer getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getPatientUuid() {
		return patientUuid;
	}
	
	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}
	
}
