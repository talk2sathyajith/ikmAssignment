package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7436442615232014474L;

	@Column(name = "user_id")
	private Long userID;

//	private Integer  companyID;

	@CreationTimestamp
	@Column(name = "creation_date", insertable = true, updatable = false)
	private Timestamp creationDate;

	@UpdateTimestamp
	@Column(name = "modified_date", insertable = true, updatable = true)
	private Timestamp modifiedDate;

	@Column(name = "active_status", columnDefinition = "integer default 1")
	private int activeStatus = 1;

	public CommonFields() {

	}
}
