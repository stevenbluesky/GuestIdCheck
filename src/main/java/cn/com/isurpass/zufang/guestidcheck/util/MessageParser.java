package cn.com.isurpass.zufang.guestidcheck.util;

import com.alibaba.fastjson.JSONObject;

public class MessageParser
{
	private String message ;
	private String templatecode ;
	private JSONObject parameter;
	
	public MessageParser(String message, String templatecode, JSONObject parameter)
	{
		super();
		this.message = message;
		this.templatecode = templatecode;
		this.parameter = parameter;
		if ( this.parameter == null )
			this.parameter = new JSONObject();
	}
	public String getMessage()
	{
		if( message != null && parameter != null )
		{
			for ( String key  : parameter.keySet())
			{
				message = message.replaceAll( String.format("\\$\\{%s\\}", key), parameter.getString(key));
			}
		}
		return message;
	}
	
	public String getMessageforLog()
	{
		if( message != null && parameter != null )
		{
			for ( String key  : parameter.keySet())
			{
				if ( "password".equals(key))
					message = message.replaceAll( String.format("\\$\\{%s\\}", key), "*********");
				else 
					message = message.replaceAll( String.format("\\$\\{%s\\}", key), parameter.getString(key));
			}
		}
		return message;
	}
	
	public String getTemplatecode()
	{
		return templatecode;
	}
	public JSONObject getParameter()
	{
		return parameter;
	}
	
	
}
