package cn.com.isurpass.zufang.guestidcheck.po;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@javax.persistence.Entity
@Table(name="room" )
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@GenericGenerator(name = "generator", strategy = "increment") 
	private Long id;
	
	/**用户id*/
	@Column(name = "personid")
	private Long personId;

	/**小区id*/
	@Column(name = "districtid")
	private Long districtId;
	
	/**房间类型 1 一居室；2 两居室；3 三居室*/
	@Column(name = "roomtype")
	private Integer roomType;
	
	/**房间名称*/
	@Column(name = "roomname")
	private String roomName;
	
	
	/**图片*/
	@Column(name = "roomimg")
	private String roomImg;
	
	private int rentfee;
	
	private int balance;
	private int arrearage;
	
	/**栋*/
	private String build;
	
	/**单元*/
	private String unit;
	
	/**楼*/
	private String floor;
	
	/**设备数量*/
//	private Integer deviceCount;
	
	/**是否已授权 1 已授权；0 未授权*/
	@Column(name = "grantflag")
	private Integer grantFlag;
	
	/**授权用户名*/
	@Column(name = "grantusername")
	private String grantUserName;
	
	/**授权租客名称*/
	@Column(name = "grantrealname")
	private String grantRealName;
	
	/**备注*/
	private String remark;
	
	/**插入时间*/
	@Column(name = "inputdate")
	private Date inputDate;
	
	/**授权开始时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "grantbegindate")
	private Date grantBeginDate;
	
	/**授权结束时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "grantenddate")
	private Date grantEndDate;

	public Date getGrantBeginDate() {
		return grantBeginDate;
	}

	public void setGrantBeginDate(Date grantBeginDate) {
		this.grantBeginDate = grantBeginDate;
	}

	public Date getGrantEndDate() {
		return grantEndDate;
	}

	public void setGrantEndDate(Date grantEndDate) {
		this.grantEndDate = grantEndDate;
	}

	public String getGrantUserName() {
		return grantUserName;
	}

	public void setGrantUserName(String grantUserName) {
		this.grantUserName = grantUserName;
	}

	public String getGrantRealName() {
		return grantRealName;
	}

	public void setGrantRealName(String grantRealName) {
		this.grantRealName = grantRealName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Integer getGrantFlag() {
		return grantFlag;
	}

	public void setGrantFlag(Integer grantFlag) {
		this.grantFlag = grantFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public int getRentfee() {
		return rentfee;
	}

	public void setRentfee(int rentfee) {
		this.rentfee = rentfee;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getArrearage() {
		return arrearage;
	}

	public void setArrearage(int arrearage) {
		this.arrearage = arrearage;
	}
	
}
