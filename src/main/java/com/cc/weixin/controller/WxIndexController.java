package com.cc.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cc.weixin.pojo.ImageItem;
import com.cc.weixin.pojo.ImagesAndTextMessage;
import com.cc.weixin.pojo.TextMessage;
import com.cc.weixin.pojo.WeiXinTlResultModel;
import com.cc.weixin.util.CheckUtil;
import com.cc.weixin.util.JsonUtils;
import com.cc.weixin.util.MessageUtil;

@Controller
@RequestMapping("/wx")
public class WxIndexController {

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public void wxIndexdoGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String signature=request.getParameter("signature");
		String timestamp=request.getParameter("timestamp");
		String nonce=request.getParameter("nonce");
		String echostr=request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		// 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public void wxIndexdoPost(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String event = map.get("Event");
			String fhMessage="";
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				//调用微信机器人返回信息
				String tulingMessage = null;
				String result = MessageUtil.jqr(content, fromUserName.replaceAll("-", "").replaceAll("_", "").substring(0,15), null);
				WeiXinTlResultModel wxModel = JsonUtils.jsonToPojo(result, WeiXinTlResultModel.class);
				System.out.println(wxModel.getCode());
				if(wxModel.getCode().equals("100000")||wxModel.getCode().equals("200000")){//文本类和连接类 文本消息
					if(wxModel.getCode().equals("100000")){
						tulingMessage=wxModel.getText();
					}else if(wxModel.getCode().equals("200000")){
						tulingMessage=wxModel.getText()+wxModel.getUrl();
					}
					TextMessage message = new TextMessage();
					message.setFromUserName(toUserName);
					message.setToUserName(fromUserName);
					message.setMsgType("text");
					message.setCreateTime(new Date().getTime());
					//message.setContent("您发送的内容是："+content);
					message.setContent(tulingMessage);
					fhMessage=MessageUtil.textMessageToXml(message);
				}else if(wxModel.getCode().equals("308000")){//集合类 图文消息
					tulingMessage = wxModel.getList().get(0).getIcon()+wxModel.getList().get(0).getName();
					ImagesAndTextMessage itMessage = new ImagesAndTextMessage();
					itMessage.setToUserName(fromUserName);
					itMessage.setFromUserName(toUserName);
					itMessage.setCreateTime(new Date().getTime());
					itMessage.setMsgType("news");
					itMessage.setArticleCount("5");
					List<ImageItem> item = new ArrayList<ImageItem>();
					ImageItem ii = null;
					for (int i = 0; i < 5; i++) {
						ii = new ImageItem();
						ii.setDescription(wxModel.getList().get(i).getInfo());
						ii.setTitle(wxModel.getList().get(i).getName());
						ii.setPicUrl(wxModel.getList().get(i).getIcon());
						ii.setUrl(wxModel.getList().get(i).getDetailurl());
						item.add(ii);
					}
					itMessage.setArticles(item);
					fhMessage = MessageUtil.imagesAndTextToXml(itMessage);
					System.out.println(fhMessage);
				}
			//关注/取消关注事件
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				//点击关注
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(event)){
					TextMessage message = new TextMessage();
					message.setFromUserName(toUserName);
					message.setToUserName(fromUserName);
					message.setMsgType("text");
					message.setCreateTime(new Date().getTime());
					message.setContent("感谢您关注骑猪上树，我们会为您带来更好的平台！");
					fhMessage=MessageUtil.textMessageToXml(message);
				//取消关注
				}else if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(event)){
					TextMessage message = new TextMessage();
					message.setFromUserName(toUserName);
					message.setToUserName(fromUserName);
					message.setMsgType("text");
					message.setCreateTime(new Date().getTime());
					message.setContent("感谢您的关注，骑猪上树会让你再回来的！");
					fhMessage=MessageUtil.textMessageToXml(message);
				}
			}
			out.print(fhMessage);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
