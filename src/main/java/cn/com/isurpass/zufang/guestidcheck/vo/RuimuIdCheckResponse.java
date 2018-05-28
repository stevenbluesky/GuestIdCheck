package cn.com.isurpass.zufang.guestidcheck.vo;

public class RuimuIdCheckResponse 
{
	private String commandid ;
	private int errorcode = 0 ;
	private String msg ;
	
	public RuimuIdCheckResponse(String commandid, int errorcode, String msg) {
		super();
		this.commandid = commandid;
		this.errorcode = errorcode;
		this.msg = msg;
	}
	public String getCommandid() {
		return commandid;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public String getMsg() {
		return msg;
	}
	
}
