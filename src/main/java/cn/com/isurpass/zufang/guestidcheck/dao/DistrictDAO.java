package cn.com.isurpass.zufang.guestidcheck.dao;

import cn.com.isurpass.zufang.guestidcheck.po.District;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liwenxiang
 * Date:2018/8/22
 * Time:14:53
 */
public interface DistrictDAO extends CrudRepository<District,Integer> {

    District findById(long id);
}
