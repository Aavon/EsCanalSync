package com.alibaba.otter.es;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.index.AbstractEsIndex;
import com.alibaba.otter.index.IndexUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;

public class TransportHelper {
	
	protected final static long DEFAULT_TIMEOUT = 3;
	
	// 通用bulk请求
	public static void SyncIndex(List<AbstractEsIndex> indexs,BulkProcessor bulkProcessor) throws JsonProcessingException, InterruptedException, ExecutionException {
			if (bulkProcessor == null || indexs.size() == 0 ) {
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			for( AbstractEsIndex index : indexs ) {
				byte[] docRaw = null;
				docRaw = mapper.writeValueAsBytes(index);
				switch( index.getAction() ) {
				case DELETE:
					DeleteRequest iq0 = new DeleteRequest(
						index.getIndexName(),
						index.getTypeName(),
						index.getId()	
					);
					if (index.getParentId() != null && index.getParentId().length() > 0 ) {
						iq0.parent(index.getParentId());
					}
					bulkProcessor.add(iq0);
					break;
				case UPDATE:
					IndexRequest iq1 = new IndexRequest(
							index.getIndexName(),
							index.getTypeName(),
							index.getId())
							.source(docRaw);
					if (index.getParentId() != null && index.getParentId().length() > 0 ) {
						iq1.parent(index.getParentId());
					}
					bulkProcessor.add(iq1);
					break;
				case UPSERT:
					IndexRequest iq2 = new IndexRequest(
							index.getIndexName(),
							index.getTypeName(),
							index.getId())
							.source(docRaw);
					if (index.getParentId() != null && index.getParentId().length() > 0 ) {
						iq2.parent(index.getParentId());
					}
					bulkProcessor.add(iq2);
					break;
				}
			}
	}
	
	/**
	 * 用Entry初始化index对象数组
	 * 
	 * @param entry
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List<AbstractEsIndex> transfer(Entry entry, Class<? extends AbstractEsIndex> t) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
            return null;
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
        List<AbstractEsIndex> indexs = new ArrayList<>();
        for (RowData rowData : rowChage.getRowDatasList()) {
        	AbstractEsIndex index = t.newInstance();
            if (eventType == EventType.DELETE) {
                // add
            	index.setAction(Action.DELETE);
            	cols = rowData.getBeforeColumnsList();
            } else if (eventType == EventType.INSERT) {
                // insert
            	index.setAction(Action.UPSERT);
            	cols = rowData.getAfterColumnsList();
            } else {
            	// update
            	index.setAction(Action.UPDATE);
            	cols = rowData.getAfterColumnsList();
            }
            IndexUtil.fieldMatch(index, cols);
            indexs.add(index);
        }
        return indexs;
	}
	
}
