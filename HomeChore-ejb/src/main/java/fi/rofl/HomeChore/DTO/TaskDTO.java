package fi.rofl.HomeChore.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TaskDTO {
	
	private int id;
	
	private String name;
	
	private String type;
	
	private boolean complete;
	
	private boolean approved;
	
	private boolean showActions;
	
	private Timestamp completeDate;
	
	private int mainTask;
	
	private Timestamp deadline;
	
	private BigDecimal timeInterval;

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

	public void setCompleteDate(Timestamp completeDate) {
		this.completeDate = completeDate;
	}

	public int getMainTask() {
		return mainTask;
	}

	public void setMainTask(int mainTask) {
		this.mainTask = mainTask;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(BigDecimal timeInterval) {
		this.timeInterval = timeInterval;
	}

	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}

}
