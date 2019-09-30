package com.jt.util;
/**
 * 该类是封装HttpClient的工具api
 * @author BoYuan
 *
 */

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
@Component
public class HttpClientService {
	@Autowired
	private CloseableHttpClient httpClient ;
	@Autowired
	private RequestConfig requestConfig;
	/**
	 * 用户调用工具方法,通过工具方法返回(服务器数据)json串数据给用户
	 * 判断用户是否传参,if
	 * else
	 * @param url
	 * @return
	 * @throws Exception
	 * args:利用map集合封装数据
	 * charset:定义字符集编码
	 */
	public String doGet(String url,Map<String,String> params,String charset ) {
		if(StringUtils.isEmpty(charset)) 
			charset = "UTF-8";
		if(params != null){
			for(String str : params.keySet()) {
				String value =params.get(str);
				url += str +"="+value+"&"; 
			}
			//去除最后一个字符
			url = url.substring(0,url.length()-1);
		}
		System.out.println(url);
		HttpGet httpGet = new HttpGet(url); 
		httpGet.setConfig(requestConfig); // 设置请求的超时时间
		CloseableHttpResponse response = null;
		String result = null;
		try {
			response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200)
				result = EntityUtils.toString(response.getEntity(),charset);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 待完成...............................................
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public String doPost(String url ,Map<String,String> params,String charset ) {
		return doGet(url,null,null);
	}
	public String doGet(String url ) {
		return doGet(url,null,null);
	}
	public String doPost(String url ,Map<String,String> params ) {
		return doGet(url,params,null);
	}
}
