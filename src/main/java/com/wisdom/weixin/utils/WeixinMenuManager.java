package com.wisdom.weixin.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinMenuManager {

	private static final Logger logger = LoggerFactory
			.getLogger(WeixinMenuManager.class);

	public static void createMenu(String params, String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ accessToken;
		sendRequest(params, url);
	}

	public static void deleteMenu(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
				+ accessToken;
		sendRequest("", url);
	}

	private static void sendRequest(String params, String url) {
		StringBuffer bufferRes = new StringBuffer();
		try {
			URL realUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			HttpURLConnection.setFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
			conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
			conn.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream());
			out.write(params);
			out.flush();
			out.close();
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String valueString = null;
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			logger.debug(bufferRes.toString());
			System.out.println(bufferRes.toString());
			in.close();
			if (conn != null) {
				conn.disconnect();
			}
		} catch (Exception e) {
			logger.warn(e.toString());
		}
	}

	public static void main(String[] args) {
		//String s = "{\"button\":[{\"name\":\"报销\",\"sub_button\":[{\"type\":\"view\",\"name\":\"上传发票\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309df15b6ddc5371&redirect_uri=http%3a%2f%2f121.41.20.251%2fgetOpenIdRedirect%3fview%3dupload_bill.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"},{\"type\":\"view\",\"name\":\"我的报销单\",\"url\":\"http://121.41.20.251/my_bills\"},{\"type\":\"view\",\"name\":\"收件箱\",\"url\":\"http://121.41.20.251/my_inbox\"}]},{\"name\":\"看报表\",\"sub_button\":[{\"type\":\"view\",\"name\":\"资产管理分析\",\"url\":\"http://121.41.20.251/assets_analyse\"},{\"type\":\"view\",\"name\":\"产品销量分析\",\"url\":\"http://121.41.20.251/sales_analyse\"},{\"type\":\"view\",\"name\":\"费用分析\",\"url\":\"http://121.41.20.251/expenses_analyse\"},{\"type\":\"view\",\"name\":\"更多报表\",\"url\":\"http://121.41.20.251/more_reports\"}]},{\"name\":\"设置\",\"sub_button\":[{\"type\":\"view\",\"name\":\"账号设置\",\"url\":\"http://121.41.20.251/settings\"},{\"type\":\"view\",\"name\":\"绑定公司\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309df15b6ddc5371&redirect_uri=http%3a%2f%2f121.41.20.251%2fgetOpenIdRedirect%3fview%3dbind_company.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"},{\"type\":\"view\",\"name\":\"帮助和教程\",\"url\":\"http://121.41.20.251/help\"}]}]}";
		// String s =
		// "{\"button\":[{\"name\":\"报销\",\"sub_button\":[{\"type\":\"view\",\"name\":\"上传发票\",\"url\":\"http://121.41.20.251/upload_bill\"},{\"type\":\"view\",\"name\":\"我的报销单\",\"url\":\"http://121.41.20.251/my_bills\"},{\"type\":\"view\",\"name\":\"收件箱\",\"url\":\"http://121.41.20.251/my_inbox\"}]},{\"name\":\"看报表\",\"sub_button\":[{\"type\":\"view\",\"name\":\"资产管理分析\",\"url\":\"http://121.41.20.251/assets_analyse\"},{\"type\":\"view\",\"name\":\"产品销量分析\",\"url\":\"http://121.41.20.251/sales_analyse\"},{\"type\":\"view\",\"name\":\"费用分析\",\"url\":\"http://121.41.20.251/expenses_analyse\"},{\"type\":\"view\",\"name\":\"更多报表\",\"url\":\"http://121.41.20.251/more_reports\"}]},{\"name\":\"设置\",\"sub_button\":[{\"type\":\"view\",\"name\":\"账号设置\",\"url\":\"http://121.41.20.251/settings\"},{\"type\":\"view\",\"name\":\"绑定公司\",\"url\":\"http://121.41.20.251/bind_company\"},{\"type\":\"view\",\"name\":\"帮助和教程\",\"url\":\"http://121.41.20.251/help\"}]}]}";
		String s = "{\"button\":[{\"type\":\"view\",\"name\":\"发票\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309df15b6ddc5371&redirect_uri=http%3a%2f%2f121.41.20.251%2fgetOpenIdRedirect%3fview%3dexpense_account.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"},{\"type\":\"view\",\"name\":\"资产管理分析\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309df15b6ddc5371&redirect_uri=http%3a%2f%2f121.41.20.251%2fgetOpenIdRedirect%3fview%3dweixin_empty.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"},{\"type\":\"view\",\"name\":\"设置\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309df15b6ddc5371&redirect_uri=http%3a%2f%2f121.41.20.251%2fgetOpenIdRedirect%3fview%3dsettings.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}]}";
		String accessToken = "G_P4k1pCBLS2ZEVw8t0L8tqKLmy1uodvPacQyhNM_VS7jYIpqE2iKuma__I9WJs0KQIWiPBoQ-TcN0Yah2lvU0odrNjhoHe4xFoOVNDV_W8";
		createMenu(s, accessToken);
		//deleteMenu(accessToken);
	}

}
