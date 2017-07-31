package com.isesol.storm;

import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.LocalDRPC;
import org.apache.storm.StormSubmitter;
import org.apache.storm.drpc.LinearDRPCTopologyBuilder;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class TestDrpc {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder("exclamation");
		builder.addBolt(new ExamBolt(), 3);
		Config conf = new Config();
	//	StormSubmitter.submitTopology("exclamation-drpc", conf, builder.createRemoteTopology());
		
		LocalDRPC drpc = new LocalDRPC();
		LocalCluster cluster = new LocalCluster();

		cluster.submitTopology("drpc-demo", conf, builder.createLocalTopology(drpc));

		System.out.println("Results for 'hello':" + drpc.execute("exclamation", "hello"));

		cluster.shutdown();
		drpc.shutdown();

	}

}

class ExamBolt extends BaseRichBolt{

	private Map _conf;
	private TopologyContext _context;
	private OutputCollector _collector;
	
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		_conf = stormConf;
		_collector = collector;
		_context = context;
	}

	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		String input = tuple.getString(1);
        _collector.emit(tuple, new Values(tuple.getValue(0), input + "!"));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("id", "result"));
	}
	
}
