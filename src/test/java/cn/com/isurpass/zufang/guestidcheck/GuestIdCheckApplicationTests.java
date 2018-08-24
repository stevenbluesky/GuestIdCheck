package cn.com.isurpass.zufang.guestidcheck;

import cn.com.isurpass.zufang.guestidcheck.service.LockPasswordService;
import cn.com.isurpass.zufang.guestidcheck.util.AES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestIdCheckApplicationTests {
	@Autowired
	private LockPasswordService lps;
	@Test
	public void contextLoads() {
	}

	@Test
	public void sendMessage(){
		lps.sendMessageToCustomer("李文翔");
	}
	@Test
	public void deAES(){
		System.out.println("解密后："+AES.decrypt2Str(""));
	}
}
