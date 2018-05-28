package cn.com.isurpass.zufang.guestidcheck.vo;

import java.util.ArrayList;
import java.util.List;

public class RuimuHeartBeatResponse 
{
	private String commandid ;
	private int errorcode = 0 ;
	private List<RuimuTask> tasks = new ArrayList<RuimuTask>();
	
	public RuimuHeartBeatResponse(String commandid, int errorcode) {
		super();
		this.commandid = commandid;
		this.errorcode = errorcode;
	}
	public String getCommandid() {
		return commandid;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public List<RuimuTask> getTasks() {
		return tasks;
	}
	
	
}
