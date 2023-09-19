package in.fssa.tharasworld.model;

import java.time.LocalDate;

public abstract class Address implements Comparable<Address> {

	private String name;
	private String address;
	private String state;
	private int pincode;
	private boolean setAsDefaultStatus;
	private int userId;
	private LocalDate currentDate;
	private LocalDate modifiedAt;
	private boolean isActive;
	private int addressId;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public boolean isSetAsDefaultStatus() {
		return setAsDefaultStatus;
	}

	public void setSetAsDefaultStatus(boolean setAsDefaultStatus) {
		this.setAsDefaultStatus = setAsDefaultStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public LocalDate getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int compareTo(Address a) {

		if (this.getAddressId() == a.getAddressId()) {
			return 0;
		} else {
			if (this.addressId > getAddressId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", name=" + name + ", address=" + address + ", state=" + state
				+ ", pincode=" + pincode + ", setAsDefaultStatus=" + setAsDefaultStatus + ", userId=" + userId
				+ ", currentDate=" + currentDate + ", modifiedAt=" + modifiedAt + ", isActive=" + isActive + "]";
	}

}
