package com.alibaba.otter.index;

import java.util.List;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.es.Action;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 组织成员关系同步
 * 
 * @author aaronzz
 *
 */

public class UserMembers extends AbstractEsIndex {
	
	@TableField( name = "id" )
	private String memberId;
	
	@TableField( name = "user_id" )
	private String userId;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "status" )
	private String status;
	
	@TableField( name = "role_id" )
	private String roleId;
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.memberId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "user_member";
	}
	
	@Override
	@JsonIgnore
	public String getParentId() {
		return this.userId;
	}

}
