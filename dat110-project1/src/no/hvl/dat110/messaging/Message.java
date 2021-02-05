package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		encoded[0] = (byte) payload.length;
		System.arraycopy(payload, 0, encoded, 1, payload.length - 1);

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		int receivedSize = received[0];
		payload = new byte[receivedSize];
		System.arraycopy(received, 1, payload, 0, receivedSize);
		
	}
}
