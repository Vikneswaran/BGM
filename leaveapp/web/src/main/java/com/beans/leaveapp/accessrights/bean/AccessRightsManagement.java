package com.beans.leaveapp.accessrights.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.accessrights.service.AccessRightsNotFound;
import com.beans.common.security.accessrights.service.AccessRightsService;
import com.beans.leaveapp.accessrights.model.AccessRightsDataModel;

public class AccessRightsManagement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	AccessRightsService accessRightsService;
	private List<AccessRights> accessRightsList;
	private AccessRightsDataModel accessRightsDataModel;
	private AccessRights newAccessRights = new AccessRights();
	private AccessRights selectedAccessRights = new AccessRights();
	private boolean insertDelete = false;
	private List<AccessRights> searchAccessRights;
	
	
	public AccessRightsService getAccessRightsService() {
		return accessRightsService;
	}
	
	public void setAccessRightsService(AccessRightsService accessRightsService) {
		this.accessRightsService = accessRightsService;
	}
	
	
	public List<AccessRights> getAccessRightsList() {
		
		if(accessRightsList == null || insertDelete == true){
			
			accessRightsList = accessRightsService.findAll();
		}		
		return accessRightsList;
	}
	
	public void setAccessRightsList(List<AccessRights> accessRightsList) {
		this.accessRightsList = accessRightsList;
	}
	
	
	public AccessRightsDataModel getAccessRightsDataModel() {
		
		if(accessRightsDataModel == null || insertDelete == true){
			
			accessRightsDataModel = new AccessRightsDataModel(getAccessRightsList());
		}		
		return accessRightsDataModel;
	}
	
	public void setAccessRightsDataModel(AccessRightsDataModel accessRightsDataModel) {
		this.accessRightsDataModel = accessRightsDataModel;
	}
	
	
	public AccessRights getNewAccessRights() {
		return newAccessRights;
	}
	
	public void setNewAccessRights(AccessRights newAccessRights) {
		this.newAccessRights = newAccessRights;
	}
	
	public void doCreateAccessRights(){
		newAccessRights.setDeleted(false);
		getAccessRightsService().create(newAccessRights);
		setInsertDelete(true);		
	}
	
	
	public AccessRights getSelectedAccessRights() {
		return selectedAccessRights;
	}
	
	public void setSelectedAccessRights(AccessRights selectedAccessRights) {
		this.selectedAccessRights = selectedAccessRights;
	}
	
	
	public void doUpdateAccessRights() {
		try {
			System.out.println("New UserAccess:" + selectedAccessRights.getAccessRights());
			System.out.println("Id:" + selectedAccessRights.getId());
			System.out.println("Description:" + selectedAccessRights.getDescription());
			accessRightsService.update(selectedAccessRights);		
			
		} catch (AccessRightsNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "UserAccess with Id: " + selectedAccessRights.getId() + "is not found");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void onRowSelect(SelectEvent event){
		setSelectedAccessRights((AccessRights)event.getObject());
		 FacesMessage msg = new FacesMessage("UserAccess Name Selected: ", selectedAccessRights.getAccessRights()); 
	        
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
	}
	
	
	public void doDeleteAccessRights(){
		try {
			getAccessRightsService().delete(selectedAccessRights.getId());
		} catch (AccessRightsNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "UserAccess with Id: " + selectedAccessRights.getId() + "is not found");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public boolean isInsertDelete() {
		return insertDelete;
	}
	public void setInsertDelete(boolean insertDelete) {
		this.insertDelete = insertDelete;
	}
	public List<AccessRights> getSearchAccessRights() {
		return searchAccessRights;
	}
	public void setSearchAccessRights(List<AccessRights> searchAccessRights) {
		this.searchAccessRights = searchAccessRights;
	}	
	

}
