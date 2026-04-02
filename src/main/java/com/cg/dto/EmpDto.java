package com.cg.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmpDto {
	
	
	private Integer empId;
	
	@NotBlank(message="emp name is required" )
	@Size(min=3,max=25,message="Emp name must between 3 and 25 characters")
	@Pattern(regexp="([a-zA-Z]+)|([a-zA-Z]+A-Z]+)",message="Employee name only contains alphabets and space")
	private String empName;
	
	@NotNull(message="sal is required")
	@DecimalMin(value="5000.0",message="emp sal must be minium rs 5000.0")	
	private Double empSal;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="date of joining required")
	@Past
	private LocalDate empDoj;
	
	@Min(value=1,message="dept id must be minimum 1")
	@Max(value=50,message="dept id max should be 50")
	@NotNull(message="dept is required")
	private Integer deptId;
	
	
	
	public EmpDto() {
		super();
	}
	
	
	public EmpDto(Integer empId,String empName,Double empSal,LocalDate empDoj,Integer deptId) {
		
		this.empId = empId;
		this.empName = empName;
		this.empSal = empSal;
		this.empDoj = empDoj;
		this.deptId = deptId;
	}
	
	


	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Double getEmpSal() {
		return empSal;
	}
	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}
	public LocalDate getEmpDoj() {
		return empDoj;
	}
	public void setEmpDoj(LocalDate empDoj) {
		this.empDoj = empDoj;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	
	

}
