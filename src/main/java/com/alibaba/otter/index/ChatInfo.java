package com.alibaba.otter.index;

/**
 * 讨论组同步映射对象
 * 
 * @author aaronzz
 *
 */
public class ChatInfo extends AbstractEsIndex {

	@TableField( name = "id" )
	private String chatId;
	
	@TableField( name = "name" )
	private String name;
	
	@TableField( name = "uid" )
	private String uid;
	
	@TableField( name = "enable" )
	private String enable;
	
	@TableField( name = "occu_type" )
	private String occuType;
	
	@TableField( name = "occu_id" )
	private String occuId;
	
	@TableField( name = "description" )
	private String description;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "create_by" )
	private String createBy;
	
	@TableField( name = "update_by" )
	private String updateBy;
	
	@TableField( name = "created_at" )
	private String createdAt;
	
	@TableField( name = "updated_at" )
	private String updatedAt;
	
	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOccuType() {
		return occuType;
	}

	public void setOccuType(String occuType) {
		this.occuType = occuType;
	}

	public String getOccuId() {
		return occuId;
	}

	public void setOccuId(String occuId) {
		this.occuId = occuId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.chatId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "chat";
	}

}
