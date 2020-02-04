package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.display.DisplayImpl;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {


		byte[] request = RPCUtils.marshallString(RPCID, message);
		byte[] response = rpcclient.call(request);

		String out = RPCUtils.unmarshallString(response);
		System.out.println("Message: " + out);


	}
}
