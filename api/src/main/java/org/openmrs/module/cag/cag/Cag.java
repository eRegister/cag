package org.openmrs.module.cag.cag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsData;

@Entity(name = "cag.cag")
@Table(name = "cag_cag")
public class Cag extends BaseOpenmrsData {
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "cag_id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "village")
	private String village;
	
	@Column(name = "constituency")
	private String constituency;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getVillage() {
		return village;
	}
	
	public void setVillage(String village) {
		this.village = village;
	}
	
	public String getConstituency() {
		return constituency;
	}
	
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
}
