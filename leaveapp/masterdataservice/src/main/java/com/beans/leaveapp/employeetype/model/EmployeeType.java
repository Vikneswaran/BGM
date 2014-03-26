package com.beans.leaveapp.employeetype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="EmployeeType")
public class EmployeeType {
	private int id;
	private String name;
	private String description;
	private boolean isDeleted= false;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description", nullable=true)
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
	
	public boolean equals(Object other)
	{
	    return other instanceof EmployeeType && id == ((EmployeeType) other).getId();
	}

	public int hashCode()
	{
	    return this.getClass().hashCode();
	}

	public String toString()
	{
	    return "EmployeeType[" + getId() + "," + getName() + "]";
	}
	
}