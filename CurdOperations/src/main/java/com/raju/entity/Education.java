package com.raju.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "EDCATION_INFO")
public class Education {
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "EDC_SEQ",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Type(type = "string")
	@Column(name = "INSTITUTION", length = 30,nullable = false)
	private String institution;
	
	@Type(type = "string")
	@Column(name = "TYPE", length = 15,nullable = false)
	private String type;
	
	@Column(name = "START_DATE", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private LocalDate endDate;
	
	@Type(type = "string")
	@Column(name = "ADDRESS", nullable = false, length = 40)
	private String address;
	
	@ManyToOne(targetEntity = Employee.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="EMP_ID",referencedColumnName = "eid")
	private Employee employee;
}
