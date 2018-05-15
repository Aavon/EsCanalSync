package com.alibaba.otter.index;

public class ScheduleInfo extends AbstractEsIndex {
	
	@TableField( name = "id" )
	private String scheduleId;
	
	@TableField( name = "type" )
	private String srcType;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "project_id" )
	private String projectId;
	
	@TableField( name = "user_id" )
	private String userId;
	
	@TableField( name = "content" )
	private String content;
	
	@TableField( name = "startTime" )
	private String startTime;
	
	@TableField( name = "end_time" )
	private String endTime;
	
	@TableField( name = "location" )
	private String location;
	
	@TableField( name = "create_by" )
	private String createBy;
	
	@TableField( name = "update_by" )
	private String updateBy;
	
	@TableField( name = "created_at" )
	private String createdAt;
	
	@TableField( name = "updated_at" )
	private String updatedAt;
	
	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
		return this.scheduleId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "schedule";
	}

}
