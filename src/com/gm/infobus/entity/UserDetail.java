package com.gm.infobus.entity;

import com.gm.infobus.repository.base.AbstractDO;

public class UserDetail extends AbstractDO{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2562869393144582910L;
	private String userName;// 用户名
	private String birth;// 出生年月日
	private String idCard;// 身份证
	private String age;
	private String location;// 地点
	private String phone;// 手机号码
	private String nickName;// 昵称
	private String photo;// 用户照片
	private String plate;// 车牌号
	private String profession;// 职业
	private String sex;// 性别：0-男 1-女
	private String signature;// 个人签名
	private String email;
	private String curVoiceUrl;//本次发送语音url

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getBirth() {
		return birth;
	}

	public final void setBirth(String birth) {
		this.birth = birth;
	}

	public final String getIdCard() {
		return idCard;
	}

	public final void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public final String getLocation() {
		return location;
	}

	public final void setLocation(String location) {
		this.location = location;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final String getNickName() {
		return nickName;
	}

	public final void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public final String getPhoto() {
		return photo;
	}

	public final void setPhoto(String photo) {
		this.photo = photo;
	}

	public final String getPlate() {
		return plate;
	}

	public final void setPlate(String plate) {
		this.plate = plate;
	}

	public final String getProfession() {
		return profession;
	}

	public final void setProfession(String profession) {
		this.profession = profession;
	}

	public final String getSex() {
		return sex;
	}

	public final void setSex(String sex) {
		this.sex = sex;
	}

	public final String getSignature() {
		return signature;
	}

	public final void setSignature(String signature) {
		this.signature = signature;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getCurVoiceUrl() {
		return curVoiceUrl;
	}

	public final void setCurVoiceUrl(String curVoiceUrl) {
		this.curVoiceUrl = curVoiceUrl;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
