package com.alibaba.otter.index;

import java.util.List;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.es.Action;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractEsIndex {
	
	@JsonIgnore
	protected Action action;
	
	/**
	 * 获取_id
	 * 
	 * @return
	 */
	public abstract String getId();
	
	/**
	 * 获取Action
	 * 
	 * @return
	 */
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * 获取index名
	 * 
	 * @return
	 */
	@JsonIgnore
	public abstract String getIndexName();
	
	/**
	 * 获取Type名
	 * 
	 * @return
	 */
	@JsonIgnore
	public abstract String getTypeName();
	
	
	/**
	 * 获取父类型类型ID
	 * @return
	 */
	@JsonIgnore
	public String getParentId() {
		return "";
	}
}
