package cn.com.isurpass.zufang.guestidcheck.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liwenxiang
 * Date:2018/8/23
 * Time:19:35
 */
@Component
@Order(value = 1)   //执行顺序控制
public class DoIfProjectRun implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("项目成功启动。。。");
    }
}
