package com.accolite.au.onboarding.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Demand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String hiringManager;
	private int numRequired;
	private String created_at;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Skill> dmdSkills = new ArrayList<>();

	public Demand() {}

	public Demand(String hiringManager, int numRequired, String created_at) {
		this.hiringManager = hiringManager;
		this.numRequired = numRequired;
		this.created_at = created_at;
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

	public int getNumRequired() {
		return numRequired;
	}

	public void setNumRequired(int numRequired) {
		this.numRequired = numRequired;
	}

	public List<Skill> getDmdSkills() {
		return dmdSkills;
	}

	public void setDmdSkills(List<Skill> dmdSkills) {
		this.dmdSkills = dmdSkills;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
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
		return "Demand [id=" + id + ", hiringManager=" + hiringManager + ", numRequired=" + numRequired
				+ ", created_at=" + created_at + ", dmdSkills=" + dmdSkills + "]";
	}
}
