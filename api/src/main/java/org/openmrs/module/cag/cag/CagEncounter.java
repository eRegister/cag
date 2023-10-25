package org.openmrs.module.cag.cag;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.DrugOrder;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.annotation.AllowDirectAccess;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
@Entity(name = "cag_encounter")
public class CagEncounter extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_encounter_id")
	private Integer id;
	
	@Column(name = "encounter_id")
	private Integer encounterId;
	
	@Transient
	private Encounter encounter;
	
	@Column(name = "cag_id")
	private Integer cagId;
	
	@Transient
	private Cag cag;
	
	@Column(name = "cag_visit_id")
	private Integer cagVisitId;
	
	@Transient
	private CagVisit cagVisit;
	
	@Column(name = "next_encounter_date")
	private Date nextEncounterDate;
	
	@Column(name = "location_id")
	private Integer locationId;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "cag_encounter_encounter", joinColumns = { @JoinColumn(name = "cag_encounter_id") }, inverseJoinColumns = { @JoinColumn(name = "encounter_id") })
	private Set<Encounter> encounters = new HashSet<Encounter>();
	
	@Transient
	public String displayed;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getEncounterId() {
		return encounterId;
	}
	
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	public Integer getCagId() {
		return cagId;
	}
	
	public void setCagId(Integer cagId) {
		this.cagId = cagId;
	}
	
	public Integer getCagVisitId() {
		return cagVisitId;
	}
	
	public void setCagVisitId(Integer cagVisitId) {
		this.cagVisitId = cagVisitId;
	}
	
	public Date getNextEncounterDate() {
		return nextEncounterDate;
	}
	
	public void setNextEncounterDate(Date nextEncounterDate) {
		this.nextEncounterDate = nextEncounterDate;
	}
	
	public Set<Encounter> getEncounters() {
		return encounters;
	}
	
	public void setEncounters(Set<Encounter> encounters) {
		this.encounters = encounters;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public Encounter getEncounter() {
		return encounter;
	}
	
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	public String getDisplayed() {
		return displayed;
	}
	
	public Cag getCag() {
		return cag;
	}
	
	public void setCag(Cag cag) {
		this.cag = cag;
	}
	
	public CagVisit getCagVisit() {
		return cagVisit;
	}
	
	public void setCagVisit(CagVisit cagVisit) {
		this.cagVisit = cagVisit;
	}
	
	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}
	
}
