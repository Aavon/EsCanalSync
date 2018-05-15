package com.alibaba.otter.index;

import java.util.List;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.es.Action;

public class TaskInfo extends AbstractEsIndex{
	
	// 建议使用字符串类型字段
	
	@TableField( name = "id" )
	private String   taskId;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "project_id" )
	private String projectId;
	
	@TableField( name = "type" )
	private String srcType;
	
	@TableField( name = "mode" )
	private String mode;
	
	@TableField( name = "status" )
	private String status;
	
	@TableField( name = "priority" )
	private String priority;
	
	@TableField( name = "progress" )
	private String progress;
	
	@TableField( name = "plan_duration" )
	private String planDuration;
	
	@TableField( name = "plan_hour" )
	private String planHour;
	
	@TableField( name = "color" )
	private String color;
	
	@TableField( name = "gantt_task_id" )
	private String ganttTaskId;
	
	@TableField( name = "name" )
	private String name;
	
	@TableField( name = "start_time" )
	private String startTime;
	
	@TableField( name = "end_time" )
	private String endTime;
	
	@TableField( name = "description" )
	private String description;
	
	@TableField( name = "create_by" )
	private String createBy;
	
	@TableField( name = "update_by" )
	private String updateBy;
	
	@TableField( name = "created_at" )
	private String createdAt;
	
	@TableField( name = "updated_at" )
	private String updatedAt;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getPlanDuration() {
		return planDuration;
	}

	public void setPlanDuration(String planDuration) {
		this.planDuration = planDuration;
	}

	public String getPlanHour() {
		return planHour;
	}

	public void setPlanHour(String planHour) {
		this.planHour = planHour;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGanttTaskId() {
		return ganttTaskId;
	}

	public void setGanttTaskId(String ganttTaskId) {
		this.ganttTaskId = ganttTaskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return taskId;
	}
	
	@Override
	public String getIndexName() {
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "task";
	}
}
