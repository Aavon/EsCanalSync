package com.alibaba.otter.handler;

import org.apache.log4j.Logger;
import org.slf4j.MDC;

import com.alibaba.otter.SingleClient;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.impl.ClusterCanalConnector;
import com.alibaba.otter.canal.protocol.Message;

/**
 * Abstrct Canal client
 * 
 * @author aaronzz
 *
 */
public abstract class AbstractClientHandler {
	
	protected final static Logger    logger = Logger.getLogger(AbstractClientHandler.class);
	
	private String         tabelReg;
	private CanalConnector connector;
	
	protected Thread             thread    = null;
	protected volatile boolean   running   = false;
	// 
	protected volatile boolean   waiting   = true;
	
	protected volatile boolean   debuging  = false;
	
	protected Thread.UncaughtExceptionHandler handler  = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread t, Throwable e) {
            logger.error("parse events has an error", e);
        }
    };
	
	public AbstractClientHandler(String tabelReg){
		this.tabelReg = tabelReg;
	}
	
	// Set default Canal Connection
	public void setConnector(CanalConnector connector) {
		this.connector = connector;
	}
	
	
	// Client start
	public void start() {
		thread = new Thread(new Runnable() {
            public void run() {
            	init();
            }
        });

        thread.setUncaughtExceptionHandler(handler);
        running = true;
        thread.start();
	}
	
	// Client stop
	public void stop() {
		if (!running) {
            return;
        }
        running = false;
        if (waiting) {
            if (connector instanceof ClusterCanalConnector) {
                ((ClusterCanalConnector) connector).setRetryTimes(-1);
            }
            thread.interrupt();
        }
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // ignore
            }
        }
		this.connector.disconnect();
	}
	
	// run
	protected void init() {
		int batchSize = 5 * 1024;
        while (running) {
            try {
                connector.connect();
                connector.subscribe(this.tabelReg);
                waiting = false;
                while (running) {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                         try {
                        	 Thread.sleep(1000);
                         } catch (InterruptedException e) {
                        	 // ignore
                         }
                    } else {
                        process(message, batchId, size);
                    }
                    connector.ack(batchId); // 提交确认
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }
            } catch (Exception e) {
                logger.error("process error!", e);
            } finally {
                connector.disconnect();
            }
        }
	}
	
	// enable/disable debug
	public void setDebug(boolean debuging) {
		this.debuging = debuging;
	}
	
	// process
	abstract protected void process(Message message, long batchId, int size);
	
	
}
