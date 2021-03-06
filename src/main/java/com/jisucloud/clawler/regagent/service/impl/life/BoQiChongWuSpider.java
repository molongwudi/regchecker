package com.jisucloud.clawler.regagent.service.impl.life;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;


@Slf4j
@PapaSpiderConfig(
		home = "boqii.com", 
		message = "波奇宠物网是属于宠物爱好者的综合型网站.这里是宠物猫，宠物狗等宠物爱好者们的集中地.也是包含宠物商城和宠物百科的专业网站.选择波奇宠物网,健康宠物生活从波奇开始.", 
		platform = "boqii", 
		platformName = "波奇宠物网", 
		tags = { "宠物" }, 
		testTelephones = { "13771025665", "18212345678" })
public class BoQiChongWuSpider extends PapaSpider {


	public boolean checkTelephone(String account) {
		if (account.length() != 11) {
			return false;
		}
		try {
			String url = "http://www.boqii.com/site/User/ajaxCheckMobile";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("mobile", account)
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Referer", "http://www.boqii.com/user/register")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request)
					.execute();
			return response.body().string().contains("exists");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
