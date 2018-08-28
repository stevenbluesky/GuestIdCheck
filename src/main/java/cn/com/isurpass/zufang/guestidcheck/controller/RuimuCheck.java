package cn.com.isurpass.zufang.guestidcheck.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.com.isurpass.zufang.guestidcheck.service.LockPasswordService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.com.isurpass.zufang.guestidcheck.vo.RuimuHeartBeatResponse;
import cn.com.isurpass.zufang.guestidcheck.vo.RuimuIdCheckResponse;
import cn.com.isurpass.zufang.guestidcheck.vo.RuimuTask;

@Controller
@RequestMapping(value = "/ruimu")
@EnableAutoConfiguration
public class RuimuCheck {
    private final static String password = "9emvf823mivc63JS6Q0dms";
    @Autowired
    private LockPasswordService lps;
    private static final Logger log = LoggerFactory.getLogger(RuimuCheck.class);

    @RequestMapping(value = "/ruimurequest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object ruimurequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println("Request arrive:******************************");
        printRequestData(request);
        String reqstr = readParameter(request);

        checkMd5(reqstr, password, request.getHeader("Md5"));
        System.out.println("Use Mac as password ");
        checkMd5(reqstr, request.getHeader("Mac"), request.getHeader("Md5"));

        JSONObject json = this.readJsonContent(reqstr);

        String command = request.getHeader("Command");
        response.addHeader("Command", request.getHeader("Command"));
        response.addHeader("Ver", request.getHeader("Ver"));

        Object rst = null;
        if (StringUtils.isEmpty(command))
            rst = new RuimuIdCheckResponse(getCommandId(json), -1, "Command is null");

        if ("record".equals(command))
            rst = guestchecked(json);
        else if ("gettask".equals(command))
            rst = heartbeat(json);
        else if ("report".equals(command))
            rst = new RuimuIdCheckResponse(getCommandId(json), 0, "success");
        else
            rst = new RuimuIdCheckResponse(getCommandId(json), -2, "Not support command:" + command);

        System.out.print("send:");
        System.out.println(JSON.toJSONString(rst));

        return rst;
    }

    private boolean checkMd5(String rsqstr, String password, String ruimumd5) {
        String str = rsqstr + password;

        String svrmd5 = DigestUtils.md5Hex(str);

        System.out.println(svrmd5);

        if (StringUtils.isEmpty(ruimumd5)) {
            System.out.println("Ruimu MD5 is null");
            return false;
        }

        if (svrmd5.equalsIgnoreCase(ruimumd5)) {
            System.out.println("Md5 match");
            return true;
        } else {
            System.out.println("Md5 not match");
            return false;
        }
    }

    /**
     * 上传刷脸记录
     * @param json
     * @return
     */
    public RuimuIdCheckResponse guestchecked(JSONObject json) {
        if (json.containsKey("cardInfo") && json.getJSONObject("cardInfo").containsKey("identityPic")) {
            savepic("identityPic.jpg", json.getJSONObject("cardInfo").getString("identityPic"));
        }

        if (json.containsKey("verifyResult") && json.getJSONObject("verifyResult").containsKey("photo")) {
            savepic("photo.jpg", json.getJSONObject("verifyResult").getString("photo"));
        }
        if (json.containsKey("cardInfo") && json.getJSONObject("cardInfo").containsKey("partyName") &&
                json.containsKey("verifyResult") && json.getJSONObject("verifyResult").getBoolean("success") &&
                json.getJSONObject("verifyResult").getDouble("similar") > 0.2) {
            log.info("上传参数：{\nbornDay:" + json.getJSONObject("cardInfo").getString("bornDay") + "\ncertAddress:" + json.getJSONObject("cardInfo").getString("certAddress") +
                    "\ncertNumber:" + json.getJSONObject("cardInfo").getString("certNumber") + "\npartyName:" + json.getJSONObject("cardInfo").getString("partyName") +
                    "\nsuccess:" + json.getJSONObject("verifyResult").getBoolean("success") + "\nsimilar:" + json.getJSONObject("verifyResult").getDouble("similar") + "}");
            lps.sendMessageToCustomer(json.getJSONObject("cardInfo").getString("partyName"));
        }

        return new RuimuIdCheckResponse(getCommandId(json), 0, "");
    }

    /**
     * 心跳回复
     * @param json
     * @return
     */
    public RuimuHeartBeatResponse heartbeat(JSONObject json) {

        RuimuHeartBeatResponse rsp = new RuimuHeartBeatResponse(getCommandId(json), 0);
        if (json.containsKey("statCode") && json.getIntValue("statCode") != 1) {
            rsp.getTasks().add(new RuimuTask(1, "a08d0cb23ed3e60bb2c58d8bbbcf71f"));
            rsp.getTasks().add(new RuimuTask(5, password));
        }

        return rsp;
    }

    private String getCommandId(JSONObject json) {
        if (json == null)
            return "";
        if (!json.containsKey("commandid"))
            return "";
        return json.getString("commandid");
    }

    private void printRequestData(HttpServletRequest request) {
        System.out.println("parameter------");
        Enumeration<String> ans = request.getParameterNames();
        for (; ans.hasMoreElements(); ) {
            String key = ans.nextElement();
            System.out.print(key);
            System.out.print("=");
            System.out.println(request.getParameter(key));
        }

        System.out.println("Header------");
        ans = request.getHeaderNames();
        for (; ans.hasMoreElements(); ) {
            String key = ans.nextElement();
            System.out.print(key);
            System.out.print("=");
            System.out.println(request.getHeader(key));
        }

        //System.out.println(readParameter(request));
    }

    private JSONObject readJsonContent(String reqstr) {
        System.out.print("Receive data body:");
        System.out.println(reqstr);
        if (StringUtils.isEmpty(reqstr))
            return null;
        return JSON.parseObject(reqstr);
    }

    public static String readParameter(HttpServletRequest request) {
        try {
            int contentLength = request.getContentLength();
            if (contentLength < 0) {
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength; ) {
                int len = request.getInputStream().read(buffer, i, contentLength - i);
                if (len == -1) {
                    break;
                }
                i += len;
            }
            return new String(buffer, "utf-8");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static void savepic(String filename, String base64pic) {

        byte[] b = Base64.decodeBase64(base64pic);

        try {
            FileUtils.writeByteArrayToFile(new File(filename), b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        savepic("E:/tmp/ruimu.jpg", "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");
    }
}
