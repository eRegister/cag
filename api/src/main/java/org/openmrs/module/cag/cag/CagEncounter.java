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
	
	@Column(name = "cag_id")
	private Integer cagId;
	
	@Column(name = "cag_visit_id")
	private Integer cagVisitId;
	
	@Column(name = "next_encounter_date")
	private Date nextEncounterDate;
	
	@Column(name = "location_id")
	private Integer locationId;
	
	@OneToMany
	private List<Encounter> encounters = new ArrayList<Encounter>();
	
	@Transient
	private Encounter encounter;
	
	@Transient
	private String cagUuid;
	
	@Transient
	private String cagVisitUuid;
	
	@Transient
	private String encounterUuid;
	
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
	
	public List<Encounter> getEncounters() {
		return encounters;
	}
	
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	
	public String getEncounterUuid() {
		return encounterUuid;
	}
	
	public void setEncounterUuid(String encounterUuid) {
		this.encounterUuid = encounterUuid;
	}
	
	public String getCagUuid() {
		return cagUuid;
	}
	
	public void setCagUuid(String cagUuid) {
		this.cagUuid = cagUuid;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public String getCagVisitUuid() {
		return cagVisitUuid;
	}
	
	public void setCagVisitUuid(String cagVisitUuid) {
		this.cagVisitUuid = cagVisitUuid;
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
	
	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}
}
