package fi.rofl.HomeChore.DTO;

import java.io.Serializable;

import fi.rofl.HomeChore.model.Member;

public class SessionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
