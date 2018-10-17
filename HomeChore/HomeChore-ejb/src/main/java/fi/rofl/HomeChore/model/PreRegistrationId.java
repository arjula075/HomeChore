package fi.rofl.HomeChore.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PreRegistrationId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getCollectiveId() {
		return collectiveId;
	}

	public void setCollectiveId(int collectiveId) {
		this.collectiveId = collectiveId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private int collectiveId;
	
	private String email;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + collectiveId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		PreRegistrationId other = (PreRegistrationId) obj;
		if (collectiveId != other.collectiveId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	

}
