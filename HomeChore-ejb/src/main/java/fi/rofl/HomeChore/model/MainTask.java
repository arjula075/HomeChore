package fi.rofl.HomeChore.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

@Entity
@XmlRootElement
@Table(name = "maintask")
public class MainTask implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue
	private int id;

	@Expose
	private String name;

	@Expose
	private String type;

	@Expose
	private BigDecimal defaultInterval;
	
	@Expose
	private boolean approvable;

	@Expose
	@Column(length = 65535,columnDefinition="Text")
	private String explanation;

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

	public BigDecimal getDefaultInterval() {
		return defaultInterval;
	}

	public void setDefaultInterval(BigDecimal defaultInterval) {
		this.defaultInterval = defaultInterval;
	}

	public boolean isApprovable() {
		return approvable;
	}

	public void setApprovable(boolean approvable) {
		this.approvable = approvable;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}




}
