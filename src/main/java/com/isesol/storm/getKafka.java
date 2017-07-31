package com.isesol.storm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.apache.storm.*;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.generated.Grouping;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.hbase.bolt.HBaseBolt;
import org.apache.storm.hbase.bolt.mapper.SimpleHBaseMapper;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.task.WorkerTopologyContext;
import org.apache.storm.topology.InputDeclarer;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import com.jcraft.jsch.jce.MD5;
import com.jcraft.jsch.jce.Random;

import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import java.security.MessageDigest;
import java.security.acl.Group;

public class getKafka {

	public static void main(String[] args)
			throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		String zkConnString = "datanode01.isesol.com:2181,datanode02.isesol.com:2181,datanode03.isesol.com:2181,datanode04.isesol.com:2181";
		String topicName = "2001";
		String zkRoot = "/data/storm";
		BrokerHosts hosts = new ZkHosts(zkConnString);
		SpoutConfig spoutConfig = new SpoutConfig(hosts, topicName, zkRoot, "jlwang3");
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.EarliestTime();
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		TopologyBuilder builder = new TopologyBuilder();

		/*
		 * List<String> fieldNameList = new ArrayList<String>();
		 * fieldNameList.add("linetest"); fieldNameList.add("line");
		 */

		List<String> fieldNameList = new ArrayList<String>();
		fieldNameList.add("rowkey");
		for (Field field : Topic2001.class.getDeclaredFields()) {
			fieldNameList.add(field.getName());
		}

		builder.setSpout("kafka-reader", kafkaSpout, 1);
		//builder.setBolt("word-splitter", new kafkaBolt(), 2).shuffleGrouping("kafka-reader");
		builder.setBolt("Handler2001Bolt", new HandlerBolt(fieldNameList), 3).shuffleGrouping("kafka-reader");
		// builder.setBolt("word-transfer", new transferBolt(),
		// 2).shuffleGrouping("word-splitter");
		Config conf = new Config();
		Map<String, String> HBConfig = Maps.newHashMap();
		HBConfig.put("hbase.zookeeper.property.clientPort", "2181");
		HBConfig.put("hbase.zookeeper.quorum",
				"datanode01.isesol.com:2181,datanode02.isesol.com:2181,datanode03.isesol.com:2181,datanode04.isesol.com:2181");
		HBConfig.put("zookeeper.znode.parent", "/hbase");

		conf.put("HBCONFIG", HBConfig);
		SimpleHBaseMapper mapper = new SimpleHBaseMapper();
		mapper.withColumnFamily("cf");
		mapper.withColumnFields(new Fields(fieldNameList));
		mapper.withRowKeyField("rowkey");
		HBaseBolt hBaseBolt = new HBaseBolt("test5", mapper).withConfigKey("HBCONFIG");
		hBaseBolt.withFlushIntervalSecs(15);
		hBaseBolt.withBatchSize(5000);
		builder.setBolt("hbase", hBaseBolt,6).shuffleGrouping("Handler2001Bolt");
		String name = getKafka.class.getSimpleName();
		conf.setNumWorkers(3);
		//conf.setNumAckers(20);
		conf.setNumEventLoggers(1);
		// conf.setMaxSpoutPending(20000);
		conf.setMessageTimeoutSecs(90);
		// LocalCluster localCluster = new LocalCluster();
		// localCluster.submitTopology(name, conf, builder.createTopology());
		StormSubmitter.submitTopology("getkafka", conf, builder.createTopology());
		// Utils.sleep(9999999);

	}
}

class transferBolt extends BaseRichBolt {

	private Map conf;
	private TopologyContext context;
	private OutputCollector collector;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub

		this.conf = stormConf;
		this.context = context;
		this.collector = collector;

	}

	public void execute(Tuple input) {
		// TODO Auto-generated method stub

		try {
			String line = input.getString(0);
			collector.emit(input,
					new Values(UUID.randomUUID().toString() + "-test1", UUID.randomUUID().toString(), line));
			collector.ack(input);
		} catch (Exception ex) {
			collector.fail(input);
		}

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("rowkey", "linetest", "line"));
	}

}

class kafkaBolt extends BaseRichBolt {

	private Map conf;
	private TopologyContext context;
	private OutputCollector collector;

	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		try {
			String line = input.getString(0);
			collector.emit(input, new Values(line));
			collector.ack(input);
		} catch (Exception ex) {
			collector.fail(input);
		}

	}

	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// TODO Auto-generated method stub
		this.conf = arg0;
		this.context = arg1;
		this.collector = arg2;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

		// declarer.declare(new Fields("line"));

	}
}

class HandlerBolt extends BaseRichBolt {

	ObjectMapper objectMapper;
	List<String> fieldNameList;
	private Map conf;
	private TopologyContext context;
	private OutputCollector collector;

	public HandlerBolt(List<String> fieldNameList) {
		this.fieldNameList = fieldNameList;

	}

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.conf = stormConf;
		this.context = context;
		this.collector = collector;
		this.objectMapper = new ObjectMapper();
		if (this.fieldNameList == null) {
			this.fieldNameList = new ArrayList<String>();
			fieldNameList.add("rowkey");
			for (Field field : Topic2001.class.getDeclaredFields()) {
				this.fieldNameList.add(field.getName());
			}
		}

	}

	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 将消息变成byte数组
			byte[] input = message.getBytes();

			// 3 计算后获得字节数组,这就是那128位了
			byte[] buff = md.digest(input);

			// 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}

	public void execute(Tuple input) {
		try {
			String jsonStr = input.getString(0);
			Map<String, Object> objMap = null;
			objMap = objectMapper.readValue(jsonStr, Map.class);

			String rowKey = String.valueOf(objMap.get("rowKey"));
			String contentStr = String.valueOf(objMap.get("messageContent"));
			Map contentMap;
			contentMap = objectMapper.readValue(contentStr, Map.class);

			List<Object> content = new ArrayList<Object>();

			for (String fieldName : fieldNameList) {
				if ("rowkey".equals(fieldName)) {
					content.add(rowKey);
				} else {
					Object fieldContent = contentMap.get(fieldName);
					content.add(fieldContent == null ? "" : String.valueOf(fieldContent));
				}
			}
			Values outPut = new Values();
			outPut.addAll(content);
			collector.emit(input, outPut);
			collector.ack(input);
		} catch (Exception e) {
			e.printStackTrace();
			collector.fail(input);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		Fields fields = new Fields(this.fieldNameList);
		declarer.declare(fields);
	}

}