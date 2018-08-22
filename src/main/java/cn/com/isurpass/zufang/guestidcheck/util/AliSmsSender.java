package cn.com.isurpass.zufang.guestidcheck.util;

import com.alibaba.fastjson.JSONObject;


public class AliSmsSender {
	private static final String ALI_APPCODE = "APPCODE 78ad21d98ef6429291a0f22417375520" ;
	private static final String ALI_SMS_SIGNNAME = "\u7BA1\u5BB6\u63D0\u9192" ;
	
	public int sendSMS(String countrycode, String phonenumber, MessageParser template) {
		JSONObject header = new JSONObject();
		header.put("Authorization", ALI_APPCODE);
		
		JSONObject parameter = new JSONObject();
		parameter.put("ParamString", template.getParameter().toJSONString());
		parameter.put("RecNum", phonenumber);
		parameter.put("SignName", ALI_SMS_SIGNNAME);
		parameter.put("TemplateCode", template.getTemplatecode());
		HttpUtil.httpGet("http://sms.market.alicloudapi.com/singleSendSms", parameter, header);
		return 0;
	}

}
