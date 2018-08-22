package cn.com.isurpass.zufang.guestidcheck.service;

import cn.com.isurpass.zufang.guestidcheck.dao.DeviceDAO;
import cn.com.isurpass.zufang.guestidcheck.dao.DistrictDAO;
import cn.com.isurpass.zufang.guestidcheck.dao.LockPasswordDAO;
import cn.com.isurpass.zufang.guestidcheck.dao.RoomDAO;
import cn.com.isurpass.zufang.guestidcheck.po.Device;
import cn.com.isurpass.zufang.guestidcheck.po.District;
import cn.com.isurpass.zufang.guestidcheck.po.LockPassword;
import cn.com.isurpass.zufang.guestidcheck.po.Room;
import cn.com.isurpass.zufang.guestidcheck.util.AES;
import cn.com.isurpass.zufang.guestidcheck.util.AliSmsSender;
import cn.com.isurpass.zufang.guestidcheck.util.MessageParser;
import cn.com.isurpass.zufang.guestidcheck.util.MjConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author liwenxiang
 * Date:2018/8/22
 * Time:11:14
 */
@Service
public class LockPasswordService {

    @Autowired
    private DeviceDAO dd;
    @Autowired
    private LockPasswordDAO lpd;
    @Autowired
    private DistrictDAO districtDAO;
    @Autowired
    private RoomDAO roomDAO;

    @Transactional
    public void sendMessageToCustomer(String name) {
        List<Long> deviceidlist = new ArrayList<>();
        Set<String> phonenumberset = new TreeSet<>();
        String districtIds = MjConfig.get("districtId");
        long districtId = Long.parseLong(districtIds);
        List<Device> devicelist = dd.findByDistrictIdAndDeviceType(districtId, 0);
        for (Device d : devicelist) {
            deviceidlist.add(d.getId());
        }
        List<LockPassword> lockpasswordrecordlist = lpd.findByUsernameAndUsertype(name, 21);
        for (Iterator it = lockpasswordrecordlist.iterator(); it.hasNext(); ) {
            LockPassword lp = (LockPassword) it.next();
            if (lp.getValidthrough().getTime() < new Date().getTime() || lp.getDeletetime() != null ||
                    (lp.getValidfrom().getTime() - (long) 2 * 60 * 60 * 1000) > new Date().getTime() || !deviceidlist.contains((long)lp.getDvcid())) {
                it.remove();
            }
        }
        for (LockPassword l : lockpasswordrecordlist) {
            phonenumberset.add(l.getPhonenumber());
        }
        if (phonenumberset.size() == 1) {
            //发密码短信咯
            for (LockPassword l : lockpasswordrecordlist){
                sendPasswordSms(l);
            }
        }else if(phonenumberset.size()>1){
            //找客服
            for (LockPassword l : lockpasswordrecordlist) {
                sendTipSms(l);
            }
        }
    }

    @Transactional
    public void sendPasswordSms(LockPassword lockpassword) {
        JSONObject json = new JSONObject();
        String templatecode = MjConfig.get("passwordSmsTemplateCode");
        long dvcid = lockpassword.getDvcid();
        Device device = dd.findById(dvcid);
        long bindRoomId = device.getBindRoomId();
        long districtId = device.getDistrictId();
        District district = districtDAO.findById(districtId);
        Room room = roomDAO.findById(bindRoomId);
        String password = AES.decrypt2Str(lockpassword.getPassword());

        if(district!=null && room!=null){
            json.put("hotal",district.getDistrictName());
            json.put("room",room.getRoomName());
            json.put("password",password);
            json.put("starttime",lockpassword.getValidfrom());
            json.put("endtime",lockpassword.getValidthrough());
        }

        //"您的${hotal}${room}开门密码为${password}，有效时间为${starttime}-${endtime}，欢迎入住。"
        MessageParser mp = new MessageParser(null ,templatecode , json);
        AliSmsSender sender = new AliSmsSender();
        sender.sendSMS("86", lockpassword.getPhonenumber(), mp);
    }

    @Transactional
    public void sendTipSms(LockPassword lockpassword){
        JSONObject json = new JSONObject();
        String templatecode = MjConfig.get("tipSmsTemplateCode");
        json.put("phonenumber",MjConfig.get("servicePhonenumber"));
        //"请联系客户服务部门获取开门密码，客服电话：${phonenumber}。"
        MessageParser mp = new MessageParser(null ,templatecode , json);
        AliSmsSender sender = new AliSmsSender();
        sender.sendSMS("86", lockpassword.getPhonenumber(), mp);
    }

}
