package cn.com.isurpass.zufang.guestidcheck.dao;

import cn.com.isurpass.zufang.guestidcheck.po.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liwenxiang
 * Date:2018/8/22
 * Time:15:03
 */
public interface RoomDAO extends CrudRepository<Room,Integer> {

    Room findById(long id);
}
