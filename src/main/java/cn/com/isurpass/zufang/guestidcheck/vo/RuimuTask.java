package cn.com.isurpass.zufang.guestidcheck.vo;

import java.util.UUID;

public class RuimuTask 
{
	private int taskcmd;
	private String taskid = UUID.randomUUID().toString();
	private String taskinfo;
	
	public RuimuTask(int taskcmd, String taskinfo) {
		super();
		this.taskcmd = taskcmd;
		this.taskinfo = taskinfo;
	}
	public int getTaskcmd() {
		return taskcmd;
	}
	public String getTaskinfo() {
		return taskinfo;
	}
	public String getTaskid() {
		return taskid;
	}
	
	
}
