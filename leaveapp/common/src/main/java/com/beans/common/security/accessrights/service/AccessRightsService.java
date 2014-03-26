package com.beans.common.security.accessrights.service;

import java.util.List;
import java.util.Set;

import com.beans.common.security.accessrights.model.AccessRights;

public interface AccessRightsService {

	
	public AccessRights create(AccessRights accessRights);
	public AccessRights delete(int id) throws AccessRightsNotFound;
	public List<AccessRights> findAll();
	public AccessRights update(AccessRights accessRights) throws AccessRightsNotFound;
	public AccessRights findById(int id) throws AccessRightsNotFound;	
	public Set<AccessRights> findAllInSet();
	
}
