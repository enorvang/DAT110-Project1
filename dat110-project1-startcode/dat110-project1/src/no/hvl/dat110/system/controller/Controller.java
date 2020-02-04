package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

import static java.lang.Thread.sleep;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		Display display;
		Sensor sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);


		// TODO
		// connect to sensor and display RPC servers
		sensorclient.connect();
		displayclient.connect();

		// create local display and sensor objects
		sensor = new Sensor();
		display = new Display();

		// register display and sensor objects in the RPC layer
		displayclient.register(display);
		sensorclient.register(sensor);


		// register stop methods in the RPC layer
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		// TODO:
		// loop while reading from sensor and write to display via RPC
		int i = 0;
		while(i < 20){
			int temp = sensor.read();
			try{
				sleep(2000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			display.write(Integer.toString(temp));
			i++;
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
