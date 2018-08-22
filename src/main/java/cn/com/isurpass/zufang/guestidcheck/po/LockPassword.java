package cn.com.isurpass.zufang.guestidcheck.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@javax.persistence.Entity
@Table(name="lockpassword" )
public class LockPassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@GenericGenerator(name = "generator", strategy = "increment") 
	private long lockpasswordid;
	private int dvcid;
	private int usercode;
	private String username;
	private String phonenumber;
	private int usertype ;
	private Date validfrom;
	private Date validthrough;
	private Date createtime;
	private int status;
	private Date deletetime;
	private int weekday;
	private String starttime;
	private String endtime;
	private String password;
	
	public long getLockpasswordid() {
		return lockpasswordid;
	}
	public void setLockpasswordid(long lockpasswordid) {
		this.lockpasswordid = lockpasswordid;
	}
	public int getDvcid() {
		return dvcid;
	}
	public void setDvcid(int dvcid) {
		this.dvcid = dvcid;
	}
	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	public Date getValidthrough() {
		return validthrough;
	}
	public void setValidthrough(Date validthrough) {
		this.validthrough = validthrough;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
	}
	public int getUsertype()
	{
		return usertype;
	}
	public void setUsertype(int usertype)
	{
		this.usertype = usertype;
	}

	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
