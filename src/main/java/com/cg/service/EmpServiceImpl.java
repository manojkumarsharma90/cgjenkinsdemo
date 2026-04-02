package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.dto.EmpDto;
import com.cg.entity.Dept;
import com.cg.entity.Emp;
import com.cg.exceptions.NotAvaliableException;
import com.cg.repo.DeptRepo;
import com.cg.repo.EmpRepo;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private DeptRepo deptRepo;

	@Override
	public Emp getEmployee(int eid) {

		Optional<Emp> opEmp = empRepo.findById(eid);

		if (opEmp.isPresent()) {
			return opEmp.get();
		}

		throw new NotAvaliableException("emp not found "+eid);
	}

	@Override
	public List<Emp> getAllEmployee() {
     
		return empRepo.findAll(Sort.by("empDoj").descending());
		
	}

	@Override
	public Integer addEmployee(EmpDto dto) {
		
		Optional<Dept> optDept=deptRepo.findById(dto.getDeptId());
		
		if(optDept.isEmpty()) {
			throw new NotAvaliableException("dept not found for "+dto.getDeptId());
		}
		
		
		Dept dept=optDept.get();
		Emp emp=new Emp();
		
		emp.setEmpName(dto.getEmpName());
		emp.setEmpSal(dto.getEmpSal());
		emp.setEmpDoj(dto.getEmpDoj());
		emp.setDept(dept); // foreign key 
		
		Emp savedEmp=empRepo.save(emp);
		return savedEmp.getEmpID();
		
		
		}

	@Override
	public List<Emp> getEmpBySalRange(double sal1, double sal2) {
		
		return empRepo.findByEmpSalBetween(sal1, sal2) ;
	}

	@Override
	public List<Emp> getEmpJoinInYear(int year) {
		
		return empRepo.getEmpJoinInYear(year);
	}

	@Override
	public List<Emp> getEmpByDeptName(String dname) {
		
		return empRepo.getEmpByDeptName(dname);
	}

	
	


}
