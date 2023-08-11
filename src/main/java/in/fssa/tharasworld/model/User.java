
package in.fssa.tharasworld.model;

import java.time.LocalDate;

public abstract class User implements Comparable<User>{
	

	private int id;
	private String email;
	private String name;
	private long phoneNumber;
	private String password;
	private boolean isActive;
	private int age;
	private LocalDate currentDate;
	private LocalDate modifiedAt;
	private String role;
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	 
	@Override
	public int compareTo(User u) {
			
		if (this.getId() == u.getId()) {
			return 0;
		} else {
			if (this.id>getId()) {
				return 1;
			} else {
				return -1;
			}
			
		} 
		
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", isActive=" + isActive + ", age=" + age + ", currentDate=" + currentDate
				+ ", modifiedAt=" + modifiedAt + ", role=" + role + "]";
	}

	
}
