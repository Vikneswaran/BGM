package com.beans.leaveapp.employeetype.service;

import java.util.List;

import com.beans.leaveapp.employeetype.model.EmployeeType;


public interface EmployeeTypeService {
	public EmployeeType create(EmployeeType employeeType);
	public EmployeeType delete(int id) throws EmployeeTypeNotFound;
	
	public List<EmployeeType> findAll();
	public EmployeeType update(EmployeeType employeeType) throws EmployeeTypeNotFound;
	public EmployeeType findById(int id) throws EmployeeTypeNotFound;
}
