package com.beans.common.security.accessrights.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.role.model.Role;


public interface AccessRightsRepository extends CrudRepository<AccessRights, Integer>{

	@Query("select ar from AccessRights ar where isDeleted = ?")
	 List<AccessRights> findByIsDeleted( int x);
	
	@Query("select ar from AccessRights ar where isDeleted = 0")
	 Set<AccessRights> findAllInSet();
}
