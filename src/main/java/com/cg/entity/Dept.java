package com.cg.entity;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name="abes_dept")
public class Dept {
	
	@Id
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="dept_name",nullable=false,unique=true,length=25)
	private String deptName;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept")
	private Set<Emp> empSet;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Emp> getEmpSet() {
		return empSet;
	}

	public void setEmpSet(Set<Emp> empSet) {
		this.empSet = empSet;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ",]";
	}
	
	
	
	
	

}
