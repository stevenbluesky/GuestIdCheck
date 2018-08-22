package cn.com.isurpass.zufang.guestidcheck.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
public class MjConfig {
	private MjConfig() {
	}

	private static final Logger log = Logger.getLogger(MjConfig.class);
	private static final String fileName = "/ruimu.properties";
	private static final Properties properties = new Properties();
	static {
		try {
			InputStream inputStream = MjConfig.class.getResourceAsStream(fileName);
			properties.load(inputStream);
		} catch (IOException e) {
			log.error("加载配置文件失败！", e);
		} catch (Throwable e) {
			log.error("加载配置文件失败！", e);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
}
