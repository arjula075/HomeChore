package fi.rofl.HomeChore.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

@Entity
@XmlRootElement
@Table(name = "taskhistory")
public class TaskHistory {
	
	@Expose
	@Id
	@GeneratedValue
    private int id;
    
	@Expose
    private int taskId;
	
	@Expose
    private int registrantId;
    
	@Expose
    @NotNull
    private Timestamp actualDate;
	
	@Expose
    private Timestamp approvalDate;
    
	@Expose
    private Integer approvedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getRegistrantId() {
		return registrantId;
	}

	public void setRegistrantId(int registrantId) {
		this.registrantId = registrantId;
	}

	public Timestamp getActualDate() {
		return actualDate;
	}

	public void setActualDate(Timestamp actualDate) {
		this.actualDate = actualDate;
	}

	public Timestamp getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	
    
}
