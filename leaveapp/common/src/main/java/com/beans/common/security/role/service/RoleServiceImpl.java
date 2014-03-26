package com.beans.common.security.role.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.role.model.Role;
import com.beans.common.security.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	public static String role="ROLE_USER";
	
	@Resource
	private RoleRepository roleRepository;
	
	
	@Override
	@Transactional
	public Role create(Role role) {
		Role roleToBeCreated = role;		
		return roleRepository.save(roleToBeCreated);
	}

	@Override
	@Transactional
	public Role delete(int id) throws RoleNotFound {
		Role role = roleRepository.findOne(id);
		
		if(role == null)
			throw new RoleNotFound();
		role.setDeleted(true);
		roleRepository.save(role);
		return role;
	}

	@Override
	public List<Role> findAll() {
		List<Role> resultList = roleRepository.findByIsDeleted(0);
		return resultList;
	}
	
	@Override
	public Set<Role> findAllInSet() {
		Set<Role> resultSet = roleRepository.findAllInSet();
		return resultSet;
	}

	@Override
	@Transactional
	public Role update(Role role) throws RoleNotFound {
		Role roleToBeUpdated = roleRepository.findOne(role.getId());
		
		if(roleToBeUpdated == null)
			throw new RoleNotFound();
		roleToBeUpdated.setId(role.getId());
		roleToBeUpdated.setRole(role.getRole());
		roleToBeUpdated.setDescription(role.getDescription());
		Set<AccessRights> accessRightsSet = new HashSet<AccessRights>();
		accessRightsSet.addAll(role.getAccessRights());
		roleToBeUpdated.setAccessRights(accessRightsSet);
		roleRepository.save(roleToBeUpdated);
		return roleToBeUpdated;
	}

	@Override
	public Role findByRole(String role) {
		Role foundRole = roleRepository.findByRole(role);			
		return foundRole;
	}
	
	@Override
	@Transactional
	public Role findById(int id) throws RoleNotFound {
	    Role role = roleRepository.findOne(id);
	     
	       if(role == null)
	    	   throw new RoleNotFound();	     
		return role;
	}	

	
}
