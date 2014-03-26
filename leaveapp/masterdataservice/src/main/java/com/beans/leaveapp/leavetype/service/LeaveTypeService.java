package com.beans.leaveapp.leavetype.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.beans.leaveapp.leavetype.model.LeaveType;

public interface LeaveTypeService {
	public LeaveType create(LeaveType leaveType);
	public LeaveType delete(int id) throws LeaveTypeNotFound;
	public List<LeaveType> findAll();
	public LeaveType update(LeaveType leaveType) throws LeaveTypeNotFound;
	public LeaveType findById(int id) throws LeaveTypeNotFound;
}



