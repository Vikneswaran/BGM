package com.beans.leaveapp.employeegrade.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import com.beans.leaveapp.employeegrade.model.EmployeeGradeDataModel;
import com.beans.leaveapp.employeegrade.model.EmployeeGrade;
import com.beans.leaveapp.employeegrade.service.EmployeeGradeNotFound;
import com.beans.leaveapp.employeegrade.service.EmployeeGradeService;

public class EmployeeGradeManagementBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// EmployeeGradeRepository employeeGradeRepository;
	EmployeeGradeService employeeGradeService;
	private List<EmployeeGrade> employeeGradeList;
	private EmployeeGradeDataModel employeeGradeDataModel;
	private EmployeeGrade newEmployeeGrade = new EmployeeGrade();
	private EmployeeGrade selectedEmployeeGrade = new EmployeeGrade();
	private boolean insertDeleted = false;

	public EmployeeGradeService getEmployeeGradeService() {
		return employeeGradeService;
	}

	public EmployeeGrade getNewEmployeeGrade() {
		return newEmployeeGrade;
	}

	public void setNewEmployeeGrade(EmployeeGrade newEmployeeGrade) {
		this.newEmployeeGrade = newEmployeeGrade;
	}

	public EmployeeGrade getSelectedEmployeeGrade() {
		return selectedEmployeeGrade;
	}

	public void setSelectedEmployeeGrade(EmployeeGrade selectedEmployeeGrade) {
		this.selectedEmployeeGrade = selectedEmployeeGrade;
	}

	public boolean isInsertDeleted() {
		return insertDeleted;
	}

	public void setInsertDelete(boolean insertDeleted) {
		this.insertDeleted = insertDeleted;
	}

	public void setEmployeeGradeService(EmployeeGradeService employeeGradeService) {
		this.employeeGradeService = employeeGradeService;
	}

	public List<EmployeeGrade> getEmployeeGradeList() {
		if(employeeGradeList == null || insertDeleted == true) {
			 try {
				employeeGradeList = getEmployeeGradeService().findAll();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return employeeGradeList;	
	}
	
	public EmployeeGradeDataModel getEmployeeGradeDataModel() {
		if(employeeGradeDataModel == null || insertDeleted == true) {
			employeeGradeDataModel = new EmployeeGradeDataModel(getEmployeeGradeList());
		}
		
		return employeeGradeDataModel;
	}

	public void setEmployeeGradeDataModel(
			EmployeeGradeDataModel employeeGradeDataModel) {
		this.employeeGradeDataModel = employeeGradeDataModel;
	}

	public void setEmployeeGradeList(List<EmployeeGrade> employeeGradeList) {
		this.employeeGradeList = employeeGradeList;
	}

	public void doCreateEmployeeGrade() throws EmployeeGradeNotFound {
		newEmployeeGrade.setDeleted(false);
		getEmployeeGradeService().create(newEmployeeGrade);
		setInsertDelete(true);
	}
	
	public void doUpdateEmployeeGrade() throws EmployeeGradeNotFound {
		try {
			System.out.println("New name:" + selectedEmployeeGrade.getName());
			System.out.println("ID: " + selectedEmployeeGrade.getId());
			getEmployeeGradeService().update(selectedEmployeeGrade);
		// RequestContext.getCurrentInstance().
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error", "Leave Type With id: " + selectedEmployeeGrade.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedEmployeeGrade((EmployeeGrade) event.getObject());
        FacesMessage msg = new FacesMessage("Leave Type Selected", selectedEmployeeGrade.getName());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	public void doDeleteEmployeeGrade() throws Exception, EmployeeGradeNotFound {
		getEmployeeGradeService().delete(selectedEmployeeGrade.getId());
		
		setInsertDelete(true);
	}
	
	public void doResetFrom() throws EmployeeGradeNotFound {
		
	}
	
	
}
