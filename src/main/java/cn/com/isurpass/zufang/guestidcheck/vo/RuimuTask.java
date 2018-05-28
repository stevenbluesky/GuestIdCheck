package cn.com.isurpass.zufang.guestidcheck.vo;

public class RuimuTask 
{
	private int taskcmd;
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
	
	
}
