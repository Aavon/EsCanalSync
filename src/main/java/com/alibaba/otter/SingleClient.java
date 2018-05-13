package com.alibaba.otter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.elasticsearch.common.settings.Settings;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.handler.AbstractClientHandler;
import com.alibaba.otter.handler.BulkProcConfig;
import com.alibaba.otter.handler.EsClientHandler;

/**
 * 单机模式的测试例子
 * 
 * @author AaronZz
 * @version 
 */
public class SingleClient {
	
	protected final static Logger    logger = Logger.getLogger(SingleClient.class);
	
	protected final static String    DEF_CONFIG_FILE = "/client.properties";
	
    public static void main(String args[]) throws Exception {
    	
    	Properties prop = null;
    	try {
    		prop = loadConfig(DEF_CONFIG_FILE);
    	} catch (IOException e) {
    		e.printStackTrace();
    		logger.error("load config failed.");
    		return;
    	}
        // 根据ip，直接创建链接，无HA的功能
        String instances = prop.getProperty("client.instance","");
        String tabelReg  = prop.getProperty("client.table.reg", ".*\\..*");
        String ip    = prop.getProperty("canal.server.ip","");
        int    port  = Integer.parseInt(prop.getProperty("canal.server.port","11111"));
        // elastic 地址
        String esAddr = prop.getProperty("elastic.server.ips","");
        int    esPort = Integer.parseInt(prop.getProperty("elastic.server.port","9300"));
        String esCluster = prop.getProperty("elastic.server.cluster","");
        String esNode  = prop.getProperty("elastic.server.node","");
        String debug   = prop.getProperty("debug","false");
        BulkProcConfig bulkConfig = new BulkProcConfig();
        bulkConfig.bulkAction = Integer.parseInt(prop.getProperty("elastic.sync.BulkAction","100"));
        bulkConfig.bulkSize = Long.parseLong(prop.getProperty("","1"));
        bulkConfig.interval = Long.parseLong(prop.getProperty("elastic.sync.Interval","5"));
        bulkConfig.conRequest = Integer.parseInt(prop.getProperty("elastic.sync.ConcurrentRequests","2"));;
        String esIps[] = esAddr.split(",");
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, port),
        	instances,
            "",
            "");
        Settings settings = Settings.builder()
                .put("cluster.name", esCluster)
                .put("node.name",esNode).build();
        final AbstractClientHandler handler = new EsClientHandler(tabelReg,esIps,esPort,settings,bulkConfig);
        handler.setConnector(connector);
        handler.setDebug("true".equals(debug));
        handler.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    logger.info("## stop the canal client");
                    handler.stop();
                } catch (Throwable e) {
                    logger.warn("##something goes wrong when stopping canal:", e);
                } finally {
                    logger.info("## canal client is down.");
                }
            }

        });
    }
    
    protected static Properties loadConfig(String path) throws IOException {
    	Properties prop = new Properties();
    	prop.load(SingleClient.class.getResourceAsStream(path)); 
    	return prop;
    }

}