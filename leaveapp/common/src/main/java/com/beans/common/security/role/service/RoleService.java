package com.beans.common.security.role.service;

import java.util.List;
import java.util.Set;

import com.beans.common.security.role.model.Role;

public interface RoleService {
	
	
	public Role create(Role role);
	public Role delete(int id) throws RoleNotFound;
	public List<Role> findAll();
	public Role update(Role role) throws RoleNotFound;
	public Role findById(int id) throws RoleNotFound;
	public Role findByRole(String role);
	public Set<Role> findAllInSet();
}
