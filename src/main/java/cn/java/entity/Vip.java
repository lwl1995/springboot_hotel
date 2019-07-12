package cn.java.entity;

import java.io.Serializable;

public class Vip implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String vipNum;
	
	private String customerName;
	
	private String gender;
	
	private String idcard;
	
	private String phone;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVipNum() {
		return vipNum;
	}

	public void setVipNum(String vipNum) {
		this.vipNum = vipNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	@Override
	public String toString() {
		return "Vip [id=" + id + ", vipNum=" + vipNum + ", customerName=" + customerName + ", gender=" + gender
				+ ", idcard=" + idcard + ", phone=" + phone + "]";
	}
	 
	 


	
	
}
