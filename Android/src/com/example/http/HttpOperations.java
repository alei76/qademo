package com.example.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpOperations {
	private static HttpClient httpClient = new DefaultHttpClient(); 
	public static void sendPost(String str) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://10.1.78.117:8080/qademo/sound";
		// POST��URL
		HttpPost httppost = new HttpPost(url);
		// ����HttpPost����
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// ����һ��NameValuePair���飬���ڴ洢�����͵Ĳ���
		params.add(new BasicNameValuePair("question", str));
		// ��Ӳ���
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// ���ñ���
		HttpResponse response = new DefaultHttpClient().execute(httppost);
		// ����Post,������һ��HttpResponse����
		if (response.getStatusLine().getStatusCode() == 200) {// ���״̬��Ϊ200,������������
			String result = EntityUtils.toString(response.getEntity());
			// �õ����ص��ַ���
			System.out.println(result);
			// ��ӡ���
		}
	}

	public static void sendGet(String str) throws Exception {
		
		String url = "http://10.1.78.117:8080/master/hello?str=1";
        try {  
            // Get����  
            HttpGet httpget = new HttpGet(url);  
            // ���ò���  
            
            httpget.setURI(new URI(httpget.getURI().toString()+str));  
            // ��������  
            HttpResponse httpresponse = httpClient.execute(httpget);  

           
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        }  
	}

	public static void main(String[] args) throws Exception {
		System.out.println("niaho");
//		sendPost("nihao");
	}
}
