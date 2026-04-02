package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Emp;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Integer> {
	
	public List<Emp> findByEmpSalBetween(double sal1,double sal2);

	
	@Query("From Emp e where Year(empDoj)=:jyear")
	public List<Emp> getEmpJoinInYear(@Param("jyear")int year);
	
	
	@Query("From Emp e   join fetch e.dept d where d.deptName=:dname")
	public List<Emp> getEmpByDeptName(@Param("dname")String dname);
}
