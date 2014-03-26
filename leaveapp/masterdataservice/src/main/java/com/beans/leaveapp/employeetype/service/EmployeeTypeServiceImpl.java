package com.beans.leaveapp.employeetype.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.employeetype.model.EmployeeType;
import com.beans.leaveapp.employeetype.repository.EmployeeTypeRepository;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {
	
	@Resource
	EmployeeTypeRepository employeeTypeRepository;
	
	
	@Override
	@Transactional
	public EmployeeType create(EmployeeType employeeType) {
		EmployeeType employeeTypeToBeCreated = employeeType;
		return employeeTypeRepository.save(employeeTypeToBeCreated);
	}

	@Override
	@Transactional(rollbackFor=EmployeeTypeNotFound.class)
	public EmployeeType delete(int id) throws EmployeeTypeNotFound {
		EmployeeType employeeType = employeeTypeRepository.findOne(id);
		
		if(employeeType == null)
			throw new EmployeeTypeNotFound();
		employeeType.setDeleted(false);
		employeeTypeRepository.save(employeeType);
		return employeeType;
	}

	@Override
	public List<EmployeeType> findAll() {
		List<EmployeeType> resultList = employeeTypeRepository.findByisDeleted(0);
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=EmployeeTypeNotFound.class)
	public EmployeeType update(EmployeeType employeeType)
			throws EmployeeTypeNotFound {
		EmployeeType employeeTypeToBeUpdated = employeeTypeRepository.findOne(employeeType.getId());
		
		if(employeeTypeToBeUpdated == null)
			throw new EmployeeTypeNotFound();
		
		employeeTypeToBeUpdated.setName(employeeType.getName());
		employeeTypeToBeUpdated.setDescription(employeeType.getDescription());
		employeeTypeToBeUpdated.setDeleted(employeeType.isDeleted());
		employeeTypeRepository.save(employeeTypeToBeUpdated);
		return employeeTypeToBeUpdated;
	}

	@Override
	public EmployeeType findById(int id) throws EmployeeTypeNotFound {
		EmployeeType employeeType = employeeTypeRepository.findOne(id);
		
		if(employeeType == null)
			throw new EmployeeTypeNotFound();
		
		return employeeType;
	}

}
