package com.accolite.au.onboarding.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Demand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String hiringManager;
	private String role;
	private String department;
	private String team;
	private String created_at;
	
	@OneToOne(cascade =  CascadeType.ALL, mappedBy = "mappedDemand")
	@JsonIgnore
	private Onboardee ob;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Skill> dmdSkills = new ArrayList<>();

	public Demand() {}

	public Demand(String hiringManager, String role, String department, String team, String created_at, Onboardee ob) {
		this.hiringManager = hiringManager;
		this.role = role;
		this.department = department;
		this.team = team;
		this.created_at = created_at;
		this.ob = ob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHiringManager() {
		return hiringManager;
	}

	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<Skill> getDmdSkills() {
		return dmdSkills;
	}

	public void setDmdSkills(List<Skill> dmdSkills) {
		this.dmdSkills = dmdSkills;
	}

	public Onboardee getOb() {
		return ob;
	}

	public void setOb(Onboardee ob) {
		this.ob = ob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demand other = (Demand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demand [id=" + id + ", hiringManager=" + hiringManager + ", role=" + role + ", department=" + department
				+ ", team=" + team + ", created_at=" + created_at + ", ob=" + ob + ", dmdSkills=" + dmdSkills + "]";
	}
}
