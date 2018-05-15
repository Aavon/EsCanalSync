package com.alibaba.otter.handler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.es.TransportHelper;
import com.alibaba.otter.exception.ClientException;
import com.alibaba.otter.index.AbstractEsIndex;
import com.alibaba.otter.index.ChatInfo;
import com.alibaba.otter.index.DocumentInfo;
import com.alibaba.otter.index.ProjectInfo;
import com.alibaba.otter.index.ScheduleInfo;
import com.alibaba.otter.index.TaskInfo;
import com.alibaba.otter.index.UserInfo;
import com.alibaba.otter.index.UserMembers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.otter.MessageUtil;

/**
 * Elastic sync handler
 * 
 * @author aaronzz
 *
 */
public class EsClientHandler extends AbstractClientHandler {

	protected final static Logger    logger = Logger.getLogger(EsClientHandler.class);
	
	TransportClient client = null;
	
	private BulkProcessor bulkProcessor = null;
	
	public EsClientHandler(String tabelReg,String[] clusterIps, int esPort, Settings settings,BulkProcConfig bulkConfig ) throws Exception {
		super(tabelReg);
		client = new PreBuiltTransportClient(settings);
		if (clusterIps.length > 0) {
			for(String sip : clusterIps) {				
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(sip), esPort));			
			}
		} else {
			throw new ClientException("ip is empty");
		}
		
		this.bulkProcessor = BulkProcessor.builder(
                this.client,
                new BulkProcessor.Listener() {

                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {
                        logger.info(String.format("---尝试插入{%s}条数据---", request.numberOfActions()));
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request, BulkResponse response) {
                        logger.info(String.format("---尝试插入{%s}条数据成功---", request.numberOfActions()));
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request, Throwable failure) {
                        logger.error("[es错误]---尝试插入数据失败---", failure);
                    }

        })
        .setBulkActions(bulkConfig.bulkAction)
        .setBulkSize(new ByteSizeValue(bulkConfig.bulkSize, ByteSizeUnit.MB))
        .setFlushInterval(TimeValue.timeValueSeconds(bulkConfig.interval))
        .setConcurrentRequests(bulkConfig.conRequest)
        .build();
	}

	@Override
	protected void process(Message message, long batchId, int size) {
		logger.info(String.format("## batch_id[%s] , size : %s",batchId,size));
		List<Entry> entries = message.getEntries();
		List<AbstractEsIndex> indexs = new ArrayList<AbstractEsIndex>();
		if (debuging) {			
			MessageUtil.printEntry(entries);
		}
		// 处理得到修改的记录
		for( Entry entry : entries) {
			String fullName = entry.getHeader().getSchemaName() +"."+ entry.getHeader().getTableName();
			List<AbstractEsIndex> newIndexs = null;
			switch(fullName) {
			case "zcloud_task.task_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,TaskInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			case "zcloud_project.project_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,ProjectInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			case "zcloud_document.document_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,DocumentInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			case "zcloud_imessage.multi_chats":
				try {
					newIndexs = TransportHelper.transfer(entry,ChatInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;	
			case "zcloud_schedule.schedule_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,ScheduleInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;	
			case "zcloud_user.user_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,UserInfo.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;	
			case "zcloud_organization.member_infos":
				try {
					newIndexs = TransportHelper.transfer(entry,UserMembers.class);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			}
			if (newIndexs != null && newIndexs.size() > 0 ) {
				indexs.addAll(newIndexs);
			}
		}
		// api CURD
		try {
			TransportHelper.SyncIndex(indexs, bulkProcessor);
		} catch (JsonProcessingException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
