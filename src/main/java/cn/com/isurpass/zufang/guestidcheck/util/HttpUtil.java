package cn.com.isurpass.zufang.guestidcheck.util;

import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);

	public static String httpGet(String url, JSONObject parameter, JSONObject header) {
		try {
			if (log.isInfoEnabled())
				log.info(parameter.toJSONString());

			StringBuffer sb = new StringBuffer();
			if (parameter != null)
				for (String key : parameter.keySet()) {
					if (sb.length() != 0)
						sb.append("&");
					sb.append(key).append("=").append(URLEncoder.encode(parameter.getString(key), "UTF-8"));
				}

			if (url.contains("?"))
				url += "&" + sb.toString();
			else
				url += "?" + sb.toString();

			HttpClient httpclient = createHttpClient(url);

			HttpGet httpget = new HttpGet(url);

			if (header != null)
				for (String key : header.keySet())
					httpget.addHeader(key, header.getString(key));

			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity, "UTF-8");
			log.info(rst);

			return rst;
		} catch (Throwable t) {
			log.error(t.getMessage(), t);
		}
		finally 
		{
			
		}
		return "";
	}

	public static String httprequest(String url, String body) {
		HttpClient httpclient = createHttpClient(url);
		log.info(url);
		log.info(body);

		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new StringEntity(body, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);

			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity, "UTF-8");
			log.info(rst);

			return rst;

		}
		catch(Throwable t) {
			log.error(t.getMessage(), t);
		}
		finally {
			
		}
		return "";
	}

	public static String httprequest(String url, Map pmap) {
		JSONObject json = JSON.parseObject(JSON.toJSONString(pmap));
		HttpClient httpclient = createHttpClient(url);
		log.info(url);

		try {
			if (log.isInfoEnabled())
				log.info(json.toJSONString());

			HttpPost httpost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (String key : json.keySet())
				nvps.add(new BasicNameValuePair(key, json.getString(key)));

			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);

			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity, "UTF-8");
			log.info(rst);

			return rst;
		} catch (Throwable t) {
			log.error(t.getMessage(), t);
		} finally {

		}
		return "";
	}
	
	public static HttpClient createHttpClient(String url)
	{
		if ( url.startsWith("https"))
		{
			return createSSLClientDefault();
			/*
			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();  
			ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();  
			registryBuilder.register("http", plainSF);  
			try {  
			    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
			    SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();  
			    LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
			    registryBuilder.register("https", sslSF);  
			} catch (KeyStoreException e) {  
			    throw new RuntimeException(e);  
			} catch (KeyManagementException e) {  
			    throw new RuntimeException(e);  
			} catch (NoSuchAlgorithmException e) {  
			    throw new RuntimeException(e);  
			}  
			Registry<ConnectionSocketFactory> registry = registryBuilder.build();  
			
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);  

			return HttpClientBuilder.create().setConnectionManager(connManager).build();
			*/
		}
		else 
			return HttpClientBuilder.create().build();
	}

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new AnyTrustStrategy()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public static void main(String arg[]) {
//		JSONObject json = new JSONObject();
//		json.put("code", "thirdparter_zufang");
//		json.put("password", "a4eed9981e2e4df0821376d395f69a6f119915");
//		String rst = HttpUtil.httprequest("https://iremote.isurpass.com.cn:2443/iremote/thirdpart/login", json);
//		
//		System.out.println(rst);
	}
}
