package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.sensor.SensorImpl;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {


		int temp = 0;

		byte[] request = RPCUtils.marshallInteger(RPCID, temp);
		byte[] response = rpcclient.call(request);

		temp = RPCUtils.unmarshallInteger(response);


		
		return temp;
	}
	
}
