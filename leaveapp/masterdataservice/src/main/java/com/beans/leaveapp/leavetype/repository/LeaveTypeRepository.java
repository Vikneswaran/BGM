package com.beans.leaveapp.leavetype.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.beans.leaveapp.leavetype.model.LeaveType;

public interface LeaveTypeRepository extends CrudRepository<LeaveType, Integer>{
	
	
	 @Query("select l from LeaveType l where isDeleted = ?")
	 List<LeaveType> findByisDeleted(int x);
	
	 @Query("select l from LeaveType l where Name = ?")
	 LeaveType findByName(String name);
}
