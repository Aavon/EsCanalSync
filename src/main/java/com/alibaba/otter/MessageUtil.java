package com.alibaba.otter;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

public class MessageUtil {
	protected final static Logger    logger = Logger.getLogger(MessageUtil.class);
	
	public static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            logger.info(String.format("====>name[%s,%s] , eventType : %s",
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                	logger.info("--> before");
                    printColumn(rowData.getBeforeColumnsList());
                    logger.info("--> after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    public  static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            logger.info(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
        }
    }

}
