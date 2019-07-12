package cn.java.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class RoomType {

	private Integer id;
	
	@Excel(name = "房间类型名字" ,orderNum = "7")
	private String room_type_name;
	
	private Float room_price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoom_type_name() {
		return room_type_name;
	}

	public void setRoom_type_name(String room_type_name) {
		this.room_type_name = room_type_name;
	}

	public Float getRoom_price() {
		return room_price;
	}

	public void setRoom_price(Float room_price) {
		this.room_price = room_price;
	}
	
	
}
