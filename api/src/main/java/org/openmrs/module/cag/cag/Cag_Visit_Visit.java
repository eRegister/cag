package org.openmrs.module.cag.cag;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Entity(name = "cag_visit_visit")
public class Cag_Visit_Visit {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_consultation_id")
	private Integer cag_consultation_id;
	
	@Column(name = "cag_visit_id")
	private Integer cag_visit_id;
	
	@Column(name = "visit_id")
	private Integer visit_id;
	
	public Integer getCag_consultation_id() {
		return cag_consultation_id;
	}
	
	public void setCag_consultation_id(Integer cag_consultation_id) {
		this.cag_consultation_id = cag_consultation_id;
	}
	
	public Integer getCag_visit_id() {
		return cag_visit_id;
	}
	
	public void setCag_visit_id(Integer cag_visit_id) {
		this.cag_visit_id = cag_visit_id;
	}
	
	public Integer getVisit_id() {
		return visit_id;
	}
	
	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
	
	public String getVisitUuid() {
		return visitUuid;
	}
	
	public void setVisitUuid(String visitUuid) {
		this.visitUuid = visitUuid;
	}
	
	@Transient
	private String visitUuid;
	
}
