package com.beans.common.security.users.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.beans.common.security.role.model.Role;

@Entity
@Table(name="Users")
public class Users {
	private int id;
	private String username;
	private String password;
	private boolean enabled;
	private Set<Role> userRoles = new HashSet<Role>();
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="username", nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="enabled", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "UserToRole" , joinColumns = { @JoinColumn(name = "userId", referencedColumnName = "id" )},
	inverseJoinColumns=
			{@JoinColumn (name = "userRoleId", referencedColumnName = "id" ) } )
	public Set<Role> getUserRoles() {
		return userRoles;
	}
	
	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}	
	
}
