package com.cg.test.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cg.entity.Dept;
import com.cg.entity.Emp;
import com.cg.exceptions.NotAvaliableException;
import com.cg.repo.EmpRepo;
import com.cg.service.EmpService;

@SpringBootTest
public class TestViewEmpById {
	
	@MockitoBean
	private EmpRepo erepo;
	
	@Autowired
	private EmpService service;
	
	private Optional<Emp> optEmp1,optEmp2;
	
	@BeforeEach
	public void beforeEach() {
		Dept dept=new Dept();
		dept.setDeptId(1);
		dept.setDeptName("hr");
		optEmp1=Optional.ofNullable(new Emp(1001,"ram",56000.0,LocalDate.now(),dept));
		optEmp2=Optional.empty();
	}
	
	@Test
	public void testViewById1(){
		Mockito.when(erepo.findById(1001)).thenReturn(optEmp1);
		
		Assertions.assertNotNull(service.getEmployee(1001));
		
	Mockito.verify(erepo).findById(1001);
		
	}
	
	public void testViewById2(){
		Mockito.when(erepo.findById(1002)).thenReturn(optEmp2);
		
		Assertions.assertThrows(NotAvaliableException.class, ()->service.getEmployee(1002));
		
		Mockito.verify(erepo).findById(1002);
		
	}
	

}
