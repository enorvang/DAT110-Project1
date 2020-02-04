package no.hvl.dat110.messaging;


import no.hvl.dat110.TODO;

public class Message {
	private final int MESSAGE_SIZE = 128;
	private byte[] payload;

	public Message(byte[] payload) {
		if (payload.length <= MESSAGE_SIZE-1) {
			this.payload = payload;
		}else{
			throw new IllegalArgumentException("Payload size is too large.");
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded =  new byte[MESSAGE_SIZE];
		encoded[0] = (byte) payload.length;
		for(int i = 0; i < payload.length; i++){
			encoded[i+1] = payload[i];
		}




		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		int length = received[0];
		payload = new byte[length];
		for(int i = 0; i < length; i++){
			payload[i] = received[i+1];
		}

		
	}
}
