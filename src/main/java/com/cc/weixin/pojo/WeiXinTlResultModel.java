package com.cc.weixin.pojo;

import java.util.List;

public class WeiXinTlResultModel {
	public String code;
	public String text;
	public String url;
	public List<WxMovieSearchModel> list;
	public WeiXinTlResultModel(){};
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<WxMovieSearchModel> getList() {
		return list;
	}
	public void setList(List<WxMovieSearchModel> list) {
		this.list = list;
	}
	
}
