package fi.rofl.HomeChore.model;

import java.sql.Timestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import fi.rofl.HomeChore.util.ChoreNamedQueries;

@Entity
@XmlRootElement
@Table(name = "pre_registration")
@NamedQueries({
    @NamedQuery(name= ChoreNamedQueries.PREREGISTRATIONS_GET_BY_EMAIL,
                query="SELECT c FROM PreRegistration c WHERE c.id.email =:email")
}) 
public class PreRegistration {
	
	@Id
	@Embedded
	private PreRegistrationId id;
	
	private Timestamp createTs;

	public PreRegistrationId getId() {
		return id;
	}

	public void setId(PreRegistrationId id) {
		this.id = id;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTs == null) ? 0 : createTs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PreRegistration other = (PreRegistration) obj;
		if (createTs == null) {
			if (other.createTs != null)
				return false;
		} else if (!createTs.equals(other.createTs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
