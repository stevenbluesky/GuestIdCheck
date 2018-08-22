package cn.com.isurpass.zufang.guestidcheck.dao;

import cn.com.isurpass.zufang.guestidcheck.po.LockPassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/8/22
 * Time:11:11
 */

@Repository
public interface LockPasswordDAO extends CrudRepository<LockPassword,Integer> {

    List<LockPassword> findByUsernameAndUsertype(String name, int i);
}
