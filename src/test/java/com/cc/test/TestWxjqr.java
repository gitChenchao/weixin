package com.cc.test;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.cc.weixin.constant.SystemConstants;
import com.cc.weixin.pojo.WeiXinTlModel;
import com.cc.weixin.pojo.WeiXinTlResultModel;
import com.cc.weixin.pojo.WxMovieSearchModel;
import com.cc.weixin.util.HttpClientUtil;
import com.cc.weixin.util.JsonUtils;

public class TestWxjqr {

	@Test
	public void test01(){
		WeiXinTlModel wxd = new WeiXinTlModel();
		wxd.setUserid("1231234");
		wxd.setKey(SystemConstants.WEIXIN_TL_KEY);
		wxd.setInfo("我想看新闻");
		wxd.setLoc("");
		//{"userid":"1231234","loc":"","key":"24b5b3cd84e648f9977db45d62a74543","info":"给我讲一个笑话吧"}
		System.out.println(JSONObject.fromObject(wxd).toString());
		String returnJson = HttpClientUtil.doPostJson(SystemConstants.WEIXIN_TL_ADDRESS, JSONObject.fromBean(wxd).toString());
		System.out.println(returnJson);
		/*JSONObject json = JSONObject.fromObject(returnJson);
		WeiXinTlResultModel r = (WeiXinTlResultModel)JSONObject.toBean(json, WeiXinTlResultModel.class);
		System.out.println(r.getList().size());*/
		WeiXinTlResultModel r = JsonUtils.jsonToPojo(returnJson, WeiXinTlResultModel.class);
		System.out.println(r.getUrl());
	}
}
