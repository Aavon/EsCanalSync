package com.alibaba.otter.index;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.es.Action;

public class ProjectInfo extends AbstractEsIndex {
	
	@TableField( name = "id" )
	private String projectId;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "logo" )
	private String logo;
	
	@TableField( name = "classification" )
	private String classification;
	
	@TableField( name = "status" )
	private String status;
	
	@TableField( name = "start_time" )
	private String startTime;
	
	@TableField( name = "end_time" )
	private String endTime;
	
	@TableField( name = "schedule_mode" )
	private String scheduleMode;
	
	@TableField( name = "is_template" )
	private String isTemplate;
	
	@TableField( name = "is_archive" )
	private String isArchive;
	
	@TableField( name = "visible" )
	private String visible;
	
	@TableField( name = "progress" )
	private String progress;
	
	@TableField( name = "mode" )
	private String mode;
	
	@TableField( name = "share_scope" )
	private String shareScope;
	
	@TableField( name = "clone_access" )
	private String cloneAccess;
	
	@TableField( name = "project_name" )
	private String name;
	
	@TableField( name = "introduction" )
	private String description;
	
	@TableField( name = "create_by" )
	private String createBy;
	
	@TableField( name = "update_by" )
	private String updateBy;
	
	@TableField( name = "created_at" )
	private String createdAt;
	
	@TableField( name = "updated_at" )
	private String updatedAt;
	
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return name;
	}

	public void setProjectName(String name ) {
		this.name = name;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getScheduleMode() {
		return scheduleMode;
	}

	public void setScheduleMode(String scheduleMode) {
		this.scheduleMode = scheduleMode;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(String isArchive) {
		this.isArchive = isArchive;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getShareScope() {
		return shareScope;
	}

	public void setShareScope(String shareScope) {
		this.shareScope = shareScope;
	}

	public String getCloneAccess() {
		return cloneAccess;
	}

	public void setCloneAccess(String cloneAccess) {
		this.cloneAccess = cloneAccess;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return this.projectId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "project";
	}
}
