package com.cc.weixin.pojo;

import java.util.List;

/**
 * 微信-回复图文消息
 * @author chao
 *
 */
public class ImagesAndTextMessage {
	public String ToUserName;//接收方帐号（收到的OpenID）
	public String FromUserName;//开发者微信号
	public Long CreateTime;//消息创建时间 （整型）
	public String MsgType;//news
	public String ArticleCount;//图文消息个数，限制为8条以内
	public List<ImageItem> Articles;//图片集合
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<ImageItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<ImageItem> articles) {
		Articles = articles;
	}
}
