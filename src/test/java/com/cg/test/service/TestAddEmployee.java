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

import com.cg.dto.EmpDto;
import com.cg.entity.Dept;
import com.cg.entity.Emp;
import com.cg.exceptions.NotAvaliableException;
import com.cg.repo.DeptRepo;
import com.cg.repo.EmpRepo;
import com.cg.service.EmpService;

@SpringBootTest
public class TestAddEmployee {

	
	@MockitoBean
	private EmpRepo erepo;
	
	@MockitoBean
	private DeptRepo drepo;
	
	@Autowired
	private EmpService service;
	
	private EmpDto dto1,dto2;
	
	@BeforeEach
	public void beforeEach() {
		dto1=new EmpDto(1,"ram",4500.0,LocalDate.now(),1);
		dto2=new EmpDto(2,"ram",4500.0,LocalDate.now(),2);
	}
	
	@Test
	public void testAdd1() {
		Mockito.when(drepo.findById(1)).thenReturn(Optional.of(new Dept()));
		
		Mockito.when(erepo.save(Mockito.any(Emp.class))).thenReturn(new Emp(1,"ram",4500.0,LocalDate.now(),new Dept()));
		
		Assertions.assertEquals(1,service.addEmployee(dto1) );
		
		Mockito.verify(drepo).findById(1);
		
		Mockito.verify(erepo).save(Mockito.any(Emp.class));
		
		
		
		
	}
	
	@Test
	public void testAdd2() {
		Mockito.when(drepo.findById(2)).thenReturn(Optional.empty());
		
	
		
		Assertions.assertThrows(NotAvaliableException.class,()-> service.addEmployee(dto2) );
		
		Mockito.verify(drepo).findById(2);
		
		
		
		
		
		
	}
	
}
