package cn.java.entity;

public class Rooms {
	
	private Integer id;

	private String roomNum;
	
	private String createTime;
	
	private String room_type_id;
	
	private String roomStatus; 
	

	

	

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(String room_type_id) {
		this.room_type_id = room_type_id;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Rooms [id=" + id + ", roomNum=" + roomNum + ", createTime=" + createTime + ", room_type_id="
				+ room_type_id + ", roomStatus=" + roomStatus + "]";
	}



	
	

	
	
	
	
}
