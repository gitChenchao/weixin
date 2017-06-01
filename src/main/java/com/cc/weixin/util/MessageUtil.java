package com.cc.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cc.weixin.constant.SystemConstants;
import com.cc.weixin.pojo.ImageItem;
import com.cc.weixin.pojo.ImagesAndTextMessage;
import com.cc.weixin.pojo.TextMessage;
import com.cc.weixin.pojo.WeiXinTlModel;
import com.thoughtworks.xstream.XStream;

/**
 * 微信文本信息格式转换
 * @author chao
 *
 */
public class MessageUtil {
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="click";
	public static final String MESSAGE_VIEW="view";
	
	/**
	 * xml转换map集合
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest req) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		InputStream stream = req.getInputStream();
		Document doc = saxReader.read(stream);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		stream.close();
		return map;
	}
	/**
	 * 文本对象转换xml
	 * @param testMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream stream = new XStream();
		stream.alias("xml", textMessage.getClass());
		return stream.toXML(textMessage);
	}
	/**
	 * 图文对象转xml
	 * @return
	 */
	public static String imagesAndTextToXml(ImagesAndTextMessage imagesAndTextMessage){
		XStream stream = new XStream();
		stream.alias("xml", imagesAndTextMessage.getClass());
		stream.alias("item", ImageItem.class);
		return stream.toXML(imagesAndTextMessage);
	}
	/**
	 * 添加机器人
	 */
	public static String jqr(String info,String userid,String loc){
		WeiXinTlModel wxd = new WeiXinTlModel();
		wxd.setUserid(userid);
		wxd.setKey(SystemConstants.WEIXIN_TL_KEY);
		wxd.setInfo(info);
		wxd.setLoc(loc);
		String returnJson = HttpClientUtil.doPostJson(SystemConstants.WEIXIN_TL_ADDRESS, JSONObject.fromBean(wxd).toString());
		return returnJson;
	}
}
