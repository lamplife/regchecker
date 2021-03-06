package com.jisucloud.clawler.regagent.service.impl.shop;

import com.deep007.goniub.selenium.mitm.AjaxHook;
import com.deep007.goniub.selenium.mitm.ChromeAjaxHookDriver;
import com.deep007.goniub.selenium.mitm.HookTracker;

import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

import java.util.Map;


@Slf4j
@PapaSpiderConfig(
		home = "dangdang.com", 
		message = "当当是知名的综合性网上购物商城，由国内著名出版机构科文公司、美国老虎基金、美国IDG集团、卢森堡剑桥集团、亚洲创业投资基金（原名软银中国创业基金）共同投资成立。", 
		platform = "dangdang", 
		platformName = "当当网", 
		tags = { "电商" , "网上书城" }, 
		testTelephones = { "18210538577", "18212345678" })
public class DangDangWangSpider extends PapaSpider implements AjaxHook{
	
	private ChromeAjaxHookDriver chromeDriver;
	private boolean check = false;
	
	public boolean checkTelephone(String account) {
		try {
			chromeDriver = ChromeAjaxHookDriver.newChromeInstance(true, true);
			chromeDriver.get("https://login.dangdang.com/register.php?returnurl=http://www.dangdang.com/");
			chromeDriver.addAjaxHook(this);
			chromeDriver.findElementById("txt_username").sendKeys(account);
			chromeDriver.findElementById("txt_password").click();smartSleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (chromeDriver != null) {
				chromeDriver.quit();
			}
		}
		return check;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

	@Override
	public HookTracker getHookTracker() {
		return HookTracker.builder().addUrl("mobile_checker.php").isPost().build();
	}

	@Override
	public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
		check = contents.getTextContents().contains("true");
	}

}
