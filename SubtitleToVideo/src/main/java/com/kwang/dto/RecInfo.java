package com.kwang.dto;

public class RecInfo {
	String beforesubtitle;
	String aftersubtitle;
	String userinfo;
	String asyncsubtitle;
	
	public RecInfo(String beforesubtitle, String aftersubtitle, String userinfo, String asyncsubtitle) {
		super();
		this.beforesubtitle = beforesubtitle;
		this.aftersubtitle = aftersubtitle;
		this.userinfo = userinfo;
		this.asyncsubtitle = asyncsubtitle;
	}
	public String getBeforesubtitle() {
		return beforesubtitle;
	}
	public void setBeforesubtitle(String beforesubtitle) {
		this.beforesubtitle = beforesubtitle;
	}
	public String getAftersubtitle() {
		return aftersubtitle;
	}
	public void setAftersubtitle(String aftersubtitle) {
		this.aftersubtitle = aftersubtitle;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public String getAsyncsubtitle() {
		return asyncsubtitle;
	}
	public void setAsyncsubtitle(String asyncsubtitle) {
		this.asyncsubtitle = asyncsubtitle;
	}
}
