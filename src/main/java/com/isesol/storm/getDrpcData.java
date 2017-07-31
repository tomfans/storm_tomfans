package com.isesol.storm;

import org.apache.storm.Config;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.DRPCExecutionException;
import org.apache.storm.thrift.TException;
import org.apache.storm.thrift.transport.TTransportException;
import org.apache.storm.utils.DRPCClient;


public class getDrpcData {
	
	public static void main(String[] args) throws DRPCExecutionException, AuthorizationException, TException{
		
		Config conf = new Config();
		conf.setDebug(true);
		DRPCClient client = new DRPCClient(conf, "10.215.4.165", 3772);
		String result = client.execute("aaa", "http://twitter.com");
		System.out.println(result);
		
	//	DRPCClient client = new DRPCClient("drpc.server.location", 3772);
	}

}
