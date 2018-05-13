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
	private Action action;
	
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
	 * 用Entry初始化index对象
	 * 
	 * @param entry
	 */
	public void transfer(Entry entry) {
		// TODO Auto-generated method stub
		if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
            return;
        }
        
        RowChange rowChage = null;
        try {
            rowChage = RowChange.parseFrom(entry.getStoreValue());
        } catch (Exception e) {
            throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                    e);
        }

        EventType eventType = rowChage.getEventType();
        List<Column> cols = null;
        for (RowData rowData : rowChage.getRowDatasList()) {
            if (eventType == EventType.DELETE) {
                // add
            	this.action = Action.DELETE;
            	cols = rowData.getBeforeColumnsList();
            } else if (eventType == EventType.INSERT) {
                // insert
            	this.action = Action.UPSERT;
            	cols = rowData.getAfterColumnsList();
            } else {
            	// update
            	this.action = Action.UPDATE;
            	cols = rowData.getAfterColumnsList();
            }
            IndexUtil.fieldMatch(this, cols);
        }
	}

}
