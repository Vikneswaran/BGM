package com.beans.common.security.accessrights.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.accessrights.repository.AccessRightsRepository;

@Service
public class AccessRightsServiceImpl implements AccessRightsService{

	
	
	@Resource
	private AccessRightsRepository accessRightsRepository;
	
	
	@Override
	@Transactional
	public AccessRights create(AccessRights accessRights) {
		AccessRights accessRightsToBeCreated = accessRights;
		return accessRightsRepository.save(accessRightsToBeCreated);
	}

	@Override
	@Transactional
	public AccessRights delete(int id) throws AccessRightsNotFound {
          AccessRights accessRights = accessRightsRepository.findOne(id);
          
          if(accessRights == null)
        	   throw new AccessRightsNotFound();
          accessRights.setDeleted(true);
          accessRightsRepository.save(accessRights);		
		return accessRights;
	}

	@Override	
	public List<AccessRights> findAll() {
		List<AccessRights> accessRightsList = accessRightsRepository.findByIsDeleted(0);
		return accessRightsList;
	}

	@Override
	@Transactional
	public AccessRights update(AccessRights accessRights) throws AccessRightsNotFound {
		AccessRights accessRightsToBeUpdated = accessRightsRepository.findOne(accessRights.getId());
		
		if(accessRightsToBeUpdated == null)
			 throw new AccessRightsNotFound();
	//	accessRightsToBeUpdated.setId(accessRights.getId());
		accessRightsToBeUpdated.setAccessRights(accessRights.getAccessRights());
		accessRightsToBeUpdated.setDescription(accessRights.getDescription());
		accessRightsRepository.save(accessRightsToBeUpdated);		   
		return accessRightsToBeUpdated;
	}

	@Override
	@Transactional
	public AccessRights findById(int id) throws AccessRightsNotFound {
            AccessRights accessRights = accessRightsRepository.findOne(id);
            
            if(accessRights == null) 
            	throw new AccessRightsNotFound();
            
		return accessRights;
	}

	@Override
	public Set<AccessRights> findAllInSet() {
			Set<AccessRights> resultSet = accessRightsRepository.findAllInSet();
		return resultSet;
	}

}
