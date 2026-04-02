package com.cg.service;

import java.util.List;

import com.cg.dto.EmpDto;
import com.cg.entity.Emp;

public interface EmpService {

	public Integer addEmployee(EmpDto dto);
	
	public Emp getEmployee(int eid);
	
	public List<Emp> getAllEmployee();
	
	public List<Emp> getEmpBySalRange(double sal1,double sal2);
	
	public List<Emp> getEmpJoinInYear(int year);
	
	public List<Emp> getEmpByDeptName(String dname);
	
	
}
