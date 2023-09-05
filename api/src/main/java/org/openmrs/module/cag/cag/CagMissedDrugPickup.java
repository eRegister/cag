package org.openmrs.module.cag.cag;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Entity(name = "cag_missed_drug_pickup")
public class CagMissedDrugPickup {
	
	@Id
	@GeneratedValue
	@Column(name = "missed_drug_pickup_id")
	private Integer id;
	
	@Column(name = "cag_visit_id")
	private Integer cag_visit_id;
	
	@Column(name = "patient_id")
	private Integer patient_id;
	
	@Transient
	private String patientUuid;
	
	private String reason_missed;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCag_visit_id() {
		return cag_visit_id;
	}
	
	public void setCag_visit_id(Integer cag_visit_id) {
		this.cag_visit_id = cag_visit_id;
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
	
	public String getReason_missed() {
		return reason_missed;
	}
	
	public void setReason_missed(String reason_missed) {
		this.reason_missed = reason_missed;
	}
}
