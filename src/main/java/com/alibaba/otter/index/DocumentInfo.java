package com.alibaba.otter.index;

/**
 * 文档信息映射对象
 * 
 * @author aaronzz
 *
 */
public class DocumentInfo extends AbstractEsIndex {
	
	@TableField( name = "id" )
	private String documentId;
	
	@TableField( name = "organization_id" )
	private String organizationId;
	
	@TableField( name = "project_id" )
	private String projectId;
	
	@TableField( name = "user_id" )
	private String userId;
	
	@TableField( name = "lib_id" )
	private String libId;
	
	@TableField( name = "folder_id" )
	private String folderId;
	
	@TableField( name = "name" )
	private String name;
	
	@TableField( name = "content_type" )
	private String contentType;
	
	@TableField( name = "url" )
	private String url;
	
	@TableField( name = "size" )
	private String size;
	
	@TableField( name = "category" )
	private String category;
	
	@TableField( name = "last_visit_time" )
	private String lastVisitTime;
	
	@TableField( name = "create_by" )
	private String createBy;
	
	@TableField( name = "update_by" )
	private String updateBy;
	
	@TableField( name = "created_at" )
	private String createdAt;
	
	@TableField( name = "updated_at" )
	private String updatedAt;
	
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getLibId() {
		return libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
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
		return this.documentId;
	}

	@Override
	public String getIndexName() {
		// TODO Auto-generated method stub
		return "e0_search_category";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "document";
	}

}
