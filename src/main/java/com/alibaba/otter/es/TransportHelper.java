package com.alibaba.otter.es;

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

import com.alibaba.otter.index.AbstractEsIndex;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransportHelper {
	
	protected final static long DEFAULT_TIMEOUT = 3;
	
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
					bulkProcessor.add(new DeleteRequest(
						index.getIndexName(),
						index.getTypeName(),
						index.getId()	
					));
					break;
				case UPDATE:
					bulkProcessor.add(new IndexRequest(
							index.getIndexName(),
							index.getTypeName(),
							index.getId()
							).source(docRaw));
					break;
				case UPSERT:
					bulkProcessor.add(new IndexRequest(
							index.getIndexName(),
							index.getTypeName(),
							index.getId()
							).source(docRaw));;
					break;
				}
			}
	}
	
}
