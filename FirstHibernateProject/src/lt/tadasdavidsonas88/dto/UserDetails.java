package lt.tadasdavidsonas88.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "USER_DETAILS")
@Table
public class UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "USER_NAME")
	private String userName;
	@ElementCollection
	@JoinTable(name="USER_ADDRESS",
			joinColumns=@JoinColumn(name="USER_ID")
			)
	@GenericGenerator(name="sequence-gen",strategy="sequence")
	@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();  // ArrayList supports indexes


	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET_NAME")),
			@AttributeOverride(name = "city", column = @Column(name = "HOME_CITY_NAME")),
			@AttributeOverride(name = "state", column = @Column(name = "HOME_STATE_NAME")),
			@AttributeOverride(name = "pincode", column = @Column(name = "HOME_PIN_CODE")) })
	private Address homeAddress;
	@Embedded
	private Address officeAddress;
	@Lob
	private String description;
	@Transient
	private String ignoredProperty;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIgnoredProperty() {
		return ignoredProperty;
	}

	public void setIgnoredProperty(String ignoredProperty) {
		this.ignoredProperty = ignoredProperty;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}

	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	
	
}
