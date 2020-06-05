package com.accolite.au.onboarding.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class Onboardee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String joiningCity;
	private String dob;
	private String mno;
	private String obStatus;
	private String joiningDate;
	private String obDate;
	private int eta;
	private String bgc;
	private String graduation;
	private String obFormalities;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "demand_id")
	@JsonIgnoreProperties("ob")
	private Demand mappedDemand;
	
	private String created_at;
	private String last_modified;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_id")
	private Address joiningAddress;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="obSkills", 
				joinColumns = @JoinColumn(name="ob_id"),
				inverseJoinColumns = @JoinColumn(name="skill_id"))
	private List<Skill> obSkills = new ArrayList<>();

	public Onboardee() {
	}
	
	public Onboardee(Long id, String name, String obStatus) {
		this.id=id;
		this.name = name;
		this.obStatus = obStatus;
	}	

	public Onboardee(String name, String email, String joiningCity, String dob, String mno, String obStatus,
			String joiningDate, String obDate, int eta, String bgc, String graduation, String obFormalities,
			String created_at, String last_modified) {
		this.name = name;
		this.email = email;
		this.joiningCity = joiningCity;
		this.dob = dob;
		this.mno = mno;
		this.obStatus = obStatus;
		this.joiningDate = joiningDate;
		this.obDate = obDate;
		this.eta = eta;
		this.bgc = bgc;
		this.graduation = graduation;
		this.obFormalities = obFormalities;
		this.created_at = created_at;
		this.last_modified = last_modified;
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

	public String getJoiningCity() {
		return joiningCity;
	}

	public void setJoiningCity(String joiningCity) {
		this.joiningCity = joiningCity;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getObStatus() {
		return obStatus;
	}

	public void setObStatus(String obStatus) {
		this.obStatus = obStatus;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getObDate() {
		return obDate;
	}

	public void setObDate(String obDate) {
		this.obDate = obDate;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getBgc() {
		return bgc;
	}

	public void setBgc(String bgc) {
		this.bgc = bgc;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getObFormalities() {
		return obFormalities;
	}

	public void setObFormalities(String obFormalities) {
		this.obFormalities = obFormalities;
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
	
	

	public Address getJoiningAddress() {
		return joiningAddress;
	}

	public void setJoiningAddress(Address joiningAddress) {
		this.joiningAddress = joiningAddress;
	}

	public List<Skill> getObSkills() {
		return obSkills;
	}

	public void setObSkills(List<Skill> obSkills) {
		this.obSkills = obSkills;
	}

	public Demand getMappedDemand() {
		return mappedDemand;
	}

	public void setMappedDemand(Demand mappedDemand) {
		this.mappedDemand = mappedDemand;
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
		return "Onboardee [id=" + id + ", name=" + name + ", email=" + email + ", joiningCity=" + joiningCity + ", dob="
				+ dob + ", mno=" + mno + ", obStatus=" + obStatus + ", joiningDate=" + joiningDate + ", obDate="
				+ obDate + ", eta=" + eta + ", bgc=" + bgc + ", graduation=" + graduation + ", obFormalities="
				+ obFormalities + ", mappedDemand=" + mappedDemand + ", created_at=" + created_at + ", last_modified="
				+ last_modified + ", joiningAddress=" + joiningAddress + ", obSkills=" + obSkills + "]";
	}
}
