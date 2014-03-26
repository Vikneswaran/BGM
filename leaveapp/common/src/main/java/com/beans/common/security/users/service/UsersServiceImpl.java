package com.beans.common.security.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.common.security.role.model.Role;
import com.beans.common.security.role.service.RoleNotFound;
import com.beans.common.security.role.service.RoleService;
import com.beans.common.security.users.model.Users;
import com.beans.common.security.users.repository.UsersRepository;


@Service
public class UsersServiceImpl implements UsersService {
	

	@Resource
	private UsersRepository usersRepository;
	
	RoleService roleService;
	
	@Override
	@Transactional
	public Users create(Users users) {	
		 Users usersToBeCreated = users;
		return usersRepository.save(usersToBeCreated);
	}

	@Override
	@Transactional(rollbackFor=UsersNotFound.class)
	public Users delete(int id) throws UsersNotFound {	
		
		return null;
	}

	@Override
	public List<Users> findAll() {
			List<Users> usersList = usersRepository.findByIsEnabled(true);
		return usersList;
	}

	@Override
	@Transactional(rollbackFor=UsersNotFound.class)
	public Users update(Users users) throws UsersNotFound {
		Users usersToBeUpdated = usersRepository.findOne(users.getId());
		
		if(usersToBeUpdated == null)
			 throw new UsersNotFound();
		usersToBeUpdated.setUsername(users.getUsername());
		usersToBeUpdated.setPassword(users.getPassword());
		usersToBeUpdated.setEnabled(users.isEnabled());
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.addAll(users.getUserRoles());
		usersToBeUpdated.setUserRoles(roleSet);
		usersRepository.save(usersToBeUpdated);		
		return usersToBeUpdated;
	}

	@Override	
	public Users registerUser(Users users) {
		
		Users createdUsers = usersRepository.save(users);
		Role userRole = roleService.findByRole("ROLE_USER");
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(userRole);
		createdUsers.setUserRoles(roleSet);		
		
		return usersRepository.save(createdUsers);
	}
	
	@Override	
	public Users findById(int id) throws UsersNotFound {
		Users users = usersRepository.findOne(id);
		
		if(users == null)
			throw new UsersNotFound();
		
		return users;
	}
	
	
	
	@Override
	public Users findByUsername(String username) throws UsersNotFound {
		Users users = usersRepository.findByUsername(username);
		
		if(users == null) {
			throw new UsersNotFound();
		}
		
		return users;
	}

	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	

	 
}
