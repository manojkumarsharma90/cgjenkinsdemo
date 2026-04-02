package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.EmpDto;
import com.cg.dto.SuccessMessageDto;
import com.cg.entity.Emp;
import com.cg.exceptions.CgValidationException;
import com.cg.service.EmpService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	
	
	@GetMapping("/viewall")
	@Operation(summary="view all employee " ,tags="emp mgmt")
	public List<Emp> viewAllEmp(){
		
		return empService.getAllEmployee();
		
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description="add an employee,dont send emp id",tags="emp mgmt" )
	
	public SuccessMessageDto addEmployee(@Valid @RequestBody EmpDto dto,BindingResult br) {
		
		if(br.hasErrors()) {
		 throw new CgValidationException(br.getFieldErrors());
		}
		
		int eid=empService.addEmployee(dto);
		
		return new SuccessMessageDto("Employee Added Succesfully with id ",eid);
	}
	
	
	
	

	

	
	
	
	
	@GetMapping("/view/{id}")
	@Operation(description="view an employee by pasing an emp id",tags="emp mgmt")
	public Emp viewEmpById(@PathVariable() Integer id){
		
		return empService.getEmployee(id);
		
	}
	
	

}
