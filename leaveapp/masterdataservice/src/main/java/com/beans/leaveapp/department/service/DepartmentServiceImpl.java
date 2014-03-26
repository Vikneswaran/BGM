package com.beans.leaveapp.department.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.department.model.Department;
import com.beans.leaveapp.department.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	DepartmentRepository departmentRepository;
	
	
	@Override
	@Transactional
	public Department create(Department department) {
		Department departmentToBeCreated = department;
		
		return departmentRepository.save(departmentToBeCreated);
	}

	@Override
	@Transactional(rollbackFor=DepartmentNotFound.class)
	public Department delete(int id) throws DepartmentNotFound {
		Department department = departmentRepository.findOne(id);
		
		if(department == null)
			throw new DepartmentNotFound();
		
		department.setDeleted(true);
		
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> findAll() {
		List<Department> resultList = departmentRepository.findByisDeleted(0);
		
		
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=DepartmentNotFound.class)
	public Department update(Department department) throws DepartmentNotFound {
		Department departmentToBeUpDepartment = departmentRepository.findOne(department.getId());
		
		if(departmentToBeUpDepartment == null)
			throw new DepartmentNotFound();
		
		departmentToBeUpDepartment.setName(department.getName());
		departmentToBeUpDepartment.setDescription(department.getDescription());
		departmentToBeUpDepartment.setDeleted(department.isDeleted());
		return departmentRepository.save(departmentToBeUpDepartment);
	}

	@Override
	public Department findById(int id) throws DepartmentNotFound {
		Department department = departmentRepository.findOne(id);
		
		if(department == null)
			throw new DepartmentNotFound();
		
		return department;
	}

}
