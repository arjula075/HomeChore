package fi.rofl.HomeChore.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

@Entity
@XmlRootElement
@Table(name = "Task")
public class Task {
	
	@Expose
    @Id
    private int id;
    
	@Expose
    @NotNull
    @NotEmpty
    private String name;
    
	@Expose
    @NotNull
    @NotEmpty
    private String type;
    
	@Expose
    private boolean complete;
    
	@Expose
    private boolean approved;
    
	@Expose
    private boolean showActions;
    
	@Expose
    private Timestamp completeDate;
    
	@Expose
    private int mainTask;
	
	@Expose
	private BigDecimal timeInterval;
    

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assignedTo")
    private Member assignedTo;
    
    @Expose
    private Integer actuallyDid;
    
    @Expose
    private Timestamp deadline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isShowActions() {
		return showActions;
	}

	public void setShowActions(boolean showActions) {
		this.showActions = showActions;
	}

	public Timestamp getCompleteDate() {
		return completeDate;
	}

	public int getMainTask() {
		return mainTask;
	}

	public void setMainTask(int mainTask) {
		this.mainTask = mainTask;
	}

	public Member getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Member assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Integer getActuallyDid() {
		return actuallyDid;
	}

	public void setActuallyDid(Integer actuallyDid) {
		this.actuallyDid = actuallyDid;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public void setCompleteDate(Timestamp completeDate) {
		this.completeDate = completeDate;
	}

	

	public BigDecimal getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(BigDecimal interval) {
		this.timeInterval = interval;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuallyDid == null) ? 0 : actuallyDid.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((assignedTo == null) ? 0 : assignedTo.hashCode());
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + id;
		result = prime * result + ((timeInterval == null) ? 0 : timeInterval.hashCode());
		result = prime * result + mainTask;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (showActions ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (actuallyDid == null) {
			if (other.actuallyDid != null)
				return false;
		} else if (!actuallyDid.equals(other.actuallyDid))
			return false;
		if (approved != other.approved)
			return false;
		if (assignedTo == null) {
			if (other.assignedTo != null)
				return false;
		} else if (!assignedTo.equals(other.assignedTo))
			return false;
		if (complete != other.complete)
			return false;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (id != other.id)
			return false;
		if (timeInterval == null) {
			if (other.timeInterval != null)
				return false;
		} else if (!timeInterval.equals(other.timeInterval))
			return false;
		if (mainTask != other.mainTask)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (showActions != other.showActions)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", type=" + type + ", complete=" + complete + ", approved="
				+ approved + ", showActions=" + showActions + ", completeDate=" + completeDate + ", mainTask="
				+ mainTask + ", interval=" + timeInterval + ", assignedTo=" + assignedTo + ", actuallyDid=" + actuallyDid
				+ ", deadline=" + deadline + "]";
	}

	

	
}
