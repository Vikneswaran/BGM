package com.beans.leaveapp.employeetype.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.leaveapp.employeetype.model.EmployeeType;

public interface EmployeeTypeRepository extends CrudRepository<EmployeeType, Integer>{
	
	@Query("select e from EmployeeType e where isDeleted = ?")
	List<EmployeeType> findByisDeleted(int x);
}
