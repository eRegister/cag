/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cag.cag;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.User;
import org.simpleframework.xml.Root;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
@Repository
@Entity(name = "cag_patient")
@Table(name = "cag_patient")
public class CagPatient {
	
	@Id
	@GeneratedValue
	@Column(name = "cag_patient_id")
	private Integer cagPatientId;
	
	@Column(name = "cag_id")
	private Integer cag_id;
	
	@Column(name = "patient_id")
	private Integer patient_id;
	
	@Basic
	@Column(name = "status")
	private boolean status;
	
	public void setCagPatientId(Integer cagPatientId) {
		this.cagPatientId = cagPatientId;
	}
	
	public Integer getCagPatientId() {
		return cagPatientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patient_id = patientId;
	}
	
	public Integer getPatientId() {
		return patient_id;
	}
	
	public void setCagId(Integer cagId) {
		this.cag_id = cagId;
	}
	
	public Integer getCagId() {
		return cag_id;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "CagPatient{" + "cagPatientId=" + cagPatientId + ", cag_id=" + cag_id + ", patient_id=" + patient_id
		        + ", status=" + status + '}';
	}
}
