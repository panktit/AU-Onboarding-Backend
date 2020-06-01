package com.accolite.au.onboarding.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Onboardee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String joiningCity;
//	private Date dob;
	private String mno;
	private String obStatus;
//	private Date joiningDate;
//	private Date obDate;
	private int eta;
	private String bgcComplete;
	private String graduationComplete;
	private String obFormalitiesComplete;
	private String created_at;
	private String last_modified;
//	List<Skill> skills;
	
	public Onboardee() {}

	public Onboardee(String name, String email, Date dob, String mno, String joiningCity, String obStatus, Date joiningDate, Date obDate,
			int eta, String bgcComplete, String graduationComplete, String obFormalitiesComplete, String created_at,
			String last_modified) {
		this.name = name;
		this.email = email;
	
		this.mno = mno;
		this.joiningCity = joiningCity;
		this.obStatus = obStatus;
		
		this.eta = eta;
		this.bgcComplete = bgcComplete;
		this.graduationComplete = graduationComplete;
		this.obFormalitiesComplete = obFormalitiesComplete;
		this.created_at = created_at;
		this.last_modified = last_modified;
//		this.skills = skills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {	
//		this.dob = dob;
//	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	
	public String getJoiningCity() {
		return joiningCity;
	}

	public void setJoiningCity(String joiningCity) {
		this.joiningCity = joiningCity;
	}

	public String getObStatus() {
		return obStatus;
	}

	public void setObStatus(String obStatus) {
		this.obStatus = obStatus;
	}
//
//	public Date getJoiningDate() {
//		return joiningDate;
//	}
//
//	public void setJoiningDate(Date joiningDate) {
//		this.joiningDate = joiningDate;
//	}

//	public Date getObDate() {
//		return obDate;
//	}
//
//	public void setObDate(Date obDate) {
//		this.obDate = obDate;
//	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String isBgcComplete() {
		return bgcComplete;
	}

	public void setBgcComplete(String bgcComplete) {
		this.bgcComplete = bgcComplete;
	}

	public String isGraduationComplete() {
		return graduationComplete;
	}

	public void setGraduationComplete(String graduationComplete) {
		this.graduationComplete = graduationComplete;
	}

	public String isObFormalitiesComplete() {
		return obFormalitiesComplete;
	}

	public void setObFormalitiesComplete(String obFormalitiesComplete) {
		this.obFormalitiesComplete = obFormalitiesComplete;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(String last_modified) {
		this.last_modified = last_modified;
	}

//	public List<Skill> getSkills() {
//		return skills;
//	}
//
//	public void setSkills(List<Skill> skills) {
//		this.skills = skills;
//	}

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
		Onboardee other = (Onboardee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Onboardee [id=" + id + ", name=" + name + ", email=" + email + ", joiningCity=" + joiningCity + ", mno="
				+ mno + ", obStatus=" + obStatus + ", eta=" + eta + ", bgcComplete=" + bgcComplete
				+ ", graduationComplete=" + graduationComplete + ", obFormalitiesComplete=" + obFormalitiesComplete
				+ ", created_at=" + created_at + ", last_modified=" + last_modified + "]";
	}
}
