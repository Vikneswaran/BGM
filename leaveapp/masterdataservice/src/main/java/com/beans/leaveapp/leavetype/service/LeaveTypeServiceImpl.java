package com.beans.leaveapp.leavetype.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Resource
	private LeaveTypeRepository leaveTypeRepository;
	
	@Override
	@Transactional
	public LeaveType create(LeaveType leaveType) {
		LeaveType leaveTypeToBeCreated = leaveType;
		
		return leaveTypeRepository.save(leaveTypeToBeCreated);
	}

	@Override
	@Transactional(rollbackFor=LeaveTypeNotFound.class)
	public LeaveType delete(int id) throws LeaveTypeNotFound {
		LeaveType leaveType = leaveTypeRepository.findOne(id);
		
		if(leaveType == null)
			throw new LeaveTypeNotFound();
		leaveType.setDeleted(true);
		leaveTypeRepository.save(leaveType);
		return leaveType;
	}

	@Override
	public List<LeaveType> findAll() {
		List<LeaveType> resultList = leaveTypeRepository.findByisDeleted(0);
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=LeaveTypeNotFound.class)
	public LeaveType update(LeaveType leaveType) throws LeaveTypeNotFound {
		LeaveType leaveTypeToBeUpdated = leaveTypeRepository.findOne(leaveType.getId());
		
		if(leaveTypeToBeUpdated == null)
			throw new LeaveTypeNotFound();
		
		leaveTypeToBeUpdated.setName(leaveType.getName());
		leaveTypeToBeUpdated.setDescription(leaveType.getDescription());
		leaveTypeToBeUpdated.setDeleted(leaveType.isDeleted());
		leaveTypeRepository.save(leaveTypeToBeUpdated);
		return leaveTypeToBeUpdated;
	}

	@Override
	public LeaveType findById(int id) throws LeaveTypeNotFound{
		LeaveType leaveType = leaveTypeRepository.findOne(id);
		
		if(leaveType == null)
			throw new LeaveTypeNotFound();
		
		return leaveType;
	}
	

	
}
