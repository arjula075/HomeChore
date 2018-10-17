package fi.rofl.HomeChore.DTO;

import java.util.List;

public class MemberDTO {
	
	private int id;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private List<TaskDTO> tasks;

	public List<TaskDTO> getTasks() {
		return tasks;
	}



	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}



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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

}
