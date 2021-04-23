package com.raju.entity;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSONAL_INFO")
public class PersonalInfo {
	@Id
	@GenericGenerator(name = "gen1",strategy = "foreign",parameters = @Parameter(name="property",value = "emp"))
	@GeneratedValue(generator = "gen1")
	private Integer id;
	
	@Type(type = "string")
	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	private String firstName;
	
	@Type(type = "string")
	@Column(name = "LAST_NAME", length = 20, nullable = false)
	private String lastName;
	
	@Type(type = "string")
	@Column(name = "GENDER", length = 10, nullable = false)
	private String gender;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Type(type = "string")
	@Column(name = "STATUS", length = 10, nullable = false)
	private String maritalStatus;
	
	@OneToOne(targetEntity = Employee.class,cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "personalInfo")
	private Employee emp;
}
