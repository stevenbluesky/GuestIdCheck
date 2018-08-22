package cn.com.isurpass.zufang.guestidcheck.dao;


import cn.com.isurpass.zufang.guestidcheck.po.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDAO extends CrudRepository<Device,Integer> {

    List<Device> findByDistrictIdAndDeviceType(long districtId, int i);

    Device findById(long deviceid);
}
