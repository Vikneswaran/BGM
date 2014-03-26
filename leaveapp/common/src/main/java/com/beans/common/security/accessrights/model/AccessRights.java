package com.beans.common.security.accessrights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.beans.common.security.role.model.Role;

@Entity
@Table(name="AccessRights")
public class AccessRights {
 
	private int id;
	private String accessRights;
	private String description;	   
	private boolean isDeleted = false;   
	   
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	
	@Column(name="accessRights", nullable=false) 
	public String getAccessRights() {
		return accessRights;
	}
	
	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}
	
	@Column(name="description" , nullable=false)
	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="isDeleted", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}		
	
	
	@Override
	public boolean equals(Object obj) {
		AccessRights inputAccessRights = (AccessRights) obj;
		if(getId() == inputAccessRights.getId()) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public int hashCode() {
		return id;
	}
		
	 
}
