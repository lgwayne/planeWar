package com.wayne.airplane.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable, Comparable<User> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String userId; // 用户编号
	private String nickName; // 用户昵称
	private String password; // 用户密码
	private Date registTime; // 用户注册时间
	private Date createShoreRecordTime; // 用户最高分纪录创建时间
	private Integer score; // 用户得分

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "\n用户信息： [用户编号=" + userId + ", " + "用户账号=" + nickName + ", " + "密码=" + password + "\n, " + "注册时间="
				+ sdf.format(registTime) + ", " + "创建时间=" + sdf.format(createShoreRecordTime) + ", " + "得分=" + score + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String nickName, String password) {
		this.nickName = nickName;
		this.password = password;
	}

	public User(String userId, String nickName, String password, Date registTime, Date createShoreRecordTime,
				Integer score) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.registTime = registTime;
		this.createShoreRecordTime = createShoreRecordTime;
		this.score = score;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getCreateShoreRecordTime() {
		return createShoreRecordTime;
	}

	public void setCreateShoreRecordTime(Date createShoreRecordTime) {

		this.createShoreRecordTime = createShoreRecordTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setShore(Integer score) {
		this.score = score;
	}

	@Override
	public int compareTo(User u) {
		if (u.score > this.score) {
			return 1;
		} else if (u.score == this.score) {
			return 0;
		} else {
			return -1;
		}
	}

}
