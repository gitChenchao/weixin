package com.cc.weixin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.weixin.constant.SystemConstants;
import com.cc.weixin.pojo.WeiXinTlModel;
import com.cc.weixin.util.HttpClientUtil;
/**
 * 微信机器人
 * @author chao
 *
 */
@Controller
@RequestMapping("/tljqr")
public class WxJqrController {

	@ResponseBody
	@RequestMapping(value="/query/{strjson}",produces = "application/json; charset=utf-8")
	public String tljqr(HttpServletRequest request,HttpServletResponse response,@PathVariable("strjson") String json) throws IOException{
		JSONObject jsonObject = JSONObject.fromObject(json);
		WeiXinTlModel wxd = new WeiXinTlModel();
		wxd.setUserid(jsonObject.getString("userid"));
		wxd.setKey(SystemConstants.WEIXIN_TL_KEY);
		wxd.setInfo(jsonObject.getString("info"));
		wxd.setLoc(jsonObject.getString("loc"));
		String returnJson = HttpClientUtil.doPostJson(SystemConstants.WEIXIN_TL_ADDRESS, JSONObject.fromBean(wxd).toString());
		return returnJson;
	}
}
