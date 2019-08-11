package com.hotelmanagement.dto;

public class RoomDetailsBean {
	
	private int room_id;
	private int hotel_id;
	private String room_no;
	private String roomm_type;
	private int per_night_rate;
	private char availability;
	public RoomDetailsBean(int room_id, int hotel_id, String room_no, String roomm_type, int per_night_rate,
			char availability) {
		super();
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.room_no = room_no;
		this.roomm_type = roomm_type;
		this.per_night_rate = per_night_rate;
		this.availability = availability;
	}
	public RoomDetailsBean(int room_id, int hotel_id, String room_no, String roomm_type, int per_night_rate) {
		super();
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.room_no = room_no;
		this.roomm_type = roomm_type;
		this.per_night_rate = per_night_rate;
	}
	public RoomDetailsBean(int hotel_id, String room_no, String roomm_type, int per_night_rate) {
		super();
		this.hotel_id = hotel_id;
		this.room_no = room_no;
		this.roomm_type = roomm_type;
		this.per_night_rate = per_night_rate;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getRoomm_type() {
		return roomm_type;
	}
	public void setRoomm_type(String roomm_type) {
		this.roomm_type = roomm_type;
	}
	public int getPer_night_rate() {
		return per_night_rate;
	}
	public void setPer_night_rate(int per_night_rate) {
		this.per_night_rate = per_night_rate;
	}
	public char getAvailability() {
		return availability;
	}
	public void setAvailability(char availability) {
		this.availability = availability;
	}
	@Override
	public String toString() {
		return "RoomDetailsBean [room_id=" + room_id + ", hotel_id=" + hotel_id + ", room_no=" + room_no
				+ ", roomm_type=" + roomm_type + ", per_night_rate=" + per_night_rate + ", availability=" + availability
				+ "]";
	}
	
}
