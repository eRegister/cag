package org.openmrs.module.cag.cag;

import org.openmrs.Encounter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Entity(name = "cag_encounter")
public class CagEncounter {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_encounter_id")
	private Integer cag_encounter_id;
	
	@Column(name = "encounter_id")
	private Integer encounter_id;
	
	@Transient
	private String encounterUuid;
	
	@Column(name = "cag_id")
	private Integer cag_id;
	
	@Transient
	private String cagUuid;
	
	@OneToMany
	private List<Encounter> encounters = new ArrayList<Encounter>();
	
	public Integer getCag_encounter_id() {
		return cag_encounter_id;
	}
	
	public void setCag_encounter_id(Integer cag_encounter_id) {
		this.cag_encounter_id = cag_encounter_id;
	}
	
	public Integer getEncounter_id() {
		return encounter_id;
	}
	
	public void setEncounter_id(Integer encounter_id) {
		this.encounter_id = encounter_id;
	}
	
	public String getEncounterUuid() {
		return encounterUuid;
	}
	
	public void setEncounterUuid(String encounterUuid) {
		this.encounterUuid = encounterUuid;
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
}
