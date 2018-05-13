package com.alibaba.otter.index;

public class UserInfo extends AbstractEsIndex {
	
	@TableField( name = "id" )
	private String userId;
	
	@TableField( name = "user_name" )
	private String userName;
	
	@TableField( name = "enable" )
	private String enable;
	
	@TableField( name = "avatar_url" )
	private String avatarUrl;
	
	@TableField( name = "user_type" )
	private String userType;
	
	@TableField( name = "sex" )
	private String sex;
	
	@TableField( name = "tel" )
	private String tel;
	
	@TableField( name = "email" )
	private String email;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "user";
	}

}
