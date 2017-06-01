package com.cc.weixin.pojo;
/**
 * @author chao
 *
 */
public class WeiXinTlModel {
	public String key;
	public String info;//请求内容
	public String loc;//地理信息
	public String userid;//唯一标志id
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
