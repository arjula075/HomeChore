package fi.rofl.HomeChore.DTO;

import java.util.List;

import fi.rofl.HomeChore.model.Member;

public class CollectiveDTO {
	
	private int id;
	
	private String name;
	
	private String type;
	
	private List<MemberDTO> members;
	
	public CollectiveDTO() {
		// TODO Auto-generated constructor stub
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}



}
