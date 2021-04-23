package com.raju.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer eid;
	
	@Column(name="EMPLOYMENT_ID",nullable = false)
	private Integer employementId;
	
	@OneToOne(targetEntity = PersonalInfo.class,cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,orphanRemoval = true)
	@PrimaryKeyJoinColumn(name="EMPL_ID",referencedColumnName = "EID")
	private PersonalInfo personalInfo;
	
	@OneToMany(targetEntity = Education.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "employee",orphanRemoval = true)
	//@JoinColumn(name = "EMP_ID",referencedColumnName = "EID")
	private Set<Education> education;
	
	@Column(name="DEPT",length=20)
	private String dept;
	
	@Column(name="REPORTING_MNGR",length=20)
	private String reportingManager;
}
