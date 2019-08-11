package com.hotelmanagement.dto;

public class UserBean {
	public UserBean(int user_id, String user_name, String mobile_no, String phone, String address, String email) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.mobile_no = mobile_no;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	private int user_id;
	private String password;
	private String role;
	private String user_name;
	private String mobile_no;
	private String phone;
	private String address;
	private String email;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserBean(int user_id, String password, String role, String user_name, String mobile_no, String phone,
			String address, String email) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.role = role;
		this.user_name = user_name;
		this.mobile_no = mobile_no;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public UserBean(String password, String role, String user_name, String mobile_no, String phone, String address,
			String email) {
		super();
		this.password = password;
		this.role = role;
		this.user_name = user_name;
		this.mobile_no = mobile_no;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public UserBean(int user_id, String user_name) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
	}
	
	
}
