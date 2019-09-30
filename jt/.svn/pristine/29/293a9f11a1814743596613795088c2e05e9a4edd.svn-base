package com.jt;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.util.HttpClientService;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHttpClient {
	@Autowired
	private CloseableHttpClient httpClient ;
	@Autowired
	private HttpClientService httpService;
	
	@Test
	public void testUtil() {
		String url = "https://pro.m.jd.com/mall/active/hAE5L5pxPWs7hABRt98Qds6FZ5K/index.html";
		String result = httpService.doGet(url);
		System.out.println(result);
	}
	
	
	
	@Test
	public void testHttp() throws ParseException, IOException {
		String url = "https://pro.m.jd.com/mall/active/hAE5L5pxPWs7hABRt98Qds6FZ5K/index.html";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if(response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}
	}
	/**
	 * 测试Httpclient
	 * 步骤:
	 * 1.实例化请求对象
	 * 2.确定url地址
	 * 3.定义请求对象
	 * 4.发起请求,获取响应结果
	 * 5.(200/400客户端向服务器传递参数异常/404/406服务器返回的参数异常/500后台服务器异常)
	 * 6.校验服务器信息
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testHttpClient() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault(); // 建造者模式
		String url = "https://pro.m.jd.com/mall/active/hAE5L5pxPWs7hABRt98Qds6FZ5K/index.html";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);
		if(response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}
	}
	
	
}
