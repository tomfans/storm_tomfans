package com.isesol.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.builtin.Count;

import java.util.Properties;

import org.apache.storm.Config;
import org.apache.storm.kafka.trident.TridentKafkaStateFactory;
import org.apache.storm.kafka.trident.TridentKafkaUpdater;
import org.apache.storm.kafka.trident.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.kafka.trident.selector.DefaultTopicSelector;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentState;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.testing.FeederBatchSpout;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.testing.MemoryMapState;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

public class getKafkaTrident {

	public static void main(String[] args)
			throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {

		TridentTopology topology = new TridentTopology();
		FixedBatchSpout spout = new FixedBatchSpout(new Fields("sentence"), 3,
				new Values("the cow jumped over the moon"),
				new Values("the man went to the store and bought some candy"),
				new Values("four score and seven years ago"), new Values("how many apples can you eat"));
		spout.setCycle(true);
		// set producer properties.
		Properties props = new Properties();
		props.put("bootstrap.servers",
				"namenode01.isesol.com:9092,namenode02.isesol.com:9092,datanode03.isesol.com:9092,datanode04.isesol.com:9092");
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		/*
		 * TridentKafkaStateFactory stateFactory = new
		 * TridentKafkaStateFactory().withProducerProperties(props)
		 * .withKafkaTopicSelector(new DefaultTopicSelector("test"))
		 * .withTridentTupleToKafkaMapper(new
		 * FieldNameBasedTupleToKafkaMapper("word", "count"));
		 * stream.partitionPersist(stateFactory, fields, new
		 * TridentKafkaUpdater(), new Fields());
		 */
		TridentState wordCounts = topology.newStream("jlwang1", spout)
				.each(new Fields("sentence"), new Split(), new Fields("word")).groupBy(new Fields("word"))
				.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count")).parallelismHint(6);

		Config conf = new Config();
		/*
		 * LocalCluster localCluster = new LocalCluster();
		 * localCluster.submitTopology("kafkaTridentTest", conf,
		 * topology.build()); Utils.sleep(9999999);
		 * 
		 */
		StormSubmitter.submitTopology("kafkaTridentTest", conf, topology.build());
	}

}
