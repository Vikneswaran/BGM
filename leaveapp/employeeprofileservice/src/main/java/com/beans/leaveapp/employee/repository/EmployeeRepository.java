package com.beans.leaveapp.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.beans.leaveapp.employee.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	@Query("select e from Employee e where isDeleted = ?")
	List<Employee> findByisDeleted(int isDeleted);
	
	@Query("select e from Employee e where name like ? and isDeleted = 0")
	List<Employee> findByNameLike(String name);
	
	@Query("select e from Employee e where employeeNumber like ? and isDeleted = 0")
	List<Employee> findByEmployeeNumberLike(String employeeNumber);
	
	@Query("select e from Employee e where name like ? and employeeNumber like ? and isDeleted=0")
	List<Employee> findByNameAndEmployeeNumberLike(String name, String employeeNumber);
	
	@Query("select e from Employee e where isResigned = ? and isDeleted = ?")
	List<Employee> findByIsResignedAndIsDeleted(int isResigned, int isDeleted);
	
	@Query("select e from Employee e left join e.users u where u.username = :username and isDeleted = 0")
	Employee findByUsername(@Param("username") String username);
}
